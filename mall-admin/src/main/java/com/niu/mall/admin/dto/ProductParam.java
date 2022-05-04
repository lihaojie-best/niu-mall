package com.niu.mall.admin.dto;


import com.niu.mall.admin.model.ProductAttribute;
import com.niu.mall.admin.model.ProductBasic;
import com.niu.mall.admin.model.productDetails;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/*
    创建修改商品的参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductParam {

  private List<ProductBasic> ProductBasic;  //商品基础信息
  private List<ProductAttribute> ProductAttribute; //商品属性
  private List<productDetails> productDetails;//商品详情

}
