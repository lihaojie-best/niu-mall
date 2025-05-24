package com.niu.mall.user.dao;

import com.niu.mall.user.po.SmsCouponPo;
import com.niu.mall.user.domain.CartProduct;
import com.niu.mall.user.domain.PromotionProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 前台购物车商品管理自定义Dao
 * Created by lihaojie on 2023/8/2.
 */
@Mapper
public interface PortalProductDao {
    /**
     * 获取购物车商品信息
     */
    CartProduct getCartProduct(@Param("id") Long id);

    /**
     * 获取促销商品信息列表
     */
    List<PromotionProduct> getPromotionProductList(@Param("ids") List<Long> ids);

    /**
     * 获取可用优惠券列表
     */
    List<SmsCouponPo> getAvailableCouponList(@Param("productId") Long productId, @Param("productCategoryId") Long productCategoryId);
}
