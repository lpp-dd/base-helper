package org.example.base.helper.redis.lock;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD})
@Retention(RUNTIME)
public @interface RedisLock {

    /**
     * 解析锁key的表达式
     * @return
     */
    String expression();

    /**
     * 超时时间
     * @return
     */
    long timeout() default 100L;

    /**
     * 超时时间单位
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;


}
