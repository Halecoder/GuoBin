package com.hl.travel;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 描述：   文件上传服务
 */

@SpringBootApplication
@MapperScan("com.hl.travel.model.dao")
public class GuobinUploadApplication {

    public static void main(String[] args) {
       SpringApplication.run(GuobinUploadApplication.class, args);
    }
}