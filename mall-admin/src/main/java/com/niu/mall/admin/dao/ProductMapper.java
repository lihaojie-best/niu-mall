package com.niu.mall.admin.dao;


import com.niu.mall.admin.model.Product;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Mapper
@Repository
public interface ProductMapper {
    /**
     * 创建商品
     */
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    int createProduct(Product record);

    /**
     * 更新商品
     */
    @Transactional
    int update(Product record);

    /**
     *查询商品全部信息
     * */
    @Transactional
    Product findById(Long productId);

}
