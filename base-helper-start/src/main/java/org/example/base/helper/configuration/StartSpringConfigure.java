package org.example.base.helper.configuration;

import lombok.Getter;
import lombok.Setter;
import org.example.base.helper.redis.lock.RedisLockAspect;
import org.example.base.helper.redis.lock.RedissonLockAspect;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * @author panfudong
 * @description 此类初始化项目中需要的bean，如果不需要依赖redis或者mysql资源，可注释掉@Configuration保证项目能正常启动
 */
@Configuration
@Setter
@Getter
public class StartSpringConfigure {

    @Value("${redis.host}")
    private String redisHost;

    @Value("${redis.port}")
    private Integer redisPort;

    @Value("${redis.password}")
    private String redisPassword;

    @Value("${redis.timeout}")
    private Integer redisTimeOut;

    @Value("${redis.master}")
    private String redisMasterAddress;

    @Value("${redis.slave}")
    private String redisSlaveAddress;

    @Bean
    public RedisLockAspect redisLockAspect() {
        return new RedisLockAspect();
    }

    @Bean
    public RedissonLockAspect redissonLockAspect() {
        return new RedissonLockAspect();
    }

    @Bean
    public JedisPool jedisPool() {
        RedisConfig redisConfig = redisConfig();
        return redisConfig.jedisPool();
    }

    @Bean
    public RedissonClient redissonClient() {
        RedisConfig redisConfig = redisConfig();
        return redisConfig.redissonClient();
    }

    public RedisConfig redisConfig() {
        RedisConfig redisConfig = new RedisConfig();
        redisConfig.setHost(redisHost);
        redisConfig.setPort(redisPort);
        redisConfig.setPassword(redisPassword);
        redisConfig.setTimeout(redisTimeOut);
        redisConfig.setMasterAddress(redisMasterAddress);
        redisConfig.setSlaveAddress(redisSlaveAddress);
        return redisConfig;
    }
}
