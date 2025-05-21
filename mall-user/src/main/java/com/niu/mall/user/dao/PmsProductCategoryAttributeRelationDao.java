package com.niu.mall.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niu.mall.user.po.PmsProductCategoryAttributeRelationPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类） Mapper 接口
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Mapper
public interface PmsProductCategoryAttributeRelationDao extends BaseMapper<PmsProductCategoryAttributeRelationPo> {

    static void insertList(List<PmsProductCategoryAttributeRelationPo> relationList) {

    }
}
