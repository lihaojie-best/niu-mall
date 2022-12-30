package com.niu.mall.admin.service;

import com.niu.mall.mbg.po.UmsAdminPo;
import com.niu.mall.mbg.po.UmsResourcePo;

import java.util.List;

/**
 * 后台用户缓存操作Service
 * 
 * @author lihaojie
 * @date 2022/12/29 19:21
 **/
public interface UmsAdminCacheService {
    /**
     * 删除后台用户缓存
     */
    void delAdmin(Long adminId);
    /**
     * 删除后台用户资源列表缓存
     */
    void delResourceList(Long adminId);

    /**
     * 当角色资源发生改变时删除缓存
     */
    void delResourceListByRole(Long roleId);
    /**
     * 当角色相关资源信息改变时删除相关后台用户缓存
     */
    void delResourceListByRoleIds(List<Long> roleIds);
    /**
     * 当资源信息改变时，删除资源项目后台用户缓存
     */
    void delResourceListByResource(Long resourceId);
    /**
     * 获取缓存后台用户信息
     */
    UmsAdminPo getAdmin(String username);
    /**
     * 设置缓存后台用户信息
     */
    void setAdmin(UmsAdminPo admin);

    /**
     * 获取缓存后台用户资源列表
     */
    List<UmsResourcePo> getResourceList(Long adminId);

    /**
     * 设置缓存后台用户资源列表
     */
    void setResourceList(Long adminId, List<UmsResourcePo> resourceList);
}
