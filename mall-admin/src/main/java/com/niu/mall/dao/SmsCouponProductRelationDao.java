package com.niu.mall.dao;

import com.niu.mall.po.SmsCouponProductRelationPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
