package com.kun98.server.interceptor;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kun98.common.po.User;
import com.kun98.server.annotations.IPRequired;
import com.kun98.server.annotations.LoginRequired;
import com.kun98.server.service.UserService;
import com.kun98.server.utils.IpUtils;
import com.kun98.server.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author LIU JIANKUN
 * @create 2022-03-14 14:41
 */
@Slf4j
@Component
public class AuthenticationInterceptor implements HandlerInterceptor{


    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        log.info("request:[{}]", request.toString());
        log.info("request.getRemoteHost():[{}]", request.getRemoteHost());
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        IPRequired ipRequired = method.getAnnotation(IPRequired.class);
        if (ipRequired != null) {
            String ipAddress = IpUtils.getIpAddr(request);
            log.info("ipAddress:[{}]", ipAddress);
            request.setAttribute("host", ipAddress);
            return true;
        }

        // 判断接口是否需要登录
        LoginRequired loginRequired = method.getAnnotation(LoginRequired.class);
        // 有 @LoginRequired 注解，需要认证
        if (loginRequired != null) {
            // 执行认证
//            String token = request.getHeader("token");  // 从 http 请求头中取出 token
            String token = request.getHeader("Authorization");
            if (token == null) {
                throw new RuntimeException("无token，请重新登录");
            }
            String userId;
            try {
                DecodedJWT verify = JWTUtils.getTokenInfo(token);
                userId= verify.getClaim("id").asString();
            } catch (JWTDecodeException e) {
                throw new RuntimeException("token无效，请重新登录");
            }
            User user = userService.findById(Long.parseLong(userId));
            if (user == null) {
                throw new RuntimeException("用户不存在，请重新登录");
            }
            // 验证 token
            try {
                JWTUtils.verifyToken(token);
            } catch (JWTVerificationException e) {
                throw new RuntimeException("token无效，请重新登录");
            }
            request.setAttribute("currentUser", user);
            return true;
        }

        return true;
    }
}
