package org.example.base.helper.configuration;

import lombok.Getter;
import lombok.Setter;
import org.example.base.helper.redis.lock.RedisLockAspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * @author panfudong
 * @description 此类初始化项目中需要的bean，如果不需要依赖redis或者mysql资源，可注释掉@Configuration保证项目能正常启动
 */
//@Configuration
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
        redisConfig.setHost(redisHost);
        redisConfig.setPort(redisPort);
        redisConfig.setPassword(redisPassword);
        redisConfig.setTimeout(redisTimeOut);
        return redisConfig;
    }
}
