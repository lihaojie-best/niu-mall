package com.niu.mall.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niu.mall.user.po.CmsTopicPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 话题表 Mapper 接口
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Mapper
public interface CmsTopicDao extends BaseMapper<CmsTopicPo> {

}
