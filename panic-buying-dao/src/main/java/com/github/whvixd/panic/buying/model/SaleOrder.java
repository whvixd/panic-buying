package com.github.whvixd.panic.buying.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * 订单实体
 * Created by wangzhx on 2020/3/1.
 */
@Data
@Entity
@Table(name = SaleOrder.Constant.TABLE_NAME)
public class SaleOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",unique = true)
    private Long id;

    @Column(name = "order_id",unique = true)
    private String orderId;

    @Column(name = "name")
    private String name;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "pay_amount")
    private Long payAmount;

    @Column(name = "deleted")
    private Integer deleted;

    @Column(name = "info")
    private String info;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "remark")
    private String remark;

    @Column(name = "total_duration")
    private Integer totalDuration;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_user")
    private String updateUser;

    @Column(name = "update_time")
    private Date updateTime;



    public interface Constant{
        String TABLE_NAME="sale_order";
        // 表数量
        Integer TABLE_MOD=3;
    }
}
