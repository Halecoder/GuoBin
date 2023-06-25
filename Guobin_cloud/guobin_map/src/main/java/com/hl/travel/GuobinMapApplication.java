package com.hl.travel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hl.travel.model.dao")
public class GuobinMapApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuobinMapApplication.class, args);
    }
}