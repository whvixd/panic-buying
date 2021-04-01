package com.github.whvixd.panic.buying.aspect;

import cn.hutool.crypto.SecureUtil;
import com.github.whvixd.panic.buying.enums.ExpireType;
import com.github.whvixd.panic.buying.exception.BusinessException;
import com.github.whvixd.panic.buying.exception.base.BusinessExceptionCode;
import com.github.whvixd.panic.buying.manager.DLockManager;
import com.github.whvixd.panic.buying.model.DLockModel;
import com.github.whvixd.panic.buying.model.annotation.DKeyAlias;
import com.github.whvixd.panic.buying.model.annotation.DLock;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.stream.IntStream;

/**
 * Created by wangzhixiang on 2021/3/31.
 */
@Aspect
@Component
@Slf4j
public class DLockAspect {

    @Autowired
    private DLockManager dLockManager;


    @Pointcut("@annotation(com.github.whvixd.panic.buying.model.annotation.DLock)")
    public void dLockAspect() {
    }

    @Around("dLockAspect()")
    public Object lockAround(ProceedingJoinPoint proceeding) {
        DLockModel dLockModel = this.getDLockModel(proceeding);
        // 获取分布式锁
        boolean locked = this.getDLock(dLockModel);
        if (locked) {
            try {
                return proceeding.proceed();
            } catch (Throwable throwable) {
                log.error("DLockAspect->lockAround error,message:{} ", throwable.getMessage(), throwable);
                throw new BusinessException(BusinessExceptionCode.SYSTEM_ERROR.getErrorMessage(), throwable);
            } finally {
                if (dLockModel.getExpireType() == ExpireType.METHOD) {
                    // 删除锁
                    this.delLock(dLockModel);
                }
            }
        } else {
            log.info("DLockAspect->lockAround don't get dLock,threadName:{},dLockModel:{}", Thread.currentThread().getName(), dLockModel);
            throw new BusinessException(BusinessExceptionCode.NOT_GET_LOCK.getErrorMessage(), dLockModel.getLockPrefix() + "->" + dLockModel.getLockKey());
        }
    }

    /**
     * 获取分布式锁
     */
    private boolean getDLock(DLockModel dLockModel) {
        if (dLockModel == null) {
            return false;
        }
        String lockPrefix = dLockModel.getLockPrefix();
        String lockKey = dLockModel.getLockKey();
        String idempotentId = dLockModel.getIdempotentId();
        int timeToLiveSeconds = dLockModel.getTimeToLiveSeconds();
        if (StringUtils.isEmpty(lockPrefix) || StringUtils.isEmpty(lockKey)) {
            log.warn("DLockAspect->getDLock check false,lockPrefix:{},lockKey:{}", lockPrefix, lockKey);
            return false;
        }
        String redisKey = getRedisKey(lockPrefix, lockKey, idempotentId);
        return dLockManager.setnx(redisKey, "1", timeToLiveSeconds);
    }

    /**
     * 删除锁
     */
    private void delLock(DLockModel dLockModel) {
        if (dLockModel == null) {
            return;
        }
        String lockPrefix = dLockModel.getLockPrefix();
        String lockKey = dLockModel.getLockKey();
        String idempotentId = dLockModel.getIdempotentId();
        dLockManager.del(getRedisKey(lockPrefix, lockKey, idempotentId));
    }


    /**
     * 获取锁参数
     */
    private DLockModel getDLockModel(ProceedingJoinPoint proceeding) {
        MethodSignature sign = (MethodSignature) proceeding.getSignature();
        Method method = sign.getMethod();
        DLock dLock = method.getAnnotation(DLock.class);
        String lockPrefix = dLock.lockPrefix();
        String lockKey = dLock.lockKey();

        return DLockModel.builder()
                .lockPrefix(StringUtils.isBlank(lockPrefix) ? method.getDeclaringClass().getSimpleName() : lockPrefix)
                .lockKey(StringUtils.isBlank(lockKey) ? method.getName() : lockKey)
                .timeToLiveSeconds(Long.valueOf(dLock.timeUnit().toSeconds(dLock.expireTime())).intValue())
                .idempotentId(getIdempotentId(dLock.idempotentKeys(), proceeding.getArgs(), method.getParameters()))
                .expireType(dLock.expireType())
                .build();
    }

    /**
     * 获取EvaluationContext，填充模板
     */
    private EvaluationContext getEvaluationContext(Object[] args, Parameter[] parameters) {
        EvaluationContext context = new StandardEvaluationContext();
        IntStream.range(0, parameters.length).forEach(i -> {
            Parameter parameter = parameters[i];
            context.setVariable(getVariableName(parameter), args[i] == null ? StringUtils.EMPTY : args[i]);
        });
        return context;
    }

    /**
     * 获取变量名称
     */
    private String getVariableName(Parameter parameter) {
        if (parameter == null) {
            return StringUtils.EMPTY;
        }
        DKeyAlias annotation = parameter.getAnnotation(DKeyAlias.class);
        return annotation == null ? parameter.getName() :
                StringUtils.defaultIfBlank(annotation.value(), parameter.getName());
    }

    /**
     * 拼接幂等id
     */
    private String getIdempotentId(String[] idempotentKeys, Object[] args, Parameter[] parameters) {
        if (!ObjectUtils.allNotNull(idempotentKeys, args, parameters)) {
            return StringUtils.EMPTY;
        }
        EvaluationContext evaluationContext = getEvaluationContext(args, parameters);
        StringBuilder builder = new StringBuilder();
        for (String idempotentKey : idempotentKeys) {
            String elValue = String.valueOf(getElValue(evaluationContext, idempotentKey));
            if (StringUtils.isNotBlank(elValue)) {
                builder.append(elValue).append(":");
            }
        }
        String result = builder.toString();
        return StringUtils.isNotBlank(result) ? SecureUtil.md5(result) : result;
    }

    /**
     * 解析EL模板值
     */
    private Object getElValue(EvaluationContext context, String idempotentKey) {
        if (StringUtils.isBlank(idempotentKey) || context == null) {
            return StringUtils.EMPTY;
        }
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(idempotentKey);
        try {
            return expression.getValue(context);
        } catch (EvaluationException e) {
            return StringUtils.EMPTY;
        }
    }

    /**
     * 获取redis key
     */
    private String getRedisKey(String lockPrefix, String lockKey, String idempotentId) {
        return lockPrefix + "-" + lockKey + (StringUtils.isNotBlank(idempotentId) ? "-" + idempotentId : StringUtils.EMPTY);
    }

}
