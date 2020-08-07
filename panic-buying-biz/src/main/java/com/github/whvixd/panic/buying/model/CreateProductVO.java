package com.github.whvixd.panic.buying.model;

import lombok.Data;

/**
 * Created by wangzhx on 2020/3/1.
 */
public interface CreateProductVO {
    @Data
    class Arg {
        private String name;

        private Integer total;

        private Long price;

        private String info;

        private Integer current;

        private String remark;

    }
}
