package com.github.whvixd.panic.buying.repository;

import com.github.whvixd.panic.buying.model.SaleOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wangzhx on 2020/3/1.
 */
@Repository
public interface SaleOrderRepository extends JpaRepository<SaleOrder, Long> {

    @Override
    SaleOrder save(SaleOrder saleOrder);
}
