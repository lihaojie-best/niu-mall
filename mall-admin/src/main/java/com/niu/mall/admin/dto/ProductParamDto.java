package com.niu.mall.admin.dto;


import com.niu.mall.admin.model.Product;
import lombok.EqualsAndHashCode;

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
