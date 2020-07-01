package com.github.whvixd.panic.buying.common.util;

import com.github.whvixd.panic.buying.util.DBUtil;
import org.junit.Assert;
import org.junit.Test;

public class DBUtilTest {
    @Test
    public void testGenerateValues(){
        String values = DBUtil.generateValues(1, "小米", 1111L);
        Assert.assertTrue("1,'小米',1111".equals(values));
    }

    @Test
    public void testGetTableName(){
        String tableName = DBUtil.getTableName("3", "SALE_ORDER", 3);
        Assert.assertTrue("SALE_ORDER_1".equals(tableName));
    }

}
