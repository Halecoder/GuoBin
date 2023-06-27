package com.hl.travel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

@SpringBootApplication
@MapperScan("com.hl.travel.model.dao")
public class GuobinCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuobinCoreApplication.class, args);
    }
}