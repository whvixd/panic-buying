package com.github.whvixd.panic.buying.controller;

import com.github.whvixd.panic.buying.model.Result;
import com.github.whvixd.panic.buying.model.base.BaseResult;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangzhx on 2020/3/1.
 */
@RestController
public class PingController {

    @GetMapping("/ping")
    public BaseResult ping() {
        return Result.ok("hello world!");
    }

    // curl -X GET 'http://localhost:8080/test?name=test&nickName=test'
    @RequestMapping("/test")
    public BaseResult test(TestA testA,TestB testB){
        if("test".equals(testA.getName())&&"test".equals(testB.getNickName())){
            return Result.ok("hello world!");
        }
        return Result.fail("hello world!");

    }

    @Data
    static class TestA{
        String id;
        String name;
    }

    @Data
    static class TestB{
        String nickName;
    }
}
