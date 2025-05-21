package com.niu.mall.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niu.mall.user.po.CmsMemberReportPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户举报表 Mapper 接口
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Mapper
public interface CmsMemberReportDao extends BaseMapper<CmsMemberReportPo> {

}
