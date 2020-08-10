package com.github.whvixd.panic.buying.processor.consumer;

import com.github.whvixd.panic.buying.entity.SaleOrderDTO;
import com.github.whvixd.panic.buying.manager.BlockQueueManager;
import com.github.whvixd.panic.buying.service.SaleOrderService;
import com.github.whvixd.panic.buying.util.FastJsonUtil;
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
                    String message = blockQueueManager.pull();
                    saleOrderService.create(FastJsonUtil.fromJson(message, SaleOrderDTO.class));
                    log.info("consumer create success,product:{}", message);
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
