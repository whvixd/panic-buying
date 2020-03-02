package com.github.whvixd.panic.buying.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by wangzhx on 2020/3/1.
 */
@Data
@Entity
@Table(name = "SALE_ORDER")
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
}
