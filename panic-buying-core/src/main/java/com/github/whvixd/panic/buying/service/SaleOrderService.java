package com.github.whvixd.panic.buying.service;

/**
 * Created by wangzhx on 2020/3/1.
 */
public interface SaleOrderService {
    boolean create(Long productId);

    void asyncCreate(Long productId);

    long count();

    long count(Long productId);
}
