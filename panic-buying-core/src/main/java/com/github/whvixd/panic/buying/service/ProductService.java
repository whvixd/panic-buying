package com.github.whvixd.panic.buying.service;


import com.github.whvixd.panic.buying.entity.ProductDTO;

/**
 * Created by wangzhx on 2020/3/1.
 */
public interface ProductService {
    Long create(ProductDTO productDTO);

    ProductDTO update(String productId, String name, Integer total, Integer soldNumber);

    ProductDTO get(String productId);

    void checkOversold(String id);

}
