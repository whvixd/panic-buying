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
    public boolean create(Long productId) {
        // 更新创建售卖总数
        productService.update(productId, null, null, count(productId));
        ProductDTO product = productService.get(productId);
        // 超卖抛出异常
        if (product.getTotal() <= product.getSold()) {
            log.error("product:{} oversold", product);
            throw new CheckOversoldException(BusinessExceptionCode.CHECK_OVERSOLD_EXCEPTION, product.getName());
        }
        SaleOrder saleOrder = new SaleOrder();
        saleOrder.setProductId(productId);
        saleOrder.setName(product.getName());
        saleOrder.setCreateTime(System.currentTimeMillis());
        // 创建订单
        return saleOrderEntityRepository.save(saleOrder);
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
        return saleOrderEntityRepository.countById(productId);
    }
}
