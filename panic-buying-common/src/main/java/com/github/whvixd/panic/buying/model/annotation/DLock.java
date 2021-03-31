package com.github.whvixd.panic.buying.model.annotation;

import com.github.whvixd.panic.buying.enums.ExpireType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁
 * Created by wangzhixiang on 2021/3/31.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface DLock {
    /**
     * 前缀标识，默为值：当前注解所在类的类名
     */
    String lockPrefix() default "";

    /**
     * key，默为值：注解的方法名
     */
    String lockKey() default "";

    /**
     * 幂等key
     * 格式：spring EL
     * 默认参数名 #arg0,#arg1
     * 可使用 @DKeyAlias 给参数添加别名
     */
    String[] idempotentKeys() default "";

    /**
     * 过期时间，默认5秒
     */
    long expireTime() default 5;

    /**
     * 过期时间单位，默认秒
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * 失效类型，默认根据过期时间失效
     */
    ExpireType expireType() default ExpireType.TIME;
}
