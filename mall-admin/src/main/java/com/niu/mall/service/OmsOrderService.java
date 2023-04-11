package com.niu.mall.service;

import com.niu.mall.dto.OmsOrderDetailDto;
import com.niu.mall.param.OmsMoneyInfoParam;
import com.niu.mall.param.OmsOrderDeliveryParam;
import com.niu.mall.param.OmsOrderQueryParam;
import com.niu.mall.param.OmsReceiverInfoParam;
import com.niu.mall.po.OmsOrderPo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
public interface OmsOrderService extends IService<OmsOrderPo> {
    /**
     * 查询订单
     *
     * @param queryParam 查询参数
     * @param pageSize 分页大小
     * @param pageNum 查询页数
     * @return java.util.List<OmsOrderPo>
     * @author lihaojie
     * @date 2023/01/16 23:46
     */
    List<OmsOrderPo> list(OmsOrderQueryParam queryParam, Integer pageSize, Integer pageNum);
    /**
     * 批量发货
     *
     * @param deliveryParamList 每个订单的发货信息
     * @return int
     * @author lihaojie
     * @date 2023/01/17 00:16
     */
    int delivery(List<OmsOrderDeliveryParam> deliveryParamList);
    /**
     * 批量关闭订单
     *
     * @param ids id列表
     * @param note 订单操作历史记录备注
     * @return int
     * @author lihaojie
     * @date 2023/01/17 00:32
     */
    int close(List<Long> ids, String note);
    /**
     * 根据id获取订单信息
     *
     * @param id 订单id
     * @return com.niu.mall.admin.dto.OmsOrderDetail
     * @author lihaojie
     * @date 2023/01/17 00:49
     */
    OmsOrderDetailDto detail(Long id);
    /**
     * 修改收货人信息
     *
     * @param receiverInfoParam 订单修改收货人信息参数
     * @return int
     * @author lihaojie
     * @date 2023/01/17 11:39
     */
    int updateReceiverInfo(OmsReceiverInfoParam receiverInfoParam);
    /**
     * 修改订单费用信息
     *
     * @param moneyInfoParam 修改订单费用信息参数
     * @return int
     * @author lihaojie
     * @date 2023/01/17 12:23
     */
    int updateMoneyInfo(OmsMoneyInfoParam moneyInfoParam);
    /**
     * 备注订单
     *
     * @param id 订单id
     * @param note 订单备注
     * @param status 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
     * @return int
     * @author lihaojie
     * @date 2023/01/17 12:24
     */
    int updateNote(Long id, String note, Integer status);
}
