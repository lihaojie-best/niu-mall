package com.niu.mall.admin.service;

import com.niu.mall.admin.domain.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.niu.mall.admin.dto.ProductParamDto;
import com.niu.mall.common.api.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 商品基本信息 服务类
 * </p>
 *
 * @author lhj
 * @since 2022-07-03
 */
@Service
public interface IProductService extends IService<Product> {
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
    Result getByProductId(Long Id);

    /**
     * 分页查询商品
     */
    List<ProductParamDto> list(ProductParamDto product, Integer pageSize, Integer pageNum);

}
