package com.github.whvixd.panic.buying.util.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 限流
 * Created by wangzhx on 2020/3/2.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {
    /**
     * 每秒产生的令牌
     */
    double permitsPerSecond() default 1.0;

    /**
     * 下次生成令牌的时间
     */
    int permits() default 1;
}
