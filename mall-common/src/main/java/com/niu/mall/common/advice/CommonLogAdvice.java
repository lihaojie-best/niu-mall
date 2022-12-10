package com.niu.mall.common.advice;

import com.niu.mall.common.api.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Slf4j
@Aspect //声明这是一个切面
@Component
public class CommonLogAdvice {

    @Around("within(@org.springframework.stereotype.* *)")//这是切面表达式，含义是只要是Service注解修饰的类都将被选中
    public Object handleExceptionLog(ProceedingJoinPoint jp){
        try {
            // 记录方法进入日志  jp.getSignature()是方法路径名  Arrays.toString(jp.getArgs()): 将jp的所有参数获取并存入数组，之后toString
            log.debug("{}方法准备调用，参数: {}", jp.getSignature(), Arrays.toString(jp.getArgs()));
            long a = System.currentTimeMillis();
            // 调用切点方法
            Object result = jp.proceed();
            // 记录方法结束日志
            log.debug("{}方法调用成功，执行耗时{}", jp.getSignature(), System.currentTimeMillis() - a);
            return result;
        } catch (Throwable throwable) {
            log.error("{}方法执行失败，原因：{}", jp.getSignature(), throwable.getMessage(), throwable);
            return Result.failed(throwable.getMessage());
        }
    }
}