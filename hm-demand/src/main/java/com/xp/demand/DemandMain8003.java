package com.xp.demand;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Climb.Xu
 * @date 2020/9/22 19:46
 */
@MapperScan("com.xp.demand.dao")
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.xp.demand.feign")
public class DemandMain8003 {
    public static void main(String[] args) {
        SpringApplication.run(DemandMain8003.class, args);
    }
}
