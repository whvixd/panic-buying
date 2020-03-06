package com.github.whvixd.panic.buying.manager;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangzhx on 2020/3/2.
 */
@Component
public class CacheManager {

    private Cache<Object, Object> guavaCache = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.SECONDS).recordStats().initialCapacity(100).build();

    private AtomicInteger count = new AtomicInteger(1);

    private final ReentrantLock reentrantLock = new ReentrantLock();

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
        try {
            //持有锁的线程长期不释放的时候，正在等待的线程可以选择放弃等待
            reentrantLock.lockInterruptibly();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            Object v = get(k);
            if (v == null) {
                put(k, count.get());
            } else {
                put(k, count.incrementAndGet());
            }
            return (int) get(k);
        } finally {
            reentrantLock.unlock();
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
