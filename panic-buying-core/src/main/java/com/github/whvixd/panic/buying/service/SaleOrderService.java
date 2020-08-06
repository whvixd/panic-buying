package com.github.whvixd.panic.buying.service;

/**
 * Created by wangzhx on 2020/3/1.
 */
public interface SaleOrderService {
    boolean create(String productId);

    void asyncCreate(String productId);

    long count();

    long count(String productId);
}
