package com.niu.mall.admin.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/*
 商品详情
 */
@Getter
@Setter
@ToString
public class productDetails {
    private Long productId;
    private String productCompany;
    private Date productDate;
}
