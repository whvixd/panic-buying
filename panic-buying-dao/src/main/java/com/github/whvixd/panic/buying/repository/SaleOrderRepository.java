package com.github.whvixd.panic.buying.repository;

import com.github.whvixd.panic.buying.model.SaleOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wangzhx on 2020/3/1.
 */
@Repository
public interface SaleOrderRepository extends JpaRepository<SaleOrder, Long> {

    @Override
    @Transactional
    SaleOrder save(SaleOrder saleOrder);

    @Query("select count(productId) from SaleOrder where productId=:productId")
    long countById(@Param("productId") Long productId);
}
