package com.github.whvixd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class PanicBuyingBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(PanicBuyingBizApplication.class, args);
    }
}
