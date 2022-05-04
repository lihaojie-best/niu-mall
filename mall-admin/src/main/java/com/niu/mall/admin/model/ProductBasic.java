package com.niu.mall.admin.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
商品基础信息

 */
@Getter
@Setter
@ToString
public class ProductBasic {
    private Long productId;
    private String productName;//商品名
    private int productPrice;//价格
    private int productSalesVolume;//销量
}
