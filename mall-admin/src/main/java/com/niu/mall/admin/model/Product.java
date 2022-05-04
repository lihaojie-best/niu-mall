package com.niu.mall.admin.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Product {
    //Attribute
    private Long productId;
    private String productColour;
    private String productType;
    private int productStock;//库存
    //basic

    private String productName;//商品名
    private int productPrice;//价格
    private int productSalesVolume;//销量
    //details

    private String productCompany;
    private Date productDate;
}
