package com.niu.mall.user.dto;


import com.niu.mall.user.po.OmsOrderItemPo;
import com.niu.mall.user.po.OmsOrderOperateHistoryPo;
import com.niu.mall.user.po.OmsOrderPo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 订单详情信息
 * Created by lihaojie on 2023/10/11.
 */
public class OmsOrderDetailDto extends OmsOrderPo {
    @Getter
    @Setter
    @ApiModelProperty("订单商品列表")
    private List<OmsOrderItemPo> orderItemList;
    @Getter
    @Setter
    @ApiModelProperty("订单操作记录列表")
    private List<OmsOrderOperateHistoryPo> historyList;
}
