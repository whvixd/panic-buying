package com.github.whvixd.panic.buying.service.impl;

import com.github.whvixd.panic.buying.entity.ProductDTO;
import com.github.whvixd.panic.buying.model.Product;
import com.github.whvixd.panic.buying.repository.ProductRepository;
import com.github.whvixd.panic.buying.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangzhx on 2020/3/1.
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Long create(ProductDTO productDTO) {
        Product product = new Product();
        product.setTotal(productDTO.getTotal());
        product.setName(productDTO.getName());
        product.setSold(productDTO.getSold());
        return productRepository.save(product).getId();
    }
}
