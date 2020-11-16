package com.github.whvixd.panic.buying.controller;

import com.github.whvixd.panic.buying.model.Result;
import com.github.whvixd.panic.buying.model.base.BaseResult;
import com.github.whvixd.panic.buying.util.FastJsonUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by wangzhx on 2020/3/1.
 */
@RestController
@Slf4j
public class PingController {

    @GetMapping("/ping")
    public BaseResult ping() {
        return Result.ok("hello world!");
    }

    // TODO: 2020/6/4 源码阅读，解析方式，添加到博客中
    // curl -X GET 'http://localhost:8080/test?name=test&nickName=test'
    // 从 入口 FrameworkServlet
    @RequestMapping("/test")
    public BaseResult test(TestA testA, TestB testB) {
        if ("test".equals(testA.getName()) && "test".equals(testB.getNickName())) {
            return Result.ok("hello world!");
        }
        return Result.fail("hello world!");

    }


    @RequestMapping(value = "/test/map", method = RequestMethod.POST)
    public BaseResult test(HttpServletRequest req, @RequestBody Map<String, Object> map) {
        log.info(FastJsonUtil.toJson(map));
        return Result.fail(FastJsonUtil.toJson(map));

    }

    @Data
    static class TestA {
        String id;
        String name;
    }

    @Data
    static class TestB {
        String nickName;
    }
}
