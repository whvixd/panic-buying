package com.github.whvixd.panic.buying.exception;

import com.github.whvixd.panic.buying.exception.base.BusinessExceptionCode;

import java.text.MessageFormat;

/**
 * Created by wangzhx on 2020/3/8.
 */
public class CheckOversoldException extends BusinessException {

    public CheckOversoldException(BusinessExceptionCode exceptionCode, Object... args) {
        this(exceptionCode.getErrorCode(), exceptionCode.getErrorMessage(), args);
    }


    public CheckOversoldException(int errorCode, String errorMessage, Object... args) {
        super(MessageFormat.format(errorMessage, args));
        this.errorCode = errorCode;
    }

    public CheckOversoldException(int errorCode, String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        this.errorCode = errorCode;
    }
}
