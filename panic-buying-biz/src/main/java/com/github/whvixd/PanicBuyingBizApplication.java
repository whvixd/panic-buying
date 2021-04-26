package com.github.whvixd;

import com.github.whvixd.panic.buying.aspect.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication//(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class PanicBuyingBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(PanicBuyingBizApplication.class, args);
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
