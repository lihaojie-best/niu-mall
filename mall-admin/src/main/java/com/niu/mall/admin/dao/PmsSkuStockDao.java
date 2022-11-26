package com.niu.mall.admin.dao;

import com.niu.mall.mbg.po.PmsSkuStockPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * sku的库存 Mapper 接口
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Mapper
public interface PmsSkuStockDao extends BaseMapper<PmsSkuStockPo> {
    /**
     * 批量插入操作
     */
    int insertList(@Param("list") List<PmsSkuStockPo> skuStockList);
}
