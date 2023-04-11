package com.niu.mall.service;

import com.niu.mall.po.UmsMenuPo;
import com.niu.mall.po.UmsResourcePo;
import com.niu.mall.po.UmsRolePo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 创建角色
     *
     * @param rolePo 创建实体类
     * @return int
     * @author lihaojie
     * @date 2023/01/01 21:15
     */
    int creat(UmsRolePo rolePo);

    /**
     * 更新角色
     *
     * @param id     角色id
     * @param rolePo 角色更新实体类
     * @return int
     * @author lihaojie
     * @date 2023/01/01 21:23
     */
    int updateById(Long id, UmsRolePo rolePo);

    /**
     * 批量删除
     *
     * @param ids id列表
     * @return int
     * @author lihaojie
     * @date 2023/01/01 21:36
     */
    int deleteBatchByIds(List<Long> ids);

    /**
     * 根据roleName分页查询
     *
     * @param keyword  关键字
     * @param pageSize 分页大小
     * @param pageNum  分页数量
     * @return java.util.List<UmsRolePo>
     * @author lihaojie
     * @date 2023/01/01 21:52
     */
    List<UmsRolePo> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 修改角色状态
     *
     * @param id     roleId
     * @param status 状态
     * @return int
     * @author lihaojie
     * @date 2023/01/01 22:19
     */
    int updateStatus(Long id, Integer status);

    /**
     * 根据roleId查询menu集合
     *
     * @param roleId 角色id
     * @return java.util.List<UmsMenuPo>
     * @author lihaojie
     * @date 2023/01/02 10:45
     */
    List<UmsMenuPo> getMenuListByRoleId(Long roleId);

    /**
     * 根据角色Id获取资源列表
     *
     * @param roleId 角色id
     * @return java.util.List<UmsResourcePo>
     * @author lihaojie
     * @date 2023/01/02 10:57
     */
    List<UmsResourcePo> getResourceByRoleId(Long roleId);

    /**
     * 给角色分配菜单
     *
     * @param roleId  角色id
     * @param menuIds 菜单id
     * @return int
     * @author lihaojie
     * @date 2023/01/02 11:05
     */
    @Transactional
    int allocMenu(Long roleId, List<Long> menuIds);

    @Transactional
    int allocResource(Long roleId, List<Long> resourceIds);
}
