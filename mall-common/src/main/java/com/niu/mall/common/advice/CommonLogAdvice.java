package com.niu.mall.common.advice;

import com.niu.mall.common.api.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 全局统一日志处理
 *
 * @author lihaojie
 * @date 2022/12/14 12:29
 **/
@Slf4j
@Aspect
@Component
public class CommonLogAdvice {
    /**
     *
     *
     * @param jp
     * @return java.lang.Object
     * @author lihaojie
     * @date 2022/12/14 12:29
     */
    //这是切面表达式，含义是只要是@Service,@Controller,@Component,@Indexed注解修饰的类都将被选中
    @Around("within(@org.springframework.stereotype.* *)")
    public Object handleExceptionLog(ProceedingJoinPoint jp){
        try {
            // 记录方法进入日志  jp.getSignature()是方法路径名  Arrays.toString(jp.getArgs()): 将jp的所有参数获取并存入数组，之后toString
            log.debug("{}方法准备调用，参数: {}", jp.getSignature(), Arrays.toString(jp.getArgs()));
            //方法调用时间
            long beginTime = System.currentTimeMillis();
            // 调用切点方法
            Object result = jp.proceed();
            // 记录方法结束日志
            log.debug("{}方法调用成功，执行耗时{}", jp.getSignature(), System.currentTimeMillis() - beginTime);
            return result;
        } catch (Throwable throwable) {
            log.error("{}方法执行失败，原因：{}", jp.getSignature(), throwable.getMessage(), throwable);
            return Result.failed(throwable.getMessage());
        }
    }
}