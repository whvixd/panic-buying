package com.github.whvixd.panic.buying.common.util;

import com.github.whvixd.panic.buying.util.BeanUtil;
import lombok.Data;
import org.junit.Test;

/**
 * Created by wangzhixiang on 2020/7/30.
 */
public class BeanUtilTest {
    @Test
    public void testTransfer(){
        A a=new A();
        a.age=1;
        a.name="a";
        System.out.println(BeanUtil.transfer(a,B.class));
        System.out.println(BeanUtil.transfer(a,B.class,(o,t)->{
            t.id=o.age;
        }));
    }

    @Data
    public static class A{
        String name;
        int age;
    }

    @Data
    public static class B{
        String name;
        int id;
    }

}
