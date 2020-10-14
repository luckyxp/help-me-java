package com.xp.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Climb.Xu
 * @date 2020/9/22 19:49
 */
@MapperScan("com.xp.user.dao")
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.xp.user.feign")
public class UserMain8000 {
    public static void main(String[] args) {
        SpringApplication.run(UserMain8000.class, args);
    }
}
