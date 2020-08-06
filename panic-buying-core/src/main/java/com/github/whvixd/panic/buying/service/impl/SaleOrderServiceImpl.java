package com.github.whvixd.panic.buying.service.impl;

import com.github.whvixd.panic.buying.entity.ProductDTO;
import com.github.whvixd.panic.buying.exception.CheckOversoldException;
import com.github.whvixd.panic.buying.exception.base.BusinessExceptionCode;
import com.github.whvixd.panic.buying.model.SaleOrder;
import com.github.whvixd.panic.buying.processor.producer.SaleOrderProducer;
import com.github.whvixd.panic.buying.repository.SaleOrderEntityRepository;
import com.github.whvixd.panic.buying.repository.SaleOrderRepository;
import com.github.whvixd.panic.buying.service.ProductService;
import com.github.whvixd.panic.buying.service.SaleOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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

    @Autowired
    private SaleOrderEntityRepository saleOrderEntityRepository;

    @Override
    @Transactional
    // todo 实现通过注解添加 分布式锁，请求头添加userId
    public boolean create(String productId) {
        // 更新创建售卖总数

        // todo 修改逻辑
        long count = count(productId);
        productService.update(productId, null, null, 1);
        ProductDTO product = productService.get(productId);
        // 超卖抛出异常
        if (product.getTotal() <= product.getSoldNumber()) {
            log.error("product:{} oversold", product);
            throw new CheckOversoldException(BusinessExceptionCode.CHECK_OVERSOLD_EXCEPTION, product.getName());
        }
        SaleOrder saleOrder = new SaleOrder();
        saleOrder.setProductId(productId);
        saleOrder.setName(product.getName());
        saleOrder.setCreateTime(new Date());
        // 创建订单
        return saleOrderEntityRepository.save(saleOrder);
    }

    @Override
    public void asyncCreate(String productId) {
        //校验不准确，收到消息的地方再次校验
        productService.checkOversold(productId);
        saleOrderProducer.send(productId);
    }

    @Override
    public long count() {
        return saleOrderRepository.count();
    }

    @Override
    public long count(String productId) {
        return saleOrderEntityRepository.countById(productId);
    }
}
