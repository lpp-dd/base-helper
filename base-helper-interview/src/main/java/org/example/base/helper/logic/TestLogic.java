package org.example.base.helper.logic;

import lombok.extern.slf4j.Slf4j;
import org.example.base.helper.redis.lock.RedisLock;
import org.springframework.stereotype.Service;

/**
 * @author panfudong
 * @description
 */
@Service
@Slf4j
public class TestLogic {

    @RedisLock(expression = "#id", leaseTime = 10000L)
    public void redisLockTest(Long id) throws InterruptedException {
        Thread.sleep(10000L);
        log.info("redis Lock in:{}", id);
    }


}
