package com.github.whvixd.panic.buying.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by wangzhx on 2020/3/5.
 */
@Component
@Slf4j
public class BlockQueueManager {

    private final BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(100);

    public void offer(String message) {
        blockingQueue.offer(message);
    }

    public void put(String message) {
        try {
            blockingQueue.put(message);
        } catch (InterruptedException e) {
            log.error("BlockQueueManager error ", e);
            throw new RuntimeException(e);
        }
    }

    public String pull() {
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
