package com.github.whvixd.panic.buying.common.util;

import com.github.whvixd.panic.buying.util.SnowFlake;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * Created by wangzhixiang on 2020/8/10.
 */
public class SnowFlakeTest {
    @Test
    public void test(){
        SnowFlake snowFlake = new SnowFlake(2, 1);
        //2^12
        IntStream.range(0,1<<12).forEach(e-> System.out.println(snowFlake.nextId()));
    }
}
