package com.github.whvixd.panic.buying.processor.producer;

import com.github.whvixd.panic.buying.manager.BlockQueueManager;
import com.github.whvixd.panic.buying.util.Lock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by wangzhx on 2020/3/6.
 */
@Component
public class SaleOrderProducer {
    @Autowired
    private BlockQueueManager blockQueueManager;

    private final Lock lock = Lock.instance;

    @Async
    public void send(Object message) {
        synchronized (lock) {
            blockQueueManager.put(message);
            lock.notifying();
        }
    }
}
