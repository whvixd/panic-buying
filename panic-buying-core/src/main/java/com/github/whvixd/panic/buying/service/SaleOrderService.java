package com.github.whvixd.panic.buying.service;

import com.github.whvixd.panic.buying.entity.SaleOrderDTO;

/**
 * Created by wangzhx on 2020/3/1.
 */
public interface SaleOrderService {
    boolean create(SaleOrderDTO saleOrderDTO);

    void asyncCreate(SaleOrderDTO saleOrderDTO);

    long count();

    long count(String productId);
}
