package com.github.whvixd.panic.buying.service.impl;

import com.github.whvixd.panic.buying.entity.ProductDTO;
import com.github.whvixd.panic.buying.model.SaleOrder;
import com.github.whvixd.panic.buying.processor.producer.SaleOrderProducer;
import com.github.whvixd.panic.buying.repository.SaleOrderRepository;
import com.github.whvixd.panic.buying.service.ProductService;
import com.github.whvixd.panic.buying.service.SaleOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangzhx on 2020/3/2.
 */
@Slf4j
@Service
public class SaleOrderServiceImpl implements SaleOrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private SaleOrderRepository saleOrderRepository;

    @Autowired
    private SaleOrderProducer saleOrderProducer;

    @Override
    public Long create(Long productId) {
        ProductDTO product = productService.get(productId);
        if (product.getTotal() <= product.getSold()) {
            log.error("product:{} oversold", product);
            // TODO: 2020/3/7 业务异常
            throw new RuntimeException(String.format("%s oversold", product.getName()));
        }
        SaleOrder saleOrder = new SaleOrder();
        saleOrder.setProductId(productId);
        saleOrder.setName(product.getName());
        saleOrder.setCreateTime(System.currentTimeMillis());
        return saleOrderRepository.save(saleOrder).getId();
    }

    @Override
    public void asyncCreate(Long productId) {
        //校验不准确，收到消息的地方再次校验
        productService.checkOversold(productId);
        saleOrderProducer.send(productId);
    }

    @Override
    public long count() {
        return saleOrderRepository.count();
    }

    @Override
    public long count(Long productId) {
        return saleOrderRepository.countById(productId);
    }
}