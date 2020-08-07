package com.github.whvixd.panic.buying.model;

/**
 * Created by wangzhx on 2020/3/8.
 */
public interface Constants {

    interface Charset {
        String UTF_8 = "utf-8";
    }

    interface Symbol {
        String ROD = "-";
        String UNDERLINE = "_";
    }

    interface Business {
        String PRODUCT = "product";
        String V_1 = "v1";

        // 下架
        int OFF_SHELF = 0;

        // 上架
        int ON_SHELF = 1;

    }

    interface DBSymbol {
        int TRUE = 1;
        int FLASE = 0;

        // 当前数据
        int CURRENT = 1;

        // 实例数据
        int HISTORY = 0;

    }
}
