package com.niu.mall.common.exception;

import com.niu.mall.common.api.Result;
import org.springframework.http.HttpRequest;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理
 * (@RestControllerAdvice=@ControllerAdvice+@ResponseBody)
 *
 * @author lihaojie
 * @date 2022/12/14 12:59
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = ApiException.class)
    public Result apiExceptionHandle(HttpServletRequest request,
                                     HttpServletResponse response,
                                     ApiException exception) {
        if (exception.getErrorCode() != null) {
            return Result.failed(exception.getErrorCode());
        }
        return Result.failed(exception.getMessage());
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handleValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField() + fieldError.getDefaultMessage();
            }
        }
        return Result.validateFailed(message);
    }


    @ExceptionHandler(value = BindException.class)
    public Result handleValidException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField() + fieldError.getDefaultMessage();
            }
        }
        return Result.validateFailed(message);
    }
}
