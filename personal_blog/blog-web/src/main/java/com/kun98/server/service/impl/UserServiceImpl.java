package com.kun98.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.kun98.server.service.UserService;
import com.kun98.common.po.User;
import com.kun98.server.mapper.UserMapper;
import com.kun98.server.utils.BeanUtilsIgnoreNull;
import com.kun98.server.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

/**
 * @author LIU JIANKUN
 * @create 2022-02-02 14:25
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Resource
    private BCryptPasswordEncoder encoder;

    @Resource
    private RedisUtils redisUtils;

    @Override
    public boolean userExist(String username) {//搜索用户名是否存在
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("1");
        wrapper.eq("username", username).last("limit 1");
        return userMapper.selectCount(wrapper) != 0;
    }

    @Override
    public User login(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("uid", "username", "password", "data_status", "nickname", "avatar");
        wrapper.eq("username", user.getUsername());
        //登录的用户
        User login_user = userMapper.selectOne(wrapper);
        log.info("login_user:[{}]", login_user.toString());
        if (!encoder.matches(user.getPassword(), login_user.getPassword())) {
            throw new RuntimeException("用户名或密码不正确，登录失败");
        }
        if (!login_user.isDataStatus()) {
            throw new RuntimeException("用户已被禁用,登录失败");
        }
        update(new UpdateWrapper<User>()
//               .set("last_time", new Date())
                .set("last_ip", user.getLastIp())
                .eq("username", login_user.getUsername()));

        return login_user;
    }

    @Override
    public boolean verifyCode(String verKey, String inputCode) {

        String codeInRedis = (String) redisUtils.get(verKey);
        redisUtils.del(verKey);

        if(inputCode == null || codeInRedis.isEmpty() ){
            return false;
        }

        return inputCode.equals(codeInRedis);
    }
    /**
     * 用户提供的图片链接无效就自动生成图片
     * @param postUrl
     * @return
     */
    public String isImagesTrue(String postUrl) {
        int max = 1000;
        int min = 1;
        String picUrl = "https://unsplash.it/100/100?image=";
        try {
            URL url = new URL(postUrl);
            HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
            urlCon.setRequestMethod("POST");
            urlCon.setRequestProperty("Content-type",
                    "application/x-www-form-urlencoded");
            if (urlCon.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return postUrl;
            } else {
                Random random = new Random();
                int s = random.nextInt(max) % (max - min + 1) + min;
                return picUrl+s;
            }
        } catch (Exception e) {   // 代表图片链接无效
            Random random = new Random();
            int s = random.nextInt(max) % (max - min + 1) + min;
            return picUrl+s;
        }
    }

    @Override
    public User updateUser(User user) {
        Long uid = user.getUid();
        User userDB = findById(uid.longValue());
        if (userExist(user.getUsername()) && !userDB.getUsername().equals(user.getUsername())) {
            throw new RuntimeException("用户名已被注册");
        }
        if (user.getPassword() != null && !user.getPassword().equals("")) { // 用户更改了密码
            System.out.println("修改密码");
            user.setPassword(encoder.encode(user.getPassword()));
        } else {
            user.setPassword(null);
        }
        BeanUtilsIgnoreNull.copyPropertiesIgnoreNull(user, userDB);
        userDB.setUpdateTime(LocalDateTime.now());
        userDB.setDataStatus(true);
        userDB.setAvatar(isImagesTrue(user.getAvatar()));
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid);
        int i = userMapper.update(userDB,wrapper);
        return i > 0 ? userDB : null;
    }

    @Override
    public User findById(long parseLong) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", parseLong);
        if (userMapper.selectOne(wrapper) == null) {
            return null;
        }
        return userMapper.selectOne(wrapper);
    }

    @Override
    public void add(User user) {
        String username = user.getUsername();
        if(userExist(username)){
            throw new RuntimeException("user already exists");
        }
        long id = IdWorker.getId();
        user.setUid(id);
        user.setDataStatus(true);
        user.setAvatar(isImagesTrue(user.getAvatar()));
        user.setPassword(encoder.encode(user.getPassword()));
        userMapper.insert(user);
    }

    @Override
    @Cacheable(value = {"UserListMap"})
    public List<User> getUserList() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("uid", "username", "nickname", "avatar")
                .orderByAsc("create_time");
        return userMapper.selectList(wrapper);
    }
}
