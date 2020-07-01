package com.github.whvixd.panic.buying.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by wangzhx on 2020/3/1.
 */
@Data
@Entity
@Table(name = SaleOrder.Constant.TABLE_NAME)
public class SaleOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "ORDER_NAME")
    private String name;

    @Column(name = "CREATE_TIME")
    private Long createTime;

    public interface Constant{
        String TABLE_NAME="SALE_ORDER";
        // 表数量
        Integer TABLE_MOD=3;
    }
}
