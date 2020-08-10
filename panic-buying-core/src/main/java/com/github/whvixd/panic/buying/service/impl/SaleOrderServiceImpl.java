package com.github.whvixd.panic.buying.service.impl;

import com.github.whvixd.panic.buying.entity.ProductDTO;
import com.github.whvixd.panic.buying.entity.SaleOrderDTO;
import com.github.whvixd.panic.buying.exception.CheckOversoldException;
import com.github.whvixd.panic.buying.exception.base.BusinessExceptionCode;
import com.github.whvixd.panic.buying.model.SaleOrder;
import com.github.whvixd.panic.buying.processor.producer.SaleOrderProducer;
import com.github.whvixd.panic.buying.repository.SaleOrderEntityRepository;
import com.github.whvixd.panic.buying.repository.SaleOrderRepository;
import com.github.whvixd.panic.buying.service.ProductService;
import com.github.whvixd.panic.buying.service.SaleOrderService;
import com.github.whvixd.panic.buying.util.BeanUtil;
import com.github.whvixd.panic.buying.util.FastJsonUtil;
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
    public boolean create(SaleOrderDTO saleOrderDTO) {
        // 更新创建售卖总数
        String productId = saleOrderDTO.getProductId();
        long count = count(productId);
        productService.update(productId, Integer.valueOf(String.valueOf(count)));

        // 需要再校验吗？缓存？
        ProductDTO product = productService.get(productId);
        // 超卖抛出异常
        if (product.getTotal() <= product.getSoldNumber()) {
            log.error("product:{} oversold", product);
            throw new CheckOversoldException(BusinessExceptionCode.CHECK_OVERSOLD_EXCEPTION, product.getName());
        }

        SaleOrder saleOrder = BeanUtil.transfer(saleOrderDTO, SaleOrder.class, (r, t) -> {
            Date now = new Date();
            t.setCreateTime(now);
            t.setUpdateTime(now);
        });
        // 创建订单
        return saleOrderEntityRepository.save(saleOrder);
    }

    @Override
    // todo 添加缓存校验机制，提供接口，在售卖之前对产品售卖数量预热
    public void asyncCreate(SaleOrderDTO saleOrderDTO) {
        //校验不准确，收到消息的地方再次校验
        productService.checkOversold(saleOrderDTO.getProductId());
        saleOrderProducer.send(FastJsonUtil.toJson(saleOrderDTO));
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
