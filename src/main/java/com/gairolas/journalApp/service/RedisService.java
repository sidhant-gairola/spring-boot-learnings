package com.gairolas.journalApp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public <T> T get(String key, Class<T> entityClass) {    // to get the value of the key from the redis
        try {
            Object o = redisTemplate.opsForValue().get(key);
            if (o == null) {
                log.warn("Cache miss for key: {}", key);
                return null;
            }

            if (o instanceof String) {
                String json = (String) o;
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(json, entityClass);
            } else {
                log.error("Unexpected Redis value type for key {}: {}", key, o.getClass().getName());
                return null;
            }
        } catch (JsonProcessingException e) {
            log.error("Exception : ", e);
            return null;
        }
    }

    public void set(String key, Object o, Long ttl) {   // to set value to a key in redis
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonValue = mapper.writeValueAsString(o);
            redisTemplate.opsForValue().set(key, jsonValue, ttl, TimeUnit.SECONDS);  // ttl is the timelimit till redis store and timeunit is the type in which we will give time
        } catch (Exception e) {
            log.error("Exception : ", e);
        }
    }
}
