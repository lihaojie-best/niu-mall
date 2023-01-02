package com.niu.mall.admin.dao;

import com.niu.mall.mbg.po.UmsMenuPo;
import com.niu.mall.mbg.po.UmsResourcePo;
import com.niu.mall.mbg.po.UmsRolePo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 Mapper 接口
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Mapper
public interface UmsRoleDao extends BaseMapper<UmsRolePo> {
    /**
     * 获取后台菜单列表
     *
     * @param id
     * @return java.util.List<com.niu.mall.mbg.po.UmsMenuPo>
     * @author lihaojie
     * @date 2022/12/30 20:54
     */
    List<UmsMenuPo> getMenuList(@Param("adminId") Long id);
    /**
     * 根据角色id获取菜单列表
     *
     * @param roleId 角色id
     * @return java.util.List<com.niu.mall.mbg.po.UmsMenuPo>
     * @author lihaojie
     * @date 2023/01/02 10:48
     */
    List<UmsMenuPo> getMenuListByRoleId(@Param("roleId") Long roleId);
    /**
     * 根据角色id获取资源列表
     *
     * @param roleId 角色id
     * @return java.util.List<com.niu.mall.mbg.po.UmsResourcePo>
     * @author lihaojie
     * @date 2023/01/02 11:01
     */
    List<UmsResourcePo> getResourceByRoleId(@Param("roleId") Long roleId);
}
