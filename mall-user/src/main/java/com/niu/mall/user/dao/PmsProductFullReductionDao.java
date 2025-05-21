package com.niu.mall.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niu.mall.user.po.PmsProductFullReductionPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 产品满减表(只针对同商品) Mapper 接口
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Mapper
public interface PmsProductFullReductionDao extends BaseMapper<PmsProductFullReductionPo> {
    /**
     * 批量创建
     */
    int insertList(@Param("list") List<PmsProductFullReductionPo> productFullReductionList);
}
