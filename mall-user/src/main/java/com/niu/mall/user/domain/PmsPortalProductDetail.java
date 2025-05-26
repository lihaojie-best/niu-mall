package com.niu.mall.user.domain;


import com.niu.mall.user.po.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 前台商品详情
 * Created by lihaojie on 2023/4/6.
 */
@Getter
@Setter
public class PmsPortalProductDetail{
    @ApiModelProperty("商品信息")
    private PmsProductPo product;
    @ApiModelProperty("商品品牌")
    private PmsBrandPo brand;
    @ApiModelProperty("商品属性与参数")
    private List<PmsProductAttributePo> productAttributeList;
    @ApiModelProperty("手动录入的商品属性与参数值")
    private List<PmsProductAttributeValuePo> productAttributeValueList;
    @ApiModelProperty("商品的sku库存信息")
    private List<PmsSkuStockPo> skuStockList;
    @ApiModelProperty("商品阶梯价格设置")
    private List<PmsProductLadderPo> productLadderList;
    @ApiModelProperty("商品满减价格设置")
    private List<PmsProductFullReductionPo> productFullReductionList;
    @ApiModelProperty("商品可用优惠券")
    private List<SmsCouponPo> couponList;
}
