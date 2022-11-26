package com.niu.mall.admin.dao;

import com.niu.mall.mbg.po.UmsMemberProductCategoryRelationPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 会员与产品分类关系表（用户喜欢的分类） Mapper 接口
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Mapper
public interface UmsMemberProductCategoryRelationDao extends BaseMapper<UmsMemberProductCategoryRelationPo> {

}
