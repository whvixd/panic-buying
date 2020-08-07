package com.github.whvixd.panic.buying.util;

import lombok.experimental.UtilityClass;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * 无参构造器之间的类型转换
 * 属性之间浅copy
 * <p>
 * Created by wangzhixiang on 2020/6/16.
 */
@UtilityClass
public class BeanUtil {

    // 将source转为target
    public <Source, Target> Target transfer(Source source, Class<Target> targetClass, Processor<Source, Target> processor) {
        try {
            Target target = transfer(source, targetClass);
            processor.accept(source, target);
            return target;
        } catch (Exception e) {
            throw new RuntimeException("Bean transfer error!", e);
        }
    }

    // 根据bean的getter方法将bean转为map
    public Map<String, Object> toMap(Object bean) {
        if (bean == null) return null;
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass(), Object.class);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            Map<String, Object> map = new HashMap<>();
            for (PropertyDescriptor descriptor : propertyDescriptors) {
                Object propertyValue = descriptor.getReadMethod().invoke(bean);
                map.put(descriptor.getName(), propertyValue);
            }
            return map;
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    // 将Source中的字段复制到target中，注：需要setter和getter方法
    public <Source, Target> void copyProperties(Source source, Target target) throws IntrospectionException {
        PropertyDescriptor[] sourcePropertyDescriptors = getPropertyDescriptor(source);
        PropertyDescriptor[] targetPropertyDescriptors = getPropertyDescriptor(target);
        try {
            for (PropertyDescriptor descriptor : targetPropertyDescriptors) {
                for (PropertyDescriptor sourcePropertyDescriptor : sourcePropertyDescriptors) {
                    Object value = sourcePropertyDescriptor.getReadMethod().invoke(source);
                    descriptor.getWriteMethod().invoke(target, value);
                }
            }
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public <Source, Target> Target transfer(Source source, Class<Target> targetClass) {
        try {
            Target target = targetClass.getDeclaredConstructor().newInstance();
            PropertyDescriptor[] sourcePropertyDescriptors = getPropertyDescriptor(source);
            PropertyDescriptor[] targetPropertyDescriptors = getPropertyDescriptor(target);

            for (PropertyDescriptor descriptor : targetPropertyDescriptors) {
                for (PropertyDescriptor sourcePropertyDescriptor : sourcePropertyDescriptors) {
                    if (sourcePropertyDescriptor.getName().equals(descriptor.getName())) {
                        Object value = sourcePropertyDescriptor.getReadMethod().invoke(source);
                        descriptor.getWriteMethod().invoke(target, value);
                        break;
                    }
                }
            }
            return target;
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    private PropertyDescriptor[] getPropertyDescriptor(Object bean) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass(), Object.class);
            return beanInfo.getPropertyDescriptors();
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    // 支持自定义修改字段
    public interface Processor<Source, Target> extends BiConsumer<Source, Target> {
    }


}
