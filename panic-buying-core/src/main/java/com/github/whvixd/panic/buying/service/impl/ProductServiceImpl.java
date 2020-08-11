package com.github.whvixd.panic.buying.service.impl;

import com.github.whvixd.panic.buying.entity.ProductDTO;
import com.github.whvixd.panic.buying.exception.BusinessException;
import com.github.whvixd.panic.buying.exception.CheckOversoldException;
import com.github.whvixd.panic.buying.exception.base.BusinessExceptionCode;
import com.github.whvixd.panic.buying.model.Product;
import com.github.whvixd.panic.buying.repository.ProductRepository;
import com.github.whvixd.panic.buying.service.ProductService;
import com.github.whvixd.panic.buying.util.BeanUtil;
import com.github.whvixd.panic.buying.util.AssertUtil;
import com.github.whvixd.panic.buying.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.github.whvixd.panic.buying.model.Constants.*;

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
        Product product = BeanUtil.transfer(productDTO, Product.class, (src, t) -> {
            t.setProductId(IdUtil.generate(Business.PRODUCT, Business.V_1));
            t.setDeleted(DBSymbol.TRUE);
            t.setStatusIfAbsent(Business.ON_SHELF);
            t.setCurrent(DBSymbol.CURRENT);
            t.setVersion(IdUtil.getSnowFlakeId());
        });
        return productRepository.save(product).getId();
    }

    @Override
    public ProductDTO update(String productId, String name, Integer total, Integer soldNumber, Long price, String info, String remark, String updateUser) {
        AssertUtil.checkArgs(productId);
        Product product = null;
        if (product == null) {
            log.warn("product is null,productId:{}", productId);
            throw new BusinessException(BusinessExceptionCode.PATTERN_EXCEPTION, "product is null");
        }
        if (name != null) {
            product.setName(name);
        }
        if (total != null) {
            product.setTotal(total);
        }
        if (soldNumber != null) {
            product.setSoldNumber(soldNumber);
        }
        if (price != null) {
            product.setPrice(price);
        }
        if (StringUtils.isNotBlank(info)) {
            product.setInfo(info);
        }
        if (StringUtils.isNotBlank(remark)) {
            product.setRemark(remark);
        }
        if (StringUtils.isNotBlank(updateUser)) {
            product.setUpdateUser(updateUser);
        }
        return BeanUtil.transfer(productRepository.save(product), ProductDTO.class);
    }

    @Override
    public ProductDTO update(String productId, Integer soldNumber) {
        return update(productId, null, null, soldNumber, null, null, null,null);
    }


    @Override
    public ProductDTO get(String id) {
//        Optional<Product> optional = productRepository.findById(id);
        // todo
        Optional<Product> optional = null;
        return optional.map(p -> BeanUtil.transfer(p, ProductDTO.class)).get();
    }

    @Override
    public void checkOversold(String id) {
        // TODO: 2020/3/8 添加缓存
        ProductDTO productDTO = get(id);
        if (productDTO.getTotal() <= productDTO.getSoldNumber()) {
            log.error("product:{} oversold", productDTO);
            throw new CheckOversoldException(BusinessExceptionCode.CHECK_OVERSOLD_EXCEPTION, productDTO.getName());
        }
    }

}
