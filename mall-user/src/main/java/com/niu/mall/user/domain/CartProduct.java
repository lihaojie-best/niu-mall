package com.niu.mall.user.domain;


import com.niu.mall.user.po.PmsProductAttributePo;
import com.niu.mall.user.po.PmsProductPo;
import com.niu.mall.user.po.PmsSkuStockPo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 购物车中带规格和SKU的商品信息
 * Created by lihaojie on 2023/8/2.
 */
@Getter
@Setter
public class CartProduct extends PmsProductPo {
    @ApiModelProperty("商品属性列表")
    private List<PmsProductAttributePo> productAttributeList;
    @ApiModelProperty("商品SKU库存列表")
    private List<PmsSkuStockPo> skuStockList;
}
