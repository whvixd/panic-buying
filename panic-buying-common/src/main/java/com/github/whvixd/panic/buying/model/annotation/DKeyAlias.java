package com.github.whvixd.panic.buying.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 配合 @DLock 使用，给形参添加别名
 * Created by wangzhixiang on 2021/3/31.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface DKeyAlias {
    String value();
}
