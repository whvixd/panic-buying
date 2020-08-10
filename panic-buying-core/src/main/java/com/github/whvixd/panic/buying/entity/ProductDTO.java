package com.github.whvixd.panic.buying.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by wangzhx on 2020/3/1.
 */
@Data
public class ProductDTO {
    private String productId;

    private String name;

    private Integer total;

    private Integer soldNumber;

    private Long price;

    private String info;

    private Integer deleted;

    private Integer status;

    // 暂未用到，原计划按version更新数据，更新soldNumber过频繁
    private Integer current;

    // 暂未用到
    private String remark;

    // 暂未用到
    private Long version;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;


}
