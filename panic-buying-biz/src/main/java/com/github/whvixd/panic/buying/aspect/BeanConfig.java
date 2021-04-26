package com.github.whvixd.panic.buying.aspect;

import com.github.whvixd.panic.buying.manager.RedisClientManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wangzhixiang on 2021/3/31.
 */
@Configuration
public class BeanConfig {
    @Bean
    @ConditionalOnMissingBean
    public RedisClientManager instanceRedisClientManager(){
        return new RedisClientManager();
    }

    @Bean
    public FilterRegistrationBean registerAccessFilter() {
        FilterRegistrationBean<AccessFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new AccessFilter());
        registration.setName("AccessFilter");
        registration.addUrlPatterns("/*");
        registration.setOrder(20);
        return registration;
    }
}
