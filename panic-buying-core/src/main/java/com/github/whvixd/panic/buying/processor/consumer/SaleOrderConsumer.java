package com.github.whvixd.panic.buying.processor.consumer;

import com.github.whvixd.panic.buying.manager.BlockQueueManager;
import com.github.whvixd.panic.buying.service.SaleOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
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
        for (;;) {
            Object o = blockQueueManager.pull();
            Long produceId;
            if (o instanceof Long) {
                produceId = (Long) o;
            } else {
                log.warn("class not match,element class:{}", o.getClass().getName());
                continue;
            }
            saleOrderService.create(produceId);
        }
    }
}
