package com.kun98.encrypt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author LIU JIANKUN
 * @create 2022-03-15 23:42
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableHystrix
public class EncryptApplication {
    public static void main(String[] args) {
        SpringApplication.run(EncryptApplication.class);
    }
}
