package com.github.whvixd.panic.buying.exception;

import com.github.whvixd.panic.buying.exception.base.BusinessExceptionCode;

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

    public BusinessException() {
        super();
    }

    public BusinessException(BusinessExceptionCode code) {
        super(code.getErrorMessage());
        this.errorCode=code.getErrorCode();
    }

}
