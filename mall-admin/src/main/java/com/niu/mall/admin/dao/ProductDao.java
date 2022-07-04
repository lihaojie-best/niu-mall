package com.niu.mall.admin.dao;

import com.niu.mall.admin.domain.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 商品基本信息 Mapper 接口
 * </p>
 *
 * @author lhj
 * @since 2022-07-03
 */
@Mapper
@Repository
public interface ProductDao extends BaseMapper<Product> {
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
