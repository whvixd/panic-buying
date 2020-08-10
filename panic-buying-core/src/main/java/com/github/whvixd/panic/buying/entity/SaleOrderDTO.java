package com.github.whvixd.panic.buying.entity;

import lombok.Data;

/**
 * Created by wangzhx on 2020/3/1.
 */
@Data
public class SaleOrderDTO {
    private String name;
    private String productId;
    private Long payAmount;
    private String info;
    private String userId;
    private String remark;
    private Integer totalDuration;
    private String createUser;

}
