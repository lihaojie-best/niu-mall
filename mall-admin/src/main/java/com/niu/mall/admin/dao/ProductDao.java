package com.niu.mall.admin.dao;

import com.niu.mall.admin.dto.ProductAttributeParam;
import com.niu.mall.admin.dto.ProductParam;
import com.niu.mall.admin.model.Product;
import com.niu.mall.admin.model.ProductAttribute;
import com.niu.mall.admin.model.ProductBasic;
import com.niu.mall.admin.model.productDetails;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductDao {
    /**
     * 创建商品
     */
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    int create(ProductParam product);

    /**
     * 更新商品
     */
    @Transactional
    int update(Long id, ProductParam product);


    /**
     * 批量删除商品
     */
    @Transactional
    int DeleteStatus(List<Long> ids, Integer deleteStatus);
    /*
    *查询商品全部信息
    * */
    @Transactional
    Product findById(Long id);
    /*
     *查询商品基础信息
     * */
    @Transactional
    ProductBasic findBasicById(Long id);
    /*
     *查询商品详细信息
     * */
    @Transactional
    productDetails findDetailsById(Long id);
    /*
     *查询商品属性信息
     * */
    @Transactional
    ProductAttribute findAttributeById(Long id);
    /**
     * 更新商品属性
     *
     */
    @Transactional
    int updateAttributeById(Long id, ProductAttribute attribute);
}
