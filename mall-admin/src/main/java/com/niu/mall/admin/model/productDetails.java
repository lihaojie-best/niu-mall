package com.niu.mall.admin.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/*
 商品详情
 */
@Getter
@Setter
@ToString
public class productDetails implements Serializable {
    private Long productId;
    @ApiModelProperty(value = "details.生产厂家")
    private String productCompany;
    @ApiModelProperty(value = "details.商品日期")
    private Date productDate;
}
