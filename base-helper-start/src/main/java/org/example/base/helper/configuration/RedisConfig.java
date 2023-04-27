package org.example.base.helper.configuration;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.ReadMode;
import org.redisson.config.SubscriptionMode;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author panfudong
 * @description
 */
public class RedisConfig {

    private String masterAddress;
    private String slaveAddress;
    private String password;

    private String host;
    private int port;
    private int timeout;

    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useMasterSlaveServers()
                .setMasterAddress(masterAddress)
                .addSlaveAddress(slaveAddress)
                .setPassword(password)
                .setReadMode(ReadMode.MASTER)
                .setSubscriptionMode(SubscriptionMode.MASTER);
        return Redisson.create(config);
    }

    public JedisPool jedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        return new JedisPool(jedisPoolConfig, host, port, timeout, password);
    }
}
