package com.niu.mall.admin.dao;

import com.niu.mall.mbg.po.PmsMemberPricePo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商品会员价格表 Mapper 接口
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Mapper
public interface PmsMemberPriceDao extends BaseMapper<PmsMemberPricePo> {
    /**
     * 批量创建
     */
    int insertList(@Param("list") List<PmsMemberPricePo> memberPriceList);
}
