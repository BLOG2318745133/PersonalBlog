package com.kun98.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kun98.common.po.User;

import java.util.List;

/**
 * @author LIU JIANKUN
 * @create 2022-02-02 13:19
 */
public interface UserService extends IService<User> {

    /**
     * 查询是否存在同名用户
     * @param username
     * @return
     */
    boolean userExist(String username);

    /**
     * 用户登录
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 验证验证码
     * @param verKey
     * @param inputCode
     * @return
     */

    boolean verifyCode(String verKey, String inputCode);

    /**
     * 更新用户信息
     * @param user
     * @return boolean
     */
    User updateUser(User user);

    User findById(long parseLong);

    void add(User user);

    List<User> getUserList();
}
