package com.github.whvixd.panic.buying.aspect;

import com.github.whvixd.panic.buying.model.Result;
import com.github.whvixd.panic.buying.model.annotation.RateLimit;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by wangzhx on 2020/3/2.
 */
@Slf4j
@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class RateLimitAspect {

    private Map<String, RateLimiter> rateLimiterMap = Maps.newConcurrentMap();

    @Before(value = "@annotation(com.github.whvixd.panic.buying.model.annotation.RateLimit)")
    public void limit(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = method.getName();
        String key = className + "_" + methodName;

        RateLimiter rateLimiter = rateLimiterMap.get(key);
        RateLimit rateLimit = method.getDeclaredAnnotation(RateLimit.class);
        double permitsPerSecond = rateLimit.permitsPerSecond();
        int permits = rateLimit.permits();
        if (rateLimiter == null) {
            rateLimiter = RateLimiter.create(permitsPerSecond);
            rateLimiterMap.put(key, rateLimiter);
        }
        log.info("rate limit method:{}", key);
        boolean flag = rateLimiter.tryAcquire(permits);
        if (!flag) {
            Result.fail();
        }
    }
}
