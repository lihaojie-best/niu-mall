package com.niu.mall.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niu.mall.user.po.OmsOrderItemPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单中所包含的商品 Mapper 接口
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Mapper
public interface OmsOrderItemDao extends BaseMapper<OmsOrderItemPo> {

}
