package com.hl.travel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
@MapperScan("com.hl.travel.model.dao")
public class GuobinRoleApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuobinRoleApplication.class, args);
    }
}