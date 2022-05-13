package com.niu.mall.admin.service;
/*
 商品属性管理
 */

import com.niu.mall.admin.model.ProductAttribute;
import org.springframework.transaction.annotation.Transactional;

public interface ProductAttributeService {


    /**
     * 修改商品属性
     */
    @Transactional
    int updateAttributeById(ProductAttribute productAttribute);

    /**
     * 获取单个商品属性信息
     */
    @Transactional
    ProductAttribute findAttributeById(Long id);

}
