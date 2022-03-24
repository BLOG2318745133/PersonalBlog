package com.kun98.server.controller.admapi;

import com.kun98.common.entity.ResultInfo;
import com.kun98.server.annotations.IPRequired;
import com.kun98.server.service.UserService;
import com.kun98.common.entity.Result;
import com.kun98.common.po.User;
import com.kun98.server.utils.JWTUtils;
import com.kun98.server.utils.RedisUtils;
import com.kun98.common.vo.UserVO;
import com.wf.captcha.SpecCaptcha;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author LIU JIANKUN
 * @create 2022-03-14 0:34
 */
@Slf4j
@RequestMapping("/admapi")
@RestController
@Api(value = "用户登录controller")
public class LoginController {

    @Resource
    private RedisUtils redisUtils;

    @Autowired
    UserService userService;

    @PostMapping("/login")
    @IPRequired
    public Result login(@RequestBody UserVO userVO, HttpServletRequest request) {
        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        boolean verifyCode = userService.verifyCode(userVO.getVerKey(), userVO.getCode());
        if(!verifyCode){
            return Result.fail().codeAndMessage(ResultInfo.VERIFYCODE_UNMATCH);
        }
        log.info("用户名:[{}]", user.getUsername());
        request.getSession().setAttribute("username", user.getUsername());//放到session里
        log.info("密码:[{}]", user.getPassword());
        try {
            //认证成功，生成jwt令牌
            user.setLastIp((String) request.getAttribute("host"));
            User userDB = userService.login(user);
            HashMap<String, String> payload = new HashMap<>();
            payload.put("id", String.valueOf(userDB.getUid()));
            payload.put("lastIp", userDB.getLastIp());
            payload.put("username", userDB.getUsername());
            String token = JWTUtils.getToken(payload);

            return Result.success().code(token).message("token generated").data(userDB);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail().codeAndMessage(ResultInfo.LOGIN_DENIED);

    }

    @RequestMapping("/captcha")
    public Result Captcha(HttpServletRequest request, HttpServletResponse response) {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        String verCode = specCaptcha.text().toLowerCase();
        String key = UUID.randomUUID().toString();
        redisUtils.set(key,verCode);
        redisUtils.expire(key,300);
        request.getSession().setAttribute("CAPTCHA",verCode);

        return Result.success().code(key).message("CAPTCHA_VERIFIED").data(specCaptcha.toBase64());


    }

}
