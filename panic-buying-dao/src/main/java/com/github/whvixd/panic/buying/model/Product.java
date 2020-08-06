package com.github.whvixd.panic.buying.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 产品实体类
 * Created by wangzhx on 2020/3/1.
 */
@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",unique = true)
    private Long id;

    @Column(name = "product_id",unique = true)
    private String productId;

    @Column(name = "name")
    private String name;

    @Column(name = "total")
    private Integer total;

    @Column(name = "sold_number")
    private Integer soldNumber;

    @Column(name = "price")
    private Long price;

    @Column(name = "info")
    private String info;

    @Column(name = "deleted")
    private Integer deleted;

    @Column(name = "status")
    private Integer status;

    @Column(name = "current")
    private Integer current;

    @Column(name = "remark")
    private String remark;

    @Column(name = "version")
    private Long version;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_user")
    private String updateUser;

    @Column(name = "update_time")
    private Date updateTime;

}
