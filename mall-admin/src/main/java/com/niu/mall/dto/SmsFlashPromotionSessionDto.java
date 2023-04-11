package com.niu.mall.dto;

import com.niu.mall.po.SmsFlashPromotionSessionPo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 包含商品可选场次的数量
 *
 * @author lihaojie
 * @date 2023/04/09 19:41
 **/
public class SmsFlashPromotionSessionDto extends SmsFlashPromotionSessionPo {
    @Getter
    @Setter
    @ApiModelProperty("商品数量")
    private Long productCount;
}
