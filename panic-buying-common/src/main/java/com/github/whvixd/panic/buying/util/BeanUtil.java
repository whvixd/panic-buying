package com.github.whvixd.panic.buying.util;

import org.springframework.beans.BeanUtils;

import java.util.function.BiConsumer;

/**
 * 无参构造器之间的类型转换
 */
public class BeanUtil {

    public static <Source, Target> Target transfer(Source source, Class<Target> target) {
        return transfer(source, target, null);
    }

    public static <Source, Target> Target transfer(Source source, Class<Target> target, Processor<Source, Target> processor) {
        try {
            Target after = target.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, after);
            if (processor != null) {
                processor.accept(source, after);
            }
            return after;
        } catch (Exception e) {
            throw new RuntimeException("Class Cast Error!");
        }
    }

    interface Processor<Source, Target> extends BiConsumer<Source, Target> {
    }

}
