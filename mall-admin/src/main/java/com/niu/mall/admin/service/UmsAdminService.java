package com.niu.mall.admin.service;

import com.niu.mall.admin.param.UmsAdminParam;
import com.niu.mall.admin.param.UmsAdminLoginParam;
import com.niu.mall.admin.param.UmsUpdateAdminPasswordParam;
import com.niu.mall.mbg.po.UmsAdminPo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.niu.mall.mbg.po.UmsRolePo;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
public interface UmsAdminService extends IService<UmsAdminPo> {
    /**
     * 用户注册
     *
     * @param umsAdminParam 用户注册参数
     * @return com.niu.mall.mbg.po.UmsAdminPo
     * @author lihaojie
     * @date 2022/12/21 21:27
     */
    UmsAdminPo register(UmsAdminParam umsAdminParam);
    /**
     * 用户登录并返回token
     *
     * @param umsAdminLoginParam 用户登录参数
     * @return java.lang.String
     * @author lihaojie
     * @date 2022/12/29 15:16
     */
    String login(UmsAdminLoginParam umsAdminLoginParam);

    /**
     * 获取缓存服务
     *
     * @return com.niu.mall.admin.service.UmsAdminCacheService
     * @author lihaojie
     * @date 2022/12/29 21:01
     */
    UmsAdminCacheService getCacheService();
    /**
     * 刷新token
     *
     * @param oldToken
     * @return java.lang.String
     * @author lihaojie
     * @date 2022/12/30 19:10
     */
    String refreshToken(String oldToken);
    /**
     * 根据用户名获取用户信息
     * 
     * @param name 
     * @return com.niu.mall.mbg.po.UmsAdminPo
     * @author lihaojie
     * @date 2022/12/30 20:48
     */
    UmsAdminPo getAdminByUsername(String name);
    /**
     * 根据adminId查询RoleList
     *
     * @param id
     * @return java.util.List<com.niu.mall.mbg.po.UmsRolePo>
     * @author lihaojie
     * @date 2022/12/30 21:16
     */
    List<UmsRolePo> getRoleList(Long id);
    /**
     * 根据用户名或姓名模糊分页查询
     * 
     * @param keyword 关键字
     * @param pageSize 分页大小
     * @param pageNum 当前页
     * @return java.util.List<com.niu.mall.mbg.po.UmsAdminPo>
     * @author lihaojie
     * @date 2022/12/30 21:32
     */
    List<UmsAdminPo> list(String keyword, Integer pageSize, Integer pageNum);
    /**
     * 修改指定用户信息
     *
     * @param id
     * @param adminPo
     * @return int
     * @author lihaojie
     * @date 2022/12/30 22:15
     */
    int update(Long id, UmsAdminPo adminPo);
    /**
     * 修改密码
     *
     * @param updateAdminPasswordParam
     * @return int
     * @author lihaojie
     * @date 2022/12/30 22:36
     */
    int updatePassword(UmsUpdateAdminPasswordParam updateAdminPasswordParam);
    /**
     * 根据id删除用户
     *
     * @param id adminId
     * @return int
     * @author lihaojie
     * @date 2022/12/30 22:54
     */
    int delete(Long id);
    /**
     * 更新用户校色关系
     *
     * @param adminId
     * @param roleIds
     * @return int
     * @author lihaojie
     * @date 2022/12/30 23:08
     */
    int updateRole(Long adminId, List<Long> roleIds);

    /**
     *  获取用户信息
     *
     * @param username 用户名
     * @return org.springframework.security.core.userdetails.UserDetails
     * @author lihaojie
     * @date 2023/01/15 17:16
     */
    UserDetails loadUserByUsername(String username);
}
