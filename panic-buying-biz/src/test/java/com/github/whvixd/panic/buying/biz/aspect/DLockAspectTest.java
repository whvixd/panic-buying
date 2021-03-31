package com.github.whvixd.panic.buying.biz.aspect;

import com.github.whvixd.panic.buying.controller.PingController;
import com.github.whvixd.panic.buying.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by wangzhixiang on 2021/3/31.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DLockAspectTest {
    @Autowired
    private PingController pingController;

    @Test(expected = BusinessException.class)
    public void lockAroundTest() throws InterruptedException {
        pingController.ping();
        log.info("-------");
        pingController.ping();
        Thread.sleep(20000L);
    }

}
