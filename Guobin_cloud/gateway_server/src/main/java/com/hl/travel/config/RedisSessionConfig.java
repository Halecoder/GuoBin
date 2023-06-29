package com.hl.travel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.server.EnableRedisWebSession;

@Configuration
@EnableRedisWebSession
public class RedisSessionConfig {

    @Bean
    public <K,V> RedisTemplate<K, V> redisSessionTemplate(RedisConnectionFactory factory) {
        RedisTemplate<K, V> template = new RedisTemplate<>();
        // 配置连接工厂
        template.setConnectionFactory(factory);
        //JdkSerializationRedisSerializer jdkRedisSerializer = new JdkSerializationRedisSerializer();
        RedisSerializer<String> keySerializer = new StringRedisSerializer();
        RedisSerializer<Object> valueSerializer = new JdkSerializationRedisSerializer(
                this.getClass().getClassLoader());
        // 值采用json序列化
        template.setValueSerializer(valueSerializer);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(keySerializer);
        // 设置hash key 和value序列化模式
        template.setHashKeySerializer(keySerializer);
        template.setHashValueSerializer(valueSerializer);
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public RedisOperationsSessionRepository sessionRepository(RedisConnectionFactory redisConnectionFactory) {
        return new RedisOperationsSessionRepository(redisSessionTemplate(redisConnectionFactory));
    }

}
