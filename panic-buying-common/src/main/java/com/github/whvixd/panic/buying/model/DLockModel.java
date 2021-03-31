package com.github.whvixd.panic.buying.model;

import com.github.whvixd.panic.buying.enums.ExpireType;
import lombok.Builder;
import lombok.Data;

/**
 * Created by wangzhixiang on 2021/3/31.
 */
@Data
@Builder
public class DLockModel {
    private String lockPrefix;
    private String lockKey;
    private String idempotentId;
    private int timeToLiveSeconds;
    private ExpireType expireType;
}
