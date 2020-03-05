package com.github.whvixd.panic.buying.manager;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wangzhx on 2020/3/2.
 */
@Component
public class CacheManager {

    private Cache<Object, Object> guavaCache = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.SECONDS).recordStats().initialCapacity(100).build();

    private AtomicInteger count = new AtomicInteger(1);

    /**
     * 获取值，或没有put(key,1)
     */
    public Object getWhitDefaultValue(Object k) {
        try {
            return guavaCache.get(k, () -> 1);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public Object get(Object k) {
        return guavaCache.getIfPresent(k);
    }

    public void put(Object k, Object v) {
        guavaCache.put(k, v);
    }

    public int add(Object k) {
        synchronized (this) {
            Object v = get(k);
            if (v == null) {
                put(k, count.get());
            } else {
                put(k, count.incrementAndGet());
            }
            return (int) get(k);
        }
    }

    public int subtract(Object k) {
        synchronized (this) {
            Object v = get(k);
            if (v == null || (int) v <= 0) {
                return 0;
            } else {
                put(k, count.decrementAndGet());
            }
            return (int) get(k);
        }
    }

}
