package com.niu.mall.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niu.mall.user.dto.PmsProductCategoryWithChildrenDto;
import com.niu.mall.user.po.PmsProductCategoryPo;
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
