package com.github.whvixd.panic.buying.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by wangzhx on 2020/7/1.
 */
@UtilityClass
public class CheckUtil {

    public void checkArgs(Object... args) {
        for (Object arg : args) {
            if (arg instanceof String) {
                if (StringUtils.isEmpty((String) arg)) {
                    throw new IllegalArgumentException(String.format("%s is not empty", arg));
                }
            } else {
                if (arg == null) {
                    throw new IllegalArgumentException(String.format("%s is not empty", arg));
                }
            }
        }
    }
}
