package com.niu.mall.admin.dto;


import com.niu.mall.admin.model.Product;
import com.niu.mall.admin.model.ProductAttribute;
import com.niu.mall.admin.model.ProductBasic;
import com.niu.mall.admin.model.productDetails;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/*
    创建修改商品的参数
 */

@EqualsAndHashCode(callSuper = false)
public class ProductParamDto {
    private Product product;

    public ProductParamDto() {
    }

    public ProductParamDto(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
