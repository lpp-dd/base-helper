package org.example.base.helper.redis.lock;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.base.helper.common.ExpressionParserUtil;
import org.springframework.expression.EvaluationException;
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

/**
 * @author panfudong
 * @description <a href="https://www.cnblogs.com/wangyingshuo/p/14510524.html">参考文档</a>
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
        if (StringUtils.isBlank(lockAnno.expression()) || lockAnno.leaseTime() < 1) {
            throw new IllegalArgumentException("redis lock annotation property illegal");
        }
        Object[] args = pjp.getArgs();
        String[] names = signature.getParameterNames();
        Map<String, Object> dataMap = new HashMap<>(args.length);
        for (int i = 0; i < args.length; i++) {
            dataMap.put(names[i], args[i]);
        }
        boolean lockFlag = false;
        String key = null;
        String currentUniqueId = null;
        Jedis jedis = null;
        try {
            key = ExpressionParserUtil.parse(dataMap, lockAnno.expression(), String.class);
            currentUniqueId = InetAddress.getLocalHost().getHostAddress() + ":" + Thread.currentThread().getId();
            jedis = jedisPool.getResource();
            String result = jedis.set(key, currentUniqueId, SetParams.setParams().nx().px(lockAnno.leaseTime()));
            lockFlag = Objects.equals(result, SUCCESS);
        } catch (EvaluationException e) {
            throw new IllegalArgumentException("redis lock annotation expression illegal");
        } catch (Exception e) {
            log.error("redis lock error and do reject policy e:{}", e.getMessage(), e);
        } finally {
            if (Objects.nonNull(jedis)) {
                jedis.close();
            }
        }
        if (!lockFlag) {
            throw new UnsupportedOperationException("lock failed");
        }
        try {
            return pjp.proceed();
        } finally {
            jedis = jedisPool.getResource();
            jedis.eval(UNLOCK_SCRIPT, Collections.singletonList(key), Collections.singletonList(currentUniqueId));
            jedis.close();
        }
    }
}
