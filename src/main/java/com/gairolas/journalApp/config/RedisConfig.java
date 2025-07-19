package com.gairolas.journalApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName("host");
        config.setPort(port);
        config.setPassword(RedisPassword.of("password"));
        return new LettuceConnectionFactory(config);
    }

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(factory);

        try {
            String host = ((org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory) factory)
                    .getStandaloneConfiguration().getHostName();
            int port = ((org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory) factory)
                    .getStandaloneConfiguration().getPort();
            System.out.println("✅ Connected to Redis host: " + host + ":" + port);
        } catch (Exception e) {
            System.out.println("❌ Unable to extract Redis connection info: " + e.getMessage());
        }

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
