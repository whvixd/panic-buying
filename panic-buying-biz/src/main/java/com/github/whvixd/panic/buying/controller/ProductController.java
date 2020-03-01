package com.github.whvixd.panic.buying.controller;

import com.github.whvixd.panic.buying.entity.ProductDTO;
import com.github.whvixd.panic.buying.model.ProductVO;
import com.github.whvixd.panic.buying.model.Result;
import com.github.whvixd.panic.buying.service.ProductService;
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
    public Result create(@RequestBody ProductVO.Arg arg) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(arg.getName());
        productDTO.setSold(arg.getSold());
        productDTO.setTotal(arg.getTotal());
        try {
            return Result.ok(productService.create(productDTO));
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }
}
