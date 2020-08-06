package com.github.whvixd.panic.buying.aspect;

import com.github.whvixd.panic.buying.manager.CacheManager;
import com.github.whvixd.panic.buying.model.SaleOrderVO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * Created by wangzhx on 2020/3/2.
 */
@Slf4j
@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SaleOrderInterfaceAspect {

    @Autowired
    private CacheManager cacheManager;

    @Value("${SaleOrderInterface.minNumber}")
    private int min;
    @Value("${SaleOrderInterface.maxNumber}")
    private int max;

    @Before("execution(public * com.github.whvixd.panic.buying.controller.SaleOrderController.create(*))")
    public void lock(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args[0] instanceof SaleOrderVO.Arg) {
            SaleOrderVO.Arg arg = (SaleOrderVO.Arg) args[0];
            Long productId = arg.getProductId();
            int count = cacheManager.add(productId);
            // 堵塞线程数
            log.info("sale order interface over limit:[{},{}],current count:{}", min, max, count);
            if (count < min || count > max) {
                throw new RuntimeException("sale order interface over limit!");
            }
        }
    }

    @After("execution(public * com.github.whvixd.panic.buying.controller.SaleOrderController.create(*))")
    public void unLock(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args[0] instanceof SaleOrderVO.Arg) {
            SaleOrderVO.Arg arg = (SaleOrderVO.Arg) args[0];
            Long productId = arg.getProductId();
            int count = cacheManager.subtract(productId);
            log.info("subtract count:{}", count);
        }
    }
}
