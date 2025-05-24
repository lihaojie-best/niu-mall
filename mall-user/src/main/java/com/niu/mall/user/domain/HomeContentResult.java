package com.niu.mall.user.domain;


import com.niu.mall.user.po.CmsSubjectPo;
import com.niu.mall.user.po.PmsBrandPo;
import com.niu.mall.user.po.PmsProductPo;
import com.niu.mall.user.po.SmsHomeAdvertisePo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 首页内容返回信息封装
 * Created by macro on 2019/1/28.
 */
@Getter
@Setter
public class HomeContentResult {
    @ApiModelProperty("轮播广告")
    private List<SmsHomeAdvertisePo> advertiseList;
    @ApiModelProperty("推荐品牌")
    private List<PmsBrandPo> brandList;
    @ApiModelProperty("当前秒杀场次")
    private HomeFlashPromotion homeFlashPromotion;
    @ApiModelProperty("新品推荐")
    private List<PmsProductPo> newProductList;
    @ApiModelProperty("人气推荐")
    private List<PmsProductPo> hotProductList;
    @ApiModelProperty("推荐专题")
    private List<CmsSubjectPo> subjectList;
}
