package com.github.whvixd.panic.buying.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

/**
 * Created by wangzhx on 2020/7/1.
 */
@UtilityClass
public class AssertUtil {

    public void checkArgs(Object... args) {
        for (Object arg : args) {
            if (ObjectUtils.isEmpty(arg)) {
                throw new IllegalArgumentException("arg is not empty");
            }
        }
    }
}
