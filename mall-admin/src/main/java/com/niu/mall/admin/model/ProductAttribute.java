package com.niu.mall.admin.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/*
商品属性
 */
@Getter
@Setter
@ToString

public class ProductAttribute implements Serializable {
    @ApiModelProperty(value = "id")
    private Long productId;
    @ApiModelProperty(value = "Attribute.颜色属性")
    private String productColour;
    @ApiModelProperty(value = "Attribute.商品分类")
    private String productType;
    @ApiModelProperty(value = "Attribute.商品库存量")
    private int productStock;
}
