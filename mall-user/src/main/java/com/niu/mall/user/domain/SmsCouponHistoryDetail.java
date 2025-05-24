package com.niu.mall.user.domain;


import com.niu.mall.user.po.SmsCouponHistoryPo;
import com.niu.mall.user.po.SmsCouponPo;
import com.niu.mall.user.po.SmsCouponProductCategoryRelationPo;
import com.niu.mall.user.po.SmsCouponProductRelationPo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 优惠券领取历史详情（包括优惠券信息和关联关系）
 * Created by lihaojie on 2023/8/29.
 */
@Getter
@Setter
public class SmsCouponHistoryDetail extends SmsCouponHistoryPo {
    @ApiModelProperty("相关优惠券信息")
    private SmsCouponPo coupon;
    @ApiModelProperty("优惠券关联商品")
    private List<SmsCouponProductRelationPo> productRelationList;
    @ApiModelProperty("优惠券关联商品分类")
    private List<SmsCouponProductCategoryRelationPo> categoryRelationList;
}
