package com.github.whvixd.panic.buying.processor.consumer;

import com.github.whvixd.panic.buying.manager.BlockQueueManager;
import com.github.whvixd.panic.buying.service.SaleOrderService;
import com.github.whvixd.panic.buying.util.InvokeTask;
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

    @Autowired
    private BlockQueueManager blockQueueManager;

    @Autowired
    private SaleOrderService saleOrderService;

    @PostConstruct
    public void start() {
        InvokeTask.newInstance(this::create).invokeTaskName("SaleOrderConsumerThread").start();
    }

    private void create() {
        // TODO: 2020/3/6 需要优化，cpu占用太高
        for (;;) {
            if (blockQueueManager.isEmpty()) {
                continue;
            }
            Object o = blockQueueManager.pull();
            Long produceId;
            if (o instanceof Long) {
                produceId = (Long) o;
            } else {
                log.warn("class not match,element class:{}", o.getClass().getName());
                continue;
            }
            saleOrderService.create(produceId);
            log.info("consumer create success,produceId:{}", produceId);
        }
    }
}
