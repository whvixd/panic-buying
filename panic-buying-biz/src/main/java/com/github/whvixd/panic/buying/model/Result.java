package com.github.whvixd.panic.buying.model;

import com.github.whvixd.panic.buying.model.base.BaseResult;
import com.github.whvixd.panic.buying.model.base.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by wangzhx on 2020/3/1.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Result extends BaseResult{

    public Result code(ResultCode resultCode) {
        this.code = resultCode.code;
        return this;
    }

    public Result message(String message) {
        this.message = message;
        return this;
    }

    public Result data(Object data) {
        this.data = data;
        return this;
    }

    private static final String DEFAULT_SUCCESS_MESSAGE = "success";

    public static Result ok() {
        return new Result()
                .code(ResultCode.SUCCESS)
                .message(DEFAULT_SUCCESS_MESSAGE);
    }

    public static Result ok(Object data) {
        return new Result()
                .code(ResultCode.SUCCESS)
                .message(DEFAULT_SUCCESS_MESSAGE)
                .data(data);
    }

    public static Result fail(String message) {
        return new Result()
                .code(ResultCode.INTERNAL_SERVER_ERROR)
                .message(message);
    }

    public static Result fail() {
        return new Result()
                .code(ResultCode.INTERNAL_SERVER_ERROR)
                .message("service error");
    }

}
