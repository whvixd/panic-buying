package com.github.whvixd.panic.buying.model.base;

import lombok.Data;

/**
 * Created by wangzhixiang on 2020/8/6.
 */
@Data
public class HttpHeaders extends org.springframework.http.HttpHeaders {
    // todo 放本地线程中
    private String userName = getFirst("userName");
    private String userId = getFirst("userId");

}
