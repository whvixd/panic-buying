package com.github.whvixd.panic.buying.util;

import org.springframework.beans.BeanUtils;

import java.util.function.BiConsumer;

public class BeanUtil {

    public static <Source, Target> Target transfer(Source source, Class<Target> targetClass) {
        return transfer(source, targetClass, null);
    }

    public static <Source, Target> Target transfer(Source source, Class<Target> targetClass, Processor<Source, Target> processor) {
        try {
            Target target = targetClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, target);
            if (processor != null) {
                processor.accept(source, target);
            }
            return target;
        } catch (Exception e) {
            throw new RuntimeException("Class Cast Error!");
        }
    }

    interface Processor<Source, Target> extends BiConsumer<Source, Target> {
    }

}
