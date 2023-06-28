package com.hl.travel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients(basePackages = "com.hl.travel.clients")
public class GuobinOauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuobinOauthApplication.class, args);
    }
}