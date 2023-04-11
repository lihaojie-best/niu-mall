package com.niu.mall.dao;

import com.niu.mall.po.PmsProductAttributeValuePo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 存储产品参数信息的表 Mapper 接口
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Mapper
public interface PmsProductAttributeValueDao extends BaseMapper<PmsProductAttributeValuePo> {
    /**
     * 批量创建
     */
    int insertList(@Param("list") List<PmsProductAttributeValuePo> productAttributeValueList);
}
