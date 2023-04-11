package com.niu.mall.dto;


import com.niu.mall.po.OmsOrderItemPo;
import com.niu.mall.po.OmsOrderOperateHistoryPo;
import com.niu.mall.po.OmsOrderPo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 订单详情信息
 * Created by macro on 2018/10/11.
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
