package com.niu.mall.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niu.mall.user.param.SmsCouponParam;
import com.niu.mall.user.po.SmsCouponPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 优惠券表 Mapper 接口
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Mapper
public interface SmsCouponDao extends BaseMapper<SmsCouponPo> {


    SmsCouponParam getItem(@Param("id") Long id);
}
