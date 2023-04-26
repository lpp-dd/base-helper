package org.example.base.helper.redis.lock;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.base.helper.common.ExpressionParserUtil;
import org.redisson.api.RedissonClient;
import org.springframework.expression.EvaluationException;

import javax.annotation.Resource;
import java.lang.reflect.Method;
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
    private RedissonClient redissonClient;

    @Around(value = "@annotation(org.example.base.helper.redis.lock.RedisLock)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        RedisLock lockAnno = method.getAnnotation(RedisLock.class);
        if (StringUtils.isBlank(lockAnno.expression()) || lockAnno.timeout() < 1 || Objects.isNull(lockAnno.timeUnit())) {
            throw new IllegalArgumentException("redis lock annotation property illegal");
        }
        Object[] args = pjp.getArgs();
        String[] names = signature.getParameterNames();
        Map<String, Object> dataMap = new HashMap<>(args.length);
        for (int i = 0; i < args.length; i++) {
            dataMap.put(names[i], args[i]);
        }
        try {
            String key = ExpressionParserUtil.parse(dataMap, lockAnno.expression(), String.class);

        } catch (EvaluationException e) {
            throw new IllegalArgumentException("redis lock annotation expression illegal");
        }
        return pjp.proceed();
    }
}
