package org.example.base.helper.configuration;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.ReadMode;
import org.redisson.config.SubscriptionMode;

/**
 * @author panfudong
 * @description
 */
public class RedisConfig {

    private String masterAddress;
    private String slaveAddress;
    private String password;

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
}
