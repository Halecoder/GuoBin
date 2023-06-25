package com.hl.travel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import static org.springframework.boot.SpringApplication.run;

/**
 * 描述：     Eureka服务端
 */

@EnableEurekaServer
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class EurekaServerApplication {

    public static void main(String[] args) {
       SpringApplication.run(EurekaServerApplication.class, args);
    }
}