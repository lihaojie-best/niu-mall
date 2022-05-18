package com.niu.mall.admin.service;

import com.niu.mall.admin.dto.ProductParamDto;
import com.niu.mall.admin.model.Product;
import com.niu.mall.common.api.Result;
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
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    Result createProduct(Product product);

    /**
     * 更新商品
     */
    @Transactional
    Result update(Product product);

    /**
     * 查询商品
     */
    @Transactional
    Result getByProductId(Long Id);

    /**
     * 分页查询商品
     */
    @Transactional
    List<ProductParamDto> list(ProductParamDto product, Integer pageSize, Integer pageNum);


}
