package com.github.whvixd.panic.buying.util;

import com.google.common.base.Joiner;
import lombok.experimental.UtilityClass;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据库工具类
 * <p>
 * Created by wangzhx on 2020/7/1.
 */
@UtilityClass
public class DBUtil {
    /**
     * 获取分表的名称
     *
     * @param id        分表的唯一标识
     * @param tableName 原表名
     * @param mod       表的数量
     * @return 分表名称
     */
    public String getTableName(String id, String tableName, int mod) {
        // 校验参数
        AssertUtil.checkArgs(id, tableName);
        // md5加密
        String md5Hex = DigestUtils.md5Hex(id);
        // 取后三位
        String md5HexSub = md5Hex.substring(md5Hex.length() - 3);
        // 转int
        int num = Integer.valueOf(md5HexSub, 16);
        // 取模获取分表
        int index = num % mod;
        return tableName + "_" + index;
    }

    public String generateValues(Object... values){
        List<Object> list = Arrays.asList(values);
        list=list.stream().map(value->{
            if(value instanceof String){
                // h2 字符需要单引号
                return "'"+value+"'";
            }
            return value;
        }).collect(Collectors.toList());
        return Joiner.on(",").join(list);
    }

}
