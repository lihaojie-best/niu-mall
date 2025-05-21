package com.niu.mall.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;


/**
 * 用户登录参数
 *
 * @author lihaojie
 * @date 2022/12/29 14:06
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class UmsAdminLoginParam {
    @NonNull
    @ApiModelProperty(value = "用户名", required = true)
    private String username;
    @NonNull
    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
