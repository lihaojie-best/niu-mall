package com.niu.mall.dao;

import com.niu.mall.po.PmsProductPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 商品信息 Mapper 接口
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Mapper
public interface PmsProductDao extends BaseMapper<PmsProductPo> {

}
