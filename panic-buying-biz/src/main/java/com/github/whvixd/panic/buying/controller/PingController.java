package com.github.whvixd.panic.buying.controller;

import com.github.whvixd.panic.buying.model.Result;
import com.github.whvixd.panic.buying.model.base.BaseResult;
import org.springframework.web.bind.annotation.GetMapping;
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
}
