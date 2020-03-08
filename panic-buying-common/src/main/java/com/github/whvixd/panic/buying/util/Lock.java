package com.github.whvixd.panic.buying.util;

import lombok.Setter;

/**
 * Created by wangzhx on 2020/3/8.
 */
public enum Lock {
    instance;

    @Setter
    private Thread.State threadState;

    public void waiting() throws InterruptedException {
        setThreadState(Thread.State.WAITING);
        wait();
    }

    public void notifying() {
        if (threadState == Thread.State.WAITING) {
            setThreadState(Thread.State.RUNNABLE);
            notify();
        }
    }
}
