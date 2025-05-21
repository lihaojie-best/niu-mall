package com.niu.mall.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niu.mall.user.po.UmsAdminLoginLogPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 后台用户登录日志表 Mapper 接口
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Mapper
public interface UmsAdminLoginLogDao extends BaseMapper<UmsAdminLoginLogPo> {

}
