package com.niu.mall.dao;

import com.niu.mall.po.SmsCouponProductCategoryRelationPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 优惠券和产品分类关系表 Mapper 接口
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Mapper
public interface SmsCouponProductCategoryRelationDao extends BaseMapper<SmsCouponProductCategoryRelationPo> {
    /**
     * 批量插入优惠卷与product category
     *
     * @param productCategoryRelationPoList  集合
     * @return void
     * @author lihaojie
     * @date 2023/04/05 21:33
     */
    void insertList(@Param("productCategoryRelationPoList") List<SmsCouponProductCategoryRelationPo> productCategoryRelationPoList);

}
