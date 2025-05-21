package com.niu.mall.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niu.mall.user.dto.PmsProductAttributeCategoryDto;
import com.niu.mall.user.po.PmsProductAttributeCategoryPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 Mapper 接口
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Mapper
public interface PmsProductAttributeCategoryDao extends BaseMapper<PmsProductAttributeCategoryPo> {

    List<PmsProductAttributeCategoryDto> getListWithAttr();
}
