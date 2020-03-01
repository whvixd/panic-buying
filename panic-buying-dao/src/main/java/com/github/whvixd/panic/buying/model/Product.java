package com.github.whvixd.panic.buying.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by wangzhx on 2020/3/1.
 */
@Data
@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id;

    @Column(name = "PRODUCT_NAME")
    private String name;

    @Column(name = "PRODUCT_TOTAL")
    private Long total;

    @Column(name = "PRODUCT_SOLD")
    private Long sold;

    @Column(name = "PRODUCT_VERSION")
    private Long version;
}
