package org.example.base.helper.redis.lock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author panfudong
 * @description
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RedissonLock {

    /**
     * @return 解析锁key的表达式
     */
    String expression();

    /**
     * @return 自旋超时时间
     */
    long waitTime() default 100L;

    /**
     * @return 锁超时释放时间
     */
    long leaseTime() default 1000L;

    /**
     * @return 加锁失败的拒绝策略
     */
    RejectPolicyEnum rejectPolicy() default RejectPolicyEnum.THROW_EXCEPTION;
}
