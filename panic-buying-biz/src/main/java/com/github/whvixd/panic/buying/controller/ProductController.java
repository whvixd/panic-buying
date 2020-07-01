package com.github.whvixd.panic.buying.controller;

import com.github.whvixd.panic.buying.entity.ProductDTO;
import com.github.whvixd.panic.buying.model.ProductVO;
import com.github.whvixd.panic.buying.model.Result;
import com.github.whvixd.panic.buying.service.ProductService;
import com.github.whvixd.panic.buying.model.annotation.RateLimit;
import com.github.whvixd.panic.buying.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangzhx on 2020/3/1.
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("create")
    @RateLimit
    public Result create(@RequestBody ProductVO.Arg arg) {
        ProductDTO productDTO = BeanUtil.transfer(arg, ProductDTO.class);
        try {
            return Result.ok(productService.create(productDTO));
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }

    // TODO: 2020/7/1 添加查询接口
}
