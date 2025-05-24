package com.niu.mall.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niu.mall.user.domain.OmsOrderDetail;
import com.niu.mall.user.dto.OmsOrderDetailDto;
import com.niu.mall.user.param.OmsOrderDeliveryParam;
import com.niu.mall.user.param.OmsOrderQueryParam;
import com.niu.mall.user.po.OmsOrderItemPo;
import com.niu.mall.user.po.OmsOrderPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Mapper
public interface OmsOrderDao extends BaseMapper<OmsOrderPo> {
    /**
     * 条件查询订单
     */
    List<OmsOrderPo> getList(@Param("queryParam") OmsOrderQueryParam queryParam);

    /**
     * 批量发货
     */
    int delivery(@Param("list") List<OmsOrderDeliveryParam> deliveryParamList);
    /**
     * 获取订单详情
     *
     * @param id 订单id
     * @return com.niu.mall.admin.dto.OmsOrderDetail
     * @author lihaojie
     * @date 2023/01/17 00:51
     */
    OmsOrderDetailDto getDetail(@Param("id") Long id);
}
