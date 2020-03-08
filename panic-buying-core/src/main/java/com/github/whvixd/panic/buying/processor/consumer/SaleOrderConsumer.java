package com.github.whvixd.panic.buying.processor.consumer;

import com.github.whvixd.panic.buying.manager.BlockQueueManager;
import com.github.whvixd.panic.buying.service.ProductService;
import com.github.whvixd.panic.buying.service.SaleOrderService;
import com.github.whvixd.panic.buying.util.InvokeTask;
import com.github.whvixd.panic.buying.util.Lock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * todo 基于socket，添加网络通讯方式
 * Created by wangzhx on 2020/3/6.
 */
@Component
@Slf4j
public class SaleOrderConsumer {
    private final Lock lock = Lock.instance;
    @Autowired
    private BlockQueueManager blockQueueManager;

    @Autowired
    private SaleOrderService saleOrderService;

    @Autowired
    private ProductService productService;

    @PostConstruct
    public void start() {
        InvokeTask.newInstance(this::create).invokeTaskName("SaleOrderConsumerThread").start();
    }

    private void create() {
        synchronized (lock) {
            for (;;) {
                try {
                    if (blockQueueManager.isEmpty()) {
                        lock.waiting();
                    }
                    Object o = blockQueueManager.pull();
                    Long productId;
                    if (o instanceof Long) {
                        productId = (Long) o;
                    } else {
                        log.warn("class not match,element class:{}", o.getClass().getName());
                        continue;
                    }
                    saleOrderService.create(productId);
                    log.info("consumer create success,productId:{}", productId);
                } catch (Exception e) {
                    log.warn("SaleOrderConsumer thread waiting from ", e);
                    try {
                        lock.waiting();
                    } catch (InterruptedException interruptedException) {
                        log.error("SaleOrderConsumer InterruptedException ", interruptedException);
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
