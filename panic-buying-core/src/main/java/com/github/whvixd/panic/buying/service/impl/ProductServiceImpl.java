package com.github.whvixd.panic.buying.service.impl;

import com.github.whvixd.panic.buying.entity.ProductDTO;
import com.github.whvixd.panic.buying.exception.CheckOversoldException;
import com.github.whvixd.panic.buying.exception.base.BusinessExceptionCode;
import com.github.whvixd.panic.buying.model.Product;
import com.github.whvixd.panic.buying.repository.ProductRepository;
import com.github.whvixd.panic.buying.service.ProductService;
import com.github.whvixd.panic.buying.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by wangzhx on 2020/3/1.
 */
@Slf4j
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

    @Override
    public ProductDTO update(Long id, String name, Long total, Long sold) {
        Product product = productRepository.findById(id).get();
        if (name != null) {
            product.setName(name);
        }
        if (total != null) {
            product.setTotal(total);
        }
        if (sold != null) {
            product.setSold(sold);
        }
        return BeanUtil.transfer(productRepository.save(product), ProductDTO.class);
    }

    @Override
    public ProductDTO get(Long id) {
        Optional<Product> optional = productRepository.findById(id);
        return optional.map(p -> BeanUtil.transfer(p, ProductDTO.class)).get();
    }

    @Override
    public void checkOversold(Long id) {
        // TODO: 2020/3/8 添加缓存
        ProductDTO productDTO = get(id);
        if (productDTO.getTotal() <= productDTO.getSold()) {
            log.error("product:{} oversold", productDTO);
            throw new CheckOversoldException(BusinessExceptionCode.CHECK_OVERSOLD_EXCEPTION, productDTO.getName());
        }
    }

}
