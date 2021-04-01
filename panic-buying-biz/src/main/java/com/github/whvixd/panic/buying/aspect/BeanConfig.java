package com.github.whvixd.panic.buying.aspect;

import com.github.whvixd.panic.buying.manager.DLockClientManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wangzhixiang on 2021/3/31.
 */
@Configuration
public class BeanConfig {
    @Bean
    @ConditionalOnMissingBean
    public DLockClientManager instanceRedisClientManager(){
        return new DLockClientManager();
    }
}
