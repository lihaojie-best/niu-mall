package com.niu.mall.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niu.mall.user.po.PmsProductVertifyRecordPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商品审核记录 Mapper 接口
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Mapper
public interface PmsProductVertifyRecordDao extends BaseMapper<PmsProductVertifyRecordPo> {
    int insertList(@Param("list") List<PmsProductVertifyRecordPo> list);
}
