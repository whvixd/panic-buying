package com.github.whvixd.panic.buying.manager;

import com.github.whvixd.panic.buying.util.InvokeTask;
import org.junit.Test;

import java.util.stream.LongStream;

/**
 * Created by wangzhx on 2020/3/6.
 */
public class BlockQueueManagerTest {
    @Test
    public void test() {
        BlockQueueManager blockQueueManager = new BlockQueueManager();

        LongStream.range(0L, 10L).forEach(e ->
                InvokeTask.newInstance(() -> blockQueueManager.put(e)).start());

        LongStream.range(0L, 10L).forEach(e ->
                InvokeTask.newInstance(() -> System.out.println(blockQueueManager.pull())).start());

    }

    public static void main(String[] args) {
        BlockQueueManager blockQueueManager = new BlockQueueManager();

        LongStream.range(0L, 10L).forEach(e ->
                        blockQueueManager.put(e));
//                InvokeTask.newInstance(() -> blockQueueManager.put(e)).start());

        LongStream.range(0L, 10L).forEach(e ->
                        System.out.println(blockQueueManager.pull()));
//                InvokeTask.newInstance(() -> System.out.println(blockQueueManager.pull())).start());
    }
}
