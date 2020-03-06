package com.github.whvixd.panic.buying.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by wangzhx on 2020/3/5.
 */
@Component
@Slf4j
public class BlockQueueManager {

    private BlockingQueue<Object> blockingQueue = new LinkedBlockingDeque<>(100);

    public void offer(Object o) {
        blockingQueue.offer(o);
    }

    public void put(Object o) {
        try {
            blockingQueue.put(o);
        } catch (InterruptedException e) {
            log.error("BlockQueueManager error ", e);
            throw new RuntimeException(e);
        }
    }

    public Object pull() {
        try {
            return blockingQueue.take();
        } catch (InterruptedException e) {
            log.error("BlockQueueManager error ", e);
            throw new RuntimeException(e);
        }

    }

    public boolean isEmpty() {
        return blockingQueue.isEmpty();
    }

}
