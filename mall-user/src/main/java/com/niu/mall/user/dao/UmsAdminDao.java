package com.niu.mall.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niu.mall.user.po.UmsAdminPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 后台用户表 Mapper 接口
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Mapper
public interface UmsAdminDao extends BaseMapper<UmsAdminPo> {

}
