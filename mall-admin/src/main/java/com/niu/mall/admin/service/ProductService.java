package com.niu.mall.admin.service;

import com.niu.mall.common.api.Result;
import com.niu.mall.mbg.domain.ProductDomain;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 商品基本信息 服务类
 * </p>
 *
 * @author lihaojie
 * @since 2022-08-31
 */
@Service
public interface ProductService extends IService<ProductDomain> {
    /**
     * 创建商品
     */
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    Result createProduct(ProductDomain product);

    /**
     * 更新商品
     */
    @Transactional
    Result update(ProductDomain product);

    /**
     * 查询商品
     */
    Result getByProductId(Long id);

}
