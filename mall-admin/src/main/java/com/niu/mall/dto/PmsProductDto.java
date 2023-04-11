package com.niu.mall.dto;

import com.niu.mall.po.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 商品模块请求参数
 *
 * @author lihaojie
 * @date 2022/11/21 19:01
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PmsProductDto extends PmsProductPo {
    @ApiModelProperty("商品阶梯价格设置")
    private List<PmsProductLadderPo> productLadderList;
    @ApiModelProperty("商品满减价格设置")
    private List<PmsProductFullReductionPo> productFullReductionList;
    @ApiModelProperty("商品会员价格设置")
    private List<PmsMemberPricePo> memberPriceList;
    @ApiModelProperty("商品的sku库存信息")
    private List<PmsSkuStockPo> skuStockList;
    @ApiModelProperty("商品参数及自定义规格属性")
    private List<PmsProductAttributeValuePo> productAttributeValueList;
    @ApiModelProperty("专题和商品关系")
    private List<CmsSubjectProductRelationPo> subjectProductRelationList;
    @ApiModelProperty("优选专区和商品的关系")
    private List<CmsPrefrenceAreaProductRelationPo> prefrenceAreaProductRelationList;
}
