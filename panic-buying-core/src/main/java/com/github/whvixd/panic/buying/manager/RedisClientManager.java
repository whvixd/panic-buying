package com.github.whvixd.panic.buying.manager;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangzhixiang on 2021/3/31.
 */
@Slf4j
public class RedisClientManager implements RedisManager {
    private Cache<String, Object> guavaCache = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.SECONDS).recordStats().initialCapacity(100).build();

    @Override
    public boolean setnx(String redisKey, String value, int timeToLiveSeconds) {
        Object object = guavaCache.getIfPresent(redisKey);
        if (object != null) {
            return false;
        }
        guavaCache.put(redisKey, value);
        log.info("RedisClientManager->setnx success,redisKey:{},value:{},timeToLiveSeconds:{}", redisKey, value, timeToLiveSeconds);
        return true;
    }

    @Override
    public void del(String redisKey) {
        guavaCache.invalidate(redisKey);
        log.info("RedisClientManager->del success,redisKey:{}", redisKey);
    }
}
