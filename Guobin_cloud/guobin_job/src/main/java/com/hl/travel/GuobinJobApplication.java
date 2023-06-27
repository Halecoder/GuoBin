package com.hl.travel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 描述：     Eureka服务端
 */

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class GuobinJobApplication {

    public static void main(String[] args) {
       SpringApplication.run(GuobinJobApplication.class, args);
    }
}