package com.github.whvixd.panic.buying.util;

import com.github.whvixd.panic.buying.model.Constants;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * UUID由以下几部分的组合：
 * （1）当前日期和时间，UUID的第一个部分与时间有关，如果你在生成一个UUID之后，过几秒又生成一个UUID，则第一个部分不同，其余相同。
 * （2）时钟序列。
 * （3）全局唯一的IEEE机器识别号，如果有网卡，从网卡MAC地址获得，没有网卡以其他方式获得。
 *
 * Created by wangzhixiang on 2020/8/7.
 */
@UtilityClass
public class IdUtil {
    public String generate(String... keys) {
        String uuid = UUID.randomUUID().toString();
        if(keys==null){
            return uuid;
        }
        StringBuilder stringBuilder=new StringBuilder();
        for(String key:keys){
            if(StringUtils.isNotBlank(key)){
                stringBuilder.append(Constants.Symbol.UNDERLINE).append(key);
            }
        }
        return uuid.concat(stringBuilder.toString());
    }

    public String generate() {
        return generate(StringUtils.EMPTY);
    }
}
