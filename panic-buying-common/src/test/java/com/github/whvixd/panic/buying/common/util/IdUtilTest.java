package com.github.whvixd.panic.buying.common.util;

import com.github.whvixd.panic.buying.util.IdUtil;
import org.junit.Test;

/**
 * Created by wangzhixiang on 2020/8/7.
 */
public class IdUtilTest {
    @Test
    public void testGenerate(){
        System.out.println(IdUtil.generate());
        System.out.println(IdUtil.generate("product","v1"));
    }
}
