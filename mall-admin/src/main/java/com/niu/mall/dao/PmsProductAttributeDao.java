package com.niu.mall.dao;

import com.niu.mall.dto.PmsProductAttrInfoDto;
import com.niu.mall.po.PmsProductAttributePo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 Mapper 接口
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Mapper
public interface PmsProductAttributeDao extends BaseMapper<PmsProductAttributePo> {
    /**
     * 根据商品分类的id获取商品属性及属性分类
     *
     * @param productCategoryId 商品类id
     * @return java.util.List<PmsProductAttrInfoDto>
     * @author lihaojie
     * @date 2022/12/18 13:08
     */
    List<PmsProductAttrInfoDto> getProductAttrInfo(Long productCategoryId);
}
