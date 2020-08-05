package com.github.whvixd.panic.buying.aspect;

import com.github.whvixd.panic.buying.exception.BusinessException;
import com.github.whvixd.panic.buying.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理类
 *
 * Created by wangzhixiang on 2020/8/5.
 */
@RestControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    @ExceptionHandler(BusinessException.class)
    public Result handleException(BusinessException e){
        log.error("BusinessException:{}",e);
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    public Result handleException(Throwable e){
        log.error("Exception:{}",e);
        return Result.fail();
    }
}
