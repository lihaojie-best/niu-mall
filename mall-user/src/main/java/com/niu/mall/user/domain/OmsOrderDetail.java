package com.niu.mall.user.domain;

import com.niu.mall.user.po.OmsOrderItemPo;
import com.niu.mall.user.po.OmsOrderPo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 包含商品信息的订单详情
 * Created by lihaojie on 2023/9/4.
 */
@Getter
@Setter
public class OmsOrderDetail extends OmsOrderPo {
    @ApiModelProperty("订单商品列表")
    private List<OmsOrderItemPo> orderItemList;
}
