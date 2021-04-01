package com.github.whvixd.panic.buying.manager;

/**
 * 实现方需自行注入
 * Created by wangzhixiang on 2021/3/31.
 */
public interface DLockManager {

    boolean setnx(String redisKey, String value, int timeToLiveSeconds);

    void del(String redisKey);
}
