package com.niu.mall.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niu.mall.user.po.SmsCouponProductRelationPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 优惠券和产品的关系表 Mapper 接口
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Mapper
public interface SmsCouponProductRelationDao extends BaseMapper<SmsCouponProductRelationPo> {

    void insertList(@Param("productRelationPoList") List<SmsCouponProductRelationPo> productRelationPoList);
}
