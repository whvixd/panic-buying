package com.github.whvixd.panic.buying.util;

import lombok.Getter;
import lombok.Setter;

import javax.swing.plaf.nimbus.State;

/**
 * Created by wangzhx on 2020/3/8.
 */
public enum Lock {
    instance;

    @Getter
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
