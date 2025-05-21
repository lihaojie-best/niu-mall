package com.niu.mall.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niu.mall.user.po.CmsSubjectProductRelationPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 专题商品关系表 Mapper 接口
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Mapper
public interface CmsSubjectProductRelationDao extends BaseMapper<CmsSubjectProductRelationPo> {
    /**
     * 批量创建
     */
    int insertList(@Param("list") List<CmsSubjectProductRelationPo> subjectProductRelationList);
}
