package com.github.whvixd.panic.buying.controller;

import com.github.whvixd.panic.buying.model.Result;
import com.github.whvixd.panic.buying.model.SaleOrderVO;
import com.github.whvixd.panic.buying.service.SaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangzhx on 2020/3/2.
 */
@RestController
@RequestMapping("/sale/order")
public class SaleOrderController {

    @Autowired
    private SaleOrderService saleOrderService;

    @PostMapping("/create")
    public Result create(@RequestBody SaleOrderVO.Arg arg) {
        try {
            return Result.ok(saleOrderService.create(arg.getProductId()));
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }
}
