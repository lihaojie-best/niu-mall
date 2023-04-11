package com.niu.mall.dao;

import com.niu.mall.param.SmsCouponParam;
import com.niu.mall.po.SmsCouponPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
