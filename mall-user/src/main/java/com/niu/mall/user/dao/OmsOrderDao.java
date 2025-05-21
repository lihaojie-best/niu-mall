package com.niu.mall.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niu.mall.user.dto.OmsOrderDetailDto;
import com.niu.mall.user.po.OmsOrderPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
     * 获取订单详情
     *
     * @param id 订单id
     * @return com.niu.mall.admin.dto.OmsOrderDetail
     * @author lihaojie
     * @date 2023/01/17 00:51
     */
    OmsOrderDetailDto getDetail(@Param("id") Long id);
}
