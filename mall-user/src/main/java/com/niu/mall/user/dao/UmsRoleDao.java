package com.niu.mall.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niu.mall.user.po.UmsMenuPo;
import com.niu.mall.user.po.UmsResourcePo;
import com.niu.mall.user.po.UmsRolePo;
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
     * @return java.util.List<UmsMenuPo>
     * @author lihaojie
     * @date 2022/12/30 20:54
     */
    List<UmsMenuPo> getMenuList(@Param("adminId") Long id);
    /**
     * 根据角色id获取菜单列表
     *
     * @param roleId 角色id
     * @return java.util.List<UmsMenuPo>
     * @author lihaojie
     * @date 2023/01/02 10:48
     */
    List<UmsMenuPo> getMenuListByRoleId(@Param("roleId") Long roleId);
    /**
     * 根据角色id获取资源列表
     *
     * @param roleId 角色id
     * @return java.util.List<UmsResourcePo>
     * @author lihaojie
     * @date 2023/01/02 11:01
     */
    List<UmsResourcePo> getResourceByRoleId(@Param("roleId") Long roleId);
}
