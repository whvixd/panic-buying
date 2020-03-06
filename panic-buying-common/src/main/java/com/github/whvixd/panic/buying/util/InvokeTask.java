package com.github.whvixd.panic.buying.util;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * Created by wangzhx on 2019/7/12.
 */
public class InvokeTask extends Thread {
    @Getter
    private String invokeTaskName;
    private volatile static AtomicInteger count = new AtomicInteger();

    private InvokeTask() {
        if (invokeTaskName == null || invokeTaskName.length() == 0) {
            setInvokeTaskName("InvokeTask-" + count.incrementAndGet());
        }
    }

    public static <T> InvokeTask newInstance(T message, Consumer<T> consumer) {
        return new InvokeTask() {
            @Override
            public void run() {
                consumer.accept(message);
            }
        };
    }

    public static InvokeTask newInstance(AgentConsumer agentConsumer) {
        return new InvokeTask() {
            @Override
            public void run() {
                agentConsumer.accept();
            }
        };
    }

    private void setInvokeTaskName(String name) {
        super.setName(name);
        this.invokeTaskName = name;
    }

    public InvokeTask invokeTaskName(String name) {
        super.setName(name);
        this.invokeTaskName = name;
        return this;
    }
}
