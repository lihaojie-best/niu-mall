package com.niu.mall.admin.dao;

import com.niu.mall.admin.dto.PmsProductCategoryWithChildrenDto;
import com.niu.mall.mbg.po.PmsProductCategoryPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 产品分类 Mapper 接口
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Mapper
public interface PmsProductCategoryDao extends BaseMapper<PmsProductCategoryPo> {
    List<PmsProductCategoryWithChildrenDto> listWithChildren();
}
