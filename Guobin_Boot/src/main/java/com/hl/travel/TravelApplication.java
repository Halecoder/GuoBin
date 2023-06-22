package com.hl.travel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(basePackages = "com.hl.travel.model.dao")
@EnableScheduling
public class TravelApplication {
    public static void main(String[] args) {
        SpringApplication.run(TravelApplication.class, args);
    }

}
