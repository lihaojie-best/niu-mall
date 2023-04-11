package com.niu.mall.param;

import com.niu.mall.po.SmsCouponPo;
import com.niu.mall.po.SmsCouponProductCategoryRelationPo;
import com.niu.mall.po.SmsCouponProductRelationPo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 优惠卷信息封装，包括绑定商品和商品分类
 *
 * @author lihaojie
 * @date 2023/04/05 20:46
 **/

public class SmsCouponParam extends SmsCouponPo {
    @Getter
    @Setter
    @ApiModelProperty("优惠卷绑定的商品")
    private List<SmsCouponProductRelationPo> productRelationPoList;
    @Getter
    @Setter
    @ApiModelProperty("优惠卷绑定的商品分类")
    private List<SmsCouponProductCategoryRelationPo> productCategoryRelationPoList;
}
