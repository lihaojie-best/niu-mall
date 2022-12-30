package com.niu.mall.admin.service;

import com.niu.mall.mbg.po.UmsMenuPo;
import com.niu.mall.mbg.po.UmsRolePo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 服务类
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
public interface UmsRoleService extends IService<UmsRolePo> {
    /**
     * 获取后台菜单列表
     *
     * @param id
     * @return java.lang.Object
     * @author lihaojie
     * @date 2022/12/30 20:50
     */
    List<UmsMenuPo> getMenuList(Long id);
}
