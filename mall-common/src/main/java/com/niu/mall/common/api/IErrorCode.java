package com.niu.mall.common.api;

/**
 * 常用API返回对象接口
 *
 * @author lihaojie
 * @date 2022/12/18 13:21
 **/
public interface IErrorCode {
    /**
     * 返回码
     */
    long getCode();

    /**
     * 返回信息
     */
    String getMessage();
}
