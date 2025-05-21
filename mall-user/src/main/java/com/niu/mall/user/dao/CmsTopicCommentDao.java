package com.niu.mall.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niu.mall.user.po.CmsTopicCommentPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 专题评论表 Mapper 接口
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Mapper
public interface CmsTopicCommentDao extends BaseMapper<CmsTopicCommentPo> {

}
