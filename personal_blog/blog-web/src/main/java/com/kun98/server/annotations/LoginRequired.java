package com.kun98.server.annotations;
import java.lang.annotation.*;

/**
 * 限制必须登录才能访问的controller
 * @author LIU JIANKUN
 * @create 2022-03-14 15:49
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired {
}
