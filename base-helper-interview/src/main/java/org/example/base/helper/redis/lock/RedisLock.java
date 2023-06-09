package org.example.base.helper.redis.lock;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD})
@Retention(RUNTIME)
public @interface RedisLock {

    /**
     * @return 解析锁key的表达式
     */
    String expression();

    /**
     * @return 锁最长占用时间 单位毫秒
     */
    long leaseTime() default 1000L;

    /**
     * @return 加锁失败的拒绝策略
     */
    RejectPolicyEnum rejectPolicy() default RejectPolicyEnum.THROW_EXCEPTION;
}
