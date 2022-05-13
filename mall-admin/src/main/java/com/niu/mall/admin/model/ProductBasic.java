package com.niu.mall.admin.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/*
商品基础信息

 */
@Getter
@Setter
@ToString
public class ProductBasic implements Serializable {
    private Long productId;
    @ApiModelProperty(value = "Basic.商品名")
    private String productName;
    @ApiModelProperty(value = "Basic.价格")
    private int productPrice;
    @ApiModelProperty(value = "Basic.销量")
    private int productSalesVolume;
}
