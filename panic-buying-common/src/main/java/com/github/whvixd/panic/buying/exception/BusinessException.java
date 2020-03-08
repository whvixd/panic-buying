package com.github.whvixd.panic.buying.exception;

/**
 * Created by wangzhx on 2020/3/8.
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String errorMessage) {
        super(errorMessage);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

    public BusinessException() {
        super();
    }
}
