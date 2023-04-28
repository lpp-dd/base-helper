package org.example.base.helper.redis.lock;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.base.helper.common.ExpressionParserUtil;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author panfudong
 * @description
 */
@Aspect
@Slf4j
public class RedissonLockAspect {

    @Resource
    private RedissonClient redissonClient;

    @Around(value = "@annotation(org.example.base.helper.redis.lock.RedissonLock)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        RedissonLock lockAnno = method.getAnnotation(RedissonLock.class);
        if (StringUtils.isBlank(lockAnno.expression()) || lockAnno.leaseTime() < 1 || lockAnno.waitTime() < 1 || Objects.isNull(lockAnno.rejectPolicy())) {
            throw new IllegalArgumentException("redis lock annotation property illegal");
        }
        Object[] args = pjp.getArgs();
        String[] names = signature.getParameterNames();
        Map<String, Object> dataMap = new HashMap<>(args.length);
        for (int i = 0; i < args.length; i++) {
            dataMap.put(names[i], args[i]);
        }
        String key = ExpressionParserUtil.parseWithDefault(dataMap, lockAnno.expression(), String.class, null);
        Assert.isTrue(StringUtils.isNotBlank(key), "redis lock annotation expression illegal");
        boolean lockFlag = false;
        RLock rLock = null;
        try {
            rLock = redissonClient.getLock(key);
            //支持线程级别重入
            lockFlag = rLock.tryLock(lockAnno.waitTime(), lockAnno.leaseTime(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            log.error("redis lock error:{}", e.getMessage(), e);
        }
        if (!lockFlag) {
            lockAnno.rejectPolicy().getRejectPolicy().doReject();
        }
        try {
            return pjp.proceed();
        } finally {
            if (Objects.nonNull(rLock) && rLock.isLocked() && rLock.isHeldByCurrentThread()) {
                try {
                    rLock.unlock();
                } catch (Exception e) {
                    log.error("redis unlock error:{}", e.getMessage(), e);
                }
            }
        }
    }
}