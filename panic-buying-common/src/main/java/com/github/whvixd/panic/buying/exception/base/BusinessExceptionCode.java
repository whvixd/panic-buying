package com.github.whvixd.panic.buying.exception.base;

/**
 * 异常信息
 * Created by wangzhx on 2020/03/08.
 */
public enum BusinessExceptionCode {

    /**
     * exception
     */
    CHECK_OVERSOLD_EXCEPTION(4_01,"%s oversold"),

    /**
     * error
     */
    ARG_VALIDATE_ERROR(5_01, "参数校验错误"),
    SYSTEM_ERROR(5_02, "系统异常");

    private int errorCode;
    private String errorMessage;

    BusinessExceptionCode(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
