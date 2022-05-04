package com.niu.mall.admin.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
商品属性
 */
@Getter
@Setter
@ToString

public class ProductAttribute {
    private Long productId;
    private String productColour;
    private String productType;
    private int productStock;//库存
}
