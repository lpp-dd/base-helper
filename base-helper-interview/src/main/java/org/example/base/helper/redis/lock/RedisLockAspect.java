package org.example.base.helper.redis.lock;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.base.helper.common.ExpressionParserUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author panfudong
 * @description <a href="https://www.cnblogs.com/wangyingshuo/p/14510524.html">参考文档</a>
 *
 * 特性
 * 1、互斥性
 * 2、可重入性 todo
 * 3、安全性 避免非法删除
 * 4、超时解锁
 * 5、高可用性 依赖redis集群
 *
 * 支持能力：
 * 1、自旋重试 todo
 * 2、自定义拒绝策略
 *
 * 关于异常情况的思考
 * 1、redisLock注解属性非法，显式抛出IllegalArgumentException
 * 2、redis超时，默认按照加锁失败走拒绝策略
 * 3、被代理方法异常，正常抛出
 */
@Aspect
@Slf4j
public class RedisLockAspect {

    @Resource
    private JedisPool jedisPool;

    private static final String SUCCESS = "OK";

    private static final String UNLOCK_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

    @Around(value = "@annotation(org.example.base.helper.redis.lock.RedisLock)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        RedisLock lockAnno = method.getAnnotation(RedisLock.class);
        if (StringUtils.isBlank(lockAnno.expression()) || lockAnno.leaseTime() < 1 || Objects.isNull(lockAnno.rejectPolicy())) {
            throw new IllegalArgumentException("redis lock annotation property illegal");
        }
        Object[] args = pjp.getArgs();
        String[] names = signature.getParameterNames();
        Map<String, Object> dataMap = new HashMap<>(args.length);
        for (int i = 0; i < args.length; i++) {
            dataMap.put(names[i], args[i]);
        }
        String key = ExpressionParserUtil.parseWithDefault(dataMap, lockAnno.expression(), String.class, null);
        if (Objects.isNull(key)) {
            throw new IllegalArgumentException("redis lock annotation expression illegal");
        }
        String currentUniqueId = InetAddress.getLocalHost().getHostAddress() + ":" + Thread.currentThread().getId();
        boolean lockFlag = Objects.equals(execute(jedis -> jedis.set(key, currentUniqueId, SetParams.setParams().nx().px(lockAnno.leaseTime()))), SUCCESS);
        if (!lockFlag) {
            lockAnno.rejectPolicy().getRejectPolicy().doReject();
        }
        try {
            return pjp.proceed();
        } finally {
            execute(jedis -> jedis.eval(UNLOCK_SCRIPT, Collections.singletonList(key), Collections.singletonList(currentUniqueId)));
        }
    }

    private <R> R execute(Function<Jedis, R> function) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return function.apply(jedis);
        } catch (Exception e) {
            log.error("redis lock error:{}", e.getMessage(), e);
            return null;
        } finally {
            if (Objects.nonNull(jedis)) {
                jedis.close();
            }
        }
    }
}
