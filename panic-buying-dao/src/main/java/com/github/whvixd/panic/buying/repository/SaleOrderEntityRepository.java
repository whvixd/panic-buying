package com.github.whvixd.panic.buying.repository;

import com.github.whvixd.panic.buying.exception.BusinessException;
import com.github.whvixd.panic.buying.exception.base.BusinessExceptionCode;
import com.github.whvixd.panic.buying.model.SaleOrder;
import com.github.whvixd.panic.buying.util.AssertUtil;
import com.github.whvixd.panic.buying.util.DBUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;

/**
 * Created by wangzhx on 2020/3/1.
 */
@Repository
@Slf4j
public class SaleOrderEntityRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Integer countById(String productId) {
        AssertUtil.checkArgs(productId);

        String sqlFormat = "select count(PRODUCT_ID) from %s where PRODUCT_ID=%s";
        // 获取分表名称
        String tableName = DBUtil.getTableName(String.valueOf(productId), SaleOrder.Constant.TABLE_NAME, SaleOrder.Constant.TABLE_MOD);
        String sql = String.format(sqlFormat, tableName, productId);
        log.info("SaleOrderEntityRepository.countById sql:{}", sql);
        try {
            Query query = entityManager.createNativeQuery(sql);
            return ((BigInteger) query.getSingleResult()).intValue();
        } catch (Exception e) {
            log.error("SaleOrderEntityRepository.countById error, productId:{}", productId, e);
            throw new BusinessException(BusinessExceptionCode.SYSTEM_ERROR);
        }
    }

    public boolean save(SaleOrder saleOrder) {
        String productId = saleOrder.getProductId();
        AssertUtil.checkArgs(productId);

        String sqlFormat = "insert into %s (PRODUCT_ID,ORDER_NAME,CREATE_TIME) values(?,?,?)";
        // 获取分表名称
        String tableName = DBUtil.getTableName(String.valueOf(productId), SaleOrder.Constant.TABLE_NAME, SaleOrder.Constant.TABLE_MOD);
        String sql = String.format(sqlFormat, tableName);
        log.info("SaleOrderEntityRepository.save sql:{}", sql);
        try {
            entityManager.createNativeQuery(sql)
                    .setParameter(1,productId)
                    .setParameter(2,saleOrder.getName())
                    .setParameter(3,saleOrder.getCreateTime())
                    .executeUpdate();
        } catch (Exception e) {
            log.error("SaleOrderEntityRepository.save error, productId:{}", productId, e);
            throw new BusinessException(BusinessExceptionCode.SYSTEM_ERROR);
        }
        return true;
    }

}
