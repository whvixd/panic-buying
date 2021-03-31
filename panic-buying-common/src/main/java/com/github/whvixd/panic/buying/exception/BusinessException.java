package com.github.whvixd.panic.buying.exception;

import com.github.whvixd.panic.buying.exception.base.BusinessExceptionCode;

import java.text.MessageFormat;

/**
 * Created by wangzhx on 2020/3/8.
 */
public class BusinessException extends RuntimeException {
    int errorCode;

    public BusinessException(String errorMessage) {
        super(errorMessage);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

    public BusinessException(String errorMessage, Object... params) {
        this(MessageFormat.format(errorMessage, params));
    }

    public BusinessException() {
        super();
    }

    public BusinessException(BusinessExceptionCode code) {
        super(code.getErrorMessage());
        this.errorCode = code.getErrorCode();
    }

}
