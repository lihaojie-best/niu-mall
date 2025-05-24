package com.niu.mall.user.domain;


import com.niu.mall.user.po.PmsProductFullReductionPo;
import com.niu.mall.user.po.PmsProductLadderPo;
import com.niu.mall.user.po.PmsProductPo;
import com.niu.mall.user.po.PmsSkuStockPo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 促销商品信息，包括sku、打折优惠、满减优惠
 * Created by lihaojie on 2023/8/27.
 */
@Getter
@Setter
public class PromotionProduct extends PmsProductPo {
    //商品库存信息
    private List<PmsSkuStockPo> skuStockList;
    //商品打折信息
    private List<PmsProductLadderPo> productLadderList;
    //商品满减信息
    private List<PmsProductFullReductionPo> productFullReductionList;
}
