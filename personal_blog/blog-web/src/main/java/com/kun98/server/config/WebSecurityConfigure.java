package com.kun98.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author LIU JIANKUN
 * @create 2022-02-02 21:34
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf().disable().exceptionHandling();

        httpSecurity.headers().frameOptions().disable();    //Spring Security4默认是将'X-Frame-Options' 设置为 'DENY'
        httpSecurity.authorizeRequests()
                .antMatchers("/**")
                .permitAll()
//                .anyRequest().authenticated()
                .and()
                .csrf().disable();
        httpSecurity.formLogin()
                .loginProcessingUrl("/login");

    }
}
