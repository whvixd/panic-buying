package com.github.whvixd.panic.buying.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

/**
 * Created by wangzhx on 2020/3/1.
 */
public interface CreateSaleOrderVO {
    @Data
    class Arg {
        private String name;
        @NotBlank(message = "产品id不为空")
        private String productId;

        @Min(value = 0, message = "支付金额大于零")
        private Long payAmount;

        private String info;

        @NotBlank(message = "用户id不为空")
        private String userId;
        private String remark;

        @Min(value = 1, message = "分期大于一")
        private Integer totalDuration;

        private String createUser;
    }
}
