package com.xp.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Climb.Xu
 * @date 2020/9/22 20:14
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AuthMain9000 {
    public static void main(String[] args) {
        SpringApplication.run(AuthMain9000.class, args);
    }
}
