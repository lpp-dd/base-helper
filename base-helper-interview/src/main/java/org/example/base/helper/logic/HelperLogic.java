package org.example.base.helper.logic;

import lombok.extern.slf4j.Slf4j;
import org.example.base.helper.redis.lock.RedisLock;
import org.example.base.helper.redis.lock.RedissonLock;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author panfudong
 * @description
 */
@Service
@Slf4j
public class HelperLogic {

    @Resource
    private ApplicationContext applicationContext;

    public void redisLockTest(Long id) {
        HelperLogic selfLogic = applicationContext.getBean(HelperLogic.class);
        for (int i = 0; i < 2; i++) {
            selfLogic.reentryRedisLockTest(id);
        }
    }

    @RedisLock(expression = "#id", leaseTime = 10000L)
    public void reentryRedisLockTest(Long id) {
        log.info("redis Lock in id:{}", id);
    }

    public void redissionLockTest(Long id) {
        HelperLogic selfLogic = applicationContext.getBean(HelperLogic.class);
        for (int i = 0; i < 2; i++) {
            selfLogic.reentryRedissionLockTest(id);
        }
    }

    @RedissonLock(expression = "#id", leaseTime = 10000L)
    public void reentryRedissionLockTest(Long id) {
        log.info("redission lock in id:{} threadId:{}", id, Thread.currentThread().getId());
    }


}
