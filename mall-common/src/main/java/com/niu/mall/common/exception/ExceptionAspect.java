package com.niu.mall.common.exception;

import com.niu.mall.common.api.Result;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 统一业务异常切面处理
 *
 * @author lihaojie
 * @date 2023/04/09 09:06
 **/
@Aspect
@Component
public class ExceptionAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAspect.class);

    @Pointcut("execution(public * com.niu.mall.controller.*.*(..))||execution(public * com.niu.mall.*.controller.*.*(..))")
    public void globalExpect() {
    }

    /**
     * 环绕方法
     *
     * @param joinPoint 切入点
     * @return java.lang.Object
     * @author lihaojie
     * @date 2023/04/09 09:21
     */
    @Order(0)
    @Around("globalExpect()")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        //  业务逻辑增强代码
        try {
            Object result = joinPoint.proceed();
            LOGGER.info(joinPoint.getSignature().toShortString()+" 成功。");
            return result;
        } catch (Throwable e) {
            //打印错误的堆栈信息
            LOGGER.error(joinPoint.getSignature().toShortString()+" 失败: "+ e);
            return Result.failed(e.getMessage());
        }
    }

    @Pointcut("execution(public * com.niu.mall.service.*.*(..))||execution(public * com.niu.mall.*.service.*.*(..))")
    public void serverGlobalExpect() {
    }

    @Around("serverGlobalExpect()")
    public Object doServerAround(ProceedingJoinPoint joinPoint) {
        try {
            Object result = joinPoint.proceed();
            LOGGER.info(joinPoint.getSignature().toShortString()+" 成功。");
            return result;
        } catch (Throwable e) {
            LOGGER.info(joinPoint.getSignature().toShortString()+" 失败: "+e);
            throw new RuntimeException(e);
        }
    }
}
