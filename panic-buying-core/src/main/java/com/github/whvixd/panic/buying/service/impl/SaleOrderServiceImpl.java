package com.github.whvixd.panic.buying.service.impl;

import com.github.whvixd.panic.buying.entity.ProductDTO;
import com.github.whvixd.panic.buying.model.SaleOrder;
import com.github.whvixd.panic.buying.repository.SaleOrderRepository;
import com.github.whvixd.panic.buying.service.ProductService;
import com.github.whvixd.panic.buying.service.SaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangzhx on 2020/3/2.
 */
@Service
public class SaleOrderServiceImpl implements SaleOrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private SaleOrderRepository saleOrderRepository;

    @Override
    public Long create(Long productId) {
        ProductDTO product = productService.get(productId);
        SaleOrder saleOrder = new SaleOrder();
        saleOrder.setProductId(productId);
        saleOrder.setName(product.getName());
        saleOrder.setCreateTime(System.currentTimeMillis());
        return saleOrderRepository.save(saleOrder).getId();
    }
}
