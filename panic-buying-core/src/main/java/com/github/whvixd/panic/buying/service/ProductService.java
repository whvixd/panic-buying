package com.github.whvixd.panic.buying.service;


import com.github.whvixd.panic.buying.entity.ProductDTO;

/**
 * Created by wangzhx on 2020/3/1.
 */
public interface ProductService {
    Long create(ProductDTO productDTO);

    ProductDTO update(Long id, String name, Long total, Long sold);

    ProductDTO get(Long id);

    void checkOversold(Long id);

}
