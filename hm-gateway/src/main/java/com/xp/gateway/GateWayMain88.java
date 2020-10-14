package com.xp.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author Climb.Xu
 * @date 2020/9/22 21:40
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class GateWayMain88 {
    public static void main(String[] args) {
        SpringApplication.run(GateWayMain88.class, args);
    }
}
