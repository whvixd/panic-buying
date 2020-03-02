package com.github.whvixd.panic.buying.manager;

import org.junit.Test;

import java.util.stream.IntStream;

/**
 * Created by wangzhx on 2020/3/2.
 */
public class CacheManagerTest {

    @Test
    public void test() {
        CacheManager cacheManager = new CacheManager();
//        System.out.println(cacheManager.getWhitDefaultValue("a"));
//        System.out.println(cacheManager.getWhitDefaultValue("a"));
//        System.out.println(cacheManager.get("1"));

        IntStream.range(0, 100).forEach(c ->
                new Thread(() ->
                        System.out.println(cacheManager.add("a"))).start());

        IntStream.range(0, 100).forEach(c ->
                new Thread(() ->
                        System.out.println(cacheManager.subtract("a"))).start());
    }

}
