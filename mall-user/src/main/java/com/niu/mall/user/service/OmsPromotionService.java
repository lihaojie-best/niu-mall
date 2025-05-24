package com.niu.mall.user.service;

import com.niu.mall.user.domain.CartPromotionItem;
import com.niu.mall.user.po.OmsCartItemPo;

import java.util.List;

/**
 * 促销管理Service
 * Created by lihaojie on 2023/8/27.
 */
public interface OmsPromotionService {
    /**
     * 计算购物车中的促销活动信息
     * @param cartItemList 购物车
     */
    List<CartPromotionItem> calcCartPromotion(List<OmsCartItemPo> cartItemList);
}
