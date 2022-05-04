package com.niu.mall.admin.service;

import com.niu.mall.admin.dto.ProductParam;
import com.niu.mall.admin.model.Product;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
商品管理service
 */
public interface ProductService {

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
    /*
    * 查询商品
     */
    @Transactional
    Product getByProductId(Long productId);
    /**
     * 分页查询商品
     */
    @Transactional
    List<ProductParam> list(ProductParam product, Integer pageSize, Integer pageNum);



}
