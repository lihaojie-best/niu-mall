package com.niu.mall.user.service;


import com.niu.mall.user.domain.CartPromotionItem;
import com.niu.mall.user.domain.SmsCouponHistoryDetail;
import com.niu.mall.user.po.SmsCouponHistoryPo;
import com.niu.mall.user.po.SmsCouponPo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户优惠券管理Service
 * Created by lihaojie on 2023/8/29.
 */
public interface UmsMemberCouponService {
    /**
     * 会员添加优惠券
     */
    @Transactional
    void add(Long couponId);

    /**
     * 获取优惠券历史列表
     */
    List<SmsCouponHistoryPo> listHistory(Integer useStatus);

    /**
     * 根据购物车信息获取可用优惠券
     */
    List<SmsCouponHistoryDetail> listCart(List<CartPromotionItem> cartItemList, Integer type);

    /**
     * 获取当前商品相关优惠券
     */
    List<SmsCouponPo> listByProduct(Long productId);

    /**
     * 获取用户优惠券列表
     */
    List<SmsCouponPo> list(Integer useStatus);
}
