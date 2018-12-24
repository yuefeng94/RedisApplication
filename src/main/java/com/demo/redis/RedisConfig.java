package com.demo.redis;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;


/**
 * @Author: demo
 * @Date: 2018/12/20
 * @Description: redis 配置类
 * @Version: 1.0
 */
@Configuration
public class RedisConfig {

    /**
     * 配置RedisTemplate
     * RedisTemplate默认采用的是JDK的序列化策略
     * Spring-boot 2.0 默认使用redis client是lettuce而不是jedis
     * @param connectionFactory
     * @return
     * @throws UnknownHostException
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
        //将redisTemplate-->key,value改为String的序列化策略(StringRedisSerialize)
        redisTemplate.setConnectionFactory(connectionFactory);
        StringRedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        //redisTemplate.setHashKeySerializer(stringSerializer);
        //redisTemplate.setHashValueSerializer(stringSerializer);
        return redisTemplate;
    }

    /**
     * 配置StringRedisTemplate
     * StringRedisTemplate默认采用的是String的序列化策略
     * @return
     * @throws UnknownHostException
     */
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory connectionFactory) throws  UnknownHostException {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(connectionFactory);
        return stringRedisTemplate;
    }

    @Bean
    public RedisService redisService(RedisTemplate redisTemplate) {
        RedisService redisService = new RedisService();
        redisService.setRedisTemplate(redisTemplate);
        return redisService;
    }
}
