package com.kun98.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author LIU JIANKUN
 * @create 2022-01-29 0:17
 */
@SpringBootApplication
@MapperScan("com.kun98.server.mapper")
@EnableCaching
@EnableEurekaClient
public class BlogServerApplicationMain {

    public static void main(String[] args) {
        SpringApplication.run(BlogServerApplicationMain.class);
    }

    @Bean
    public BCryptPasswordEncoder BCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
