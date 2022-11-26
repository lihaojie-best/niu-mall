package com.niu.mall.admin.dao;

import com.niu.mall.mbg.po.UmsAdminPermissionRelationPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 后台用户和权限关系表(除角色中定义的权限以外的加减权限) Mapper 接口
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Mapper
public interface UmsAdminPermissionRelationDao extends BaseMapper<UmsAdminPermissionRelationPo> {

}
