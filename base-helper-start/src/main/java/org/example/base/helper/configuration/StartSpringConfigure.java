package org.example.base.helper.configuration;

import org.example.base.helper.redis.lock.RedisLockAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * @author panfudong
 * @description 此类基于spring bean初始化项目中需要的bean
 */
//@Configuration
public class StartSpringConfigure {

    @Bean
    public RedisLockAspect redisLockAspect() {
        return new RedisLockAspect();
    }

    @Bean
    public JedisPool jedisPool() {
        RedisConfig redisConfig = redisConfig();
        return redisConfig.jedisPool();
    }

    public RedisConfig redisConfig() {
        RedisConfig redisConfig = new RedisConfig();
        redisConfig.setHost("");
        redisConfig.setPort(0);
        redisConfig.setPassword("");
        redisConfig.setTimeout(5000);
        return redisConfig;
    }
}
