package com.niu.mall.admin.dao;


import com.niu.mall.admin.model.Product;
import com.niu.mall.admin.model.ProductAttribute;
import org.mapstruct.Mapper;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface ProductMapper {
    /**
     * 创建商品
     */
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    int create(Product record);

    /**
     * 更新商品
     */
    @Transactional
    int update(Product record);

    /**
     *查询商品全部信息
     * */
    @Transactional
    Product findById(Long id);

    /**
     *查询商品属性信息
     * */
    @Transactional
    ProductAttribute findAttributeById(Long id);

    /**
     * 更新商品属性
     */
    @Transactional
    int updateAttributeById(Long id, ProductAttribute attribute);
}
