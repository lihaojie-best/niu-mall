package com.niu.mall.common.exception;

import com.niu.mall.common.api.IErrorCode;


/**
 * 自定义API异常
 *
 * @author lihaojie
 * @date 2022/12/07 23:02
 **/
public class ApiException extends RuntimeException {
    private IErrorCode errorCode;

    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
