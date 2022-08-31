package com.niu.mall.admin.dao;

import com.niu.mall.mbg.domain.ProductDomain;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 商品基本信息 Mapper 接口
 * </p>
 *
 * @author lihaojie
 * @since 2022-08-31
 */
@Mapper
public interface ProductDao extends BaseMapper<ProductDomain> {
    /**
     * 创建商品
     */
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    int createProduct(ProductDomain record);

    /**
     * 更新商品
     */
    @Transactional
    int update(ProductDomain record);

    /**
     *查询商品全部信息
     * */
    @Transactional
    ProductDomain findById(Long productId);
}
