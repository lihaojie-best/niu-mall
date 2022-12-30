package com.niu.mall.admin.dao;

import com.niu.mall.mbg.po.UmsMenuPo;
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
}
