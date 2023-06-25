package com.hl.travel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 描述：  Member服务启动类
 */

@SpringBootApplication
@MapperScan("com.hl.travel.model.dao")
@EnableFeignClients(basePackages = "com.hl.travel.clients")
public class GuobinMemberApplication {

    public static void main(String[] args) {
       SpringApplication.run(GuobinMemberApplication.class, args);
    }
}