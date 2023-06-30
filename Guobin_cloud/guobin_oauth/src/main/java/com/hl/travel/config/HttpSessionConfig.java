
package com.zit.springsecurity.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.nio.charset.StandardCharsets;

/**
 * 为Spring Security提供集群支持的会话注册表
 */
@EnableRedisHttpSession
@Configuration
public class HttpSessionConfig {

//    // 提供Redis连接，默认是localhost:6379
//    @Bean
//    public RedisConnectionFactory connectionFactory(){
//        return new JedisConnectionFactory();
//    }

    // httpSession的事件监听，改用session提供的会话注册表
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher(){
        return new HttpSessionEventPublisher();
    }

//    @Bean
//    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
//
//        // 使用 FastJsonRedisSerializer 来序列化和反序列化redis 的 value的值
//        FastJsonRedisSerializer<Object> serializer = new FastJsonRedisSerializer<>(Object.class);
//        ParserConfig.getGlobalInstance().addAccept("com.muzz");
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setCharset(StandardCharsets.UTF_8);
//        serializer.setFastJsonConfig(fastJsonConfig);
//        return serializer;
//    }

}
