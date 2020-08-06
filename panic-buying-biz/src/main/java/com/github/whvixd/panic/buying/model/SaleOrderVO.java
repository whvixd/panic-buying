package com.github.whvixd.panic.buying.model;

import lombok.Data;

/**
 * Created by wangzhx on 2020/3/1.
 */
public interface SaleOrderVO {
    @Data
    class Arg {
        private String productId;
    }
}
