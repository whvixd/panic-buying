package com.github.whvixd.panic.buying.controller;

import com.github.whvixd.panic.buying.entity.SaleOrderDTO;
import com.github.whvixd.panic.buying.model.Result;
import com.github.whvixd.panic.buying.model.CreateSaleOrderVO;
import com.github.whvixd.panic.buying.model.annotation.RateLimit;
import com.github.whvixd.panic.buying.service.SaleOrderService;
import com.github.whvixd.panic.buying.util.BeanUtil;
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
    @RateLimit(permitsPerSecond = 10)
    public Result create(@RequestBody CreateSaleOrderVO.Arg arg) {
        try {
            saleOrderService.asyncCreate(BeanUtil.transfer(arg, SaleOrderDTO.class));
            return Result.ok();
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }

    // TODO: 2020/7/1 添加查询接口
}
