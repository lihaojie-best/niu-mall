package com.niu.mall.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.niu.mall.admin.dao.UmsRoleMenuRelationDao;
import com.niu.mall.admin.dao.UmsRoleResourceRelationDao;
import com.niu.mall.common.api.Result;
import com.niu.mall.mbg.po.*;
import com.niu.mall.admin.dao.UmsRoleDao;
import com.niu.mall.admin.service.UmsRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.netty.util.internal.StringUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * <p>
 * 后台用户角色表 服务实现类
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Service
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleDao, UmsRolePo> implements UmsRoleService {
    @Autowired
    private UmsRoleDao umsRoleDao;
    @Autowired
    private UmsRoleResourceRelationDao resourceRelationDao;
    @Autowired
    private UmsRoleMenuRelationDao menuRelationDao;

    /**
     * 获取后台菜单列表
     *
     * @param id
     * @return java.lang.Object
     * @author lihaojie
     * @date 2022/12/30 20:51
     */
    @Override
    public List<UmsMenuPo> getMenuList(Long id) {
        return umsRoleDao.getMenuList(id);
    }

    /**
     * 创建角色
     *
     * @param rolePo 创建实体类
     * @return int
     * @author lihaojie
     * @date 2023/01/01 21:15
     */
    @Override
    public int creat(UmsRolePo rolePo) {
        //1.补全信息
        rolePo.setAdminCount(0);
        rolePo.setSort(0);
        rolePo.setCreateTime(new Date());
        //2.插入
        int count = 0;
        try {
            count = umsRoleDao.insert(rolePo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    /**
     * 更新角色
     *
     * @param id     角色id
     * @param rolePo 角色更新实体类
     * @return int
     * @author lihaojie
     * @date 2023/01/01 21:25
     */
    @Override
    public int updateById(Long id, UmsRolePo rolePo) {
        //设置校色id
        rolePo.setId(id);
        int count = 0;
        try {
            //更新
            count = umsRoleDao.updateById(rolePo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    /**
     * 批量删除
     *
     * @param ids id列表
     * @return int
     * @author lihaojie
     * @date 2023/01/01 21:36
     */
    @Override
    public int deleteBatchByIds(List<Long> ids) {
        int count = 0;
        try {
            count = umsRoleDao.deleteBatchIds(ids);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    /**
     * 根据roleName分页查询
     *
     * @param keyword  关键字
     * @param pageSize 分页大小
     * @param pageNum  分页数量
     * @return java.util.List<com.niu.mall.mbg.po.UmsRolePo>
     * @author lihaojie
     * @date 2023/01/01 21:53
     */
    @Override
    public List<UmsRolePo> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        QueryWrapper<UmsRolePo> whereWrapper = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(keyword)) {
            whereWrapper.like("name", keyword);
        }
        List<UmsRolePo> rolePoList = null;
        try {
            rolePoList = umsRoleDao.selectList(whereWrapper);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rolePoList;
    }

    /**
     * 修改角色状态
     *
     * @param id     roleId
     * @param status 状态
     * @return int
     * @author lihaojie
     * @date 2023/01/01 22:20
     */
    @Override
    public int updateStatus(Long id, Integer status) {
        //创建更新实体类
        UmsRolePo umsRolePo = new UmsRolePo();
        umsRolePo.setId(id);
        umsRolePo.setStatus(status);
        int count = 0;
        try {
            //更新
            count = umsRoleDao.updateById(umsRolePo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    /**
     * 根据roleId查询menu集合
     *
     * @param roleId 角色id
     * @return java.util.List<com.niu.mall.mbg.po.UmsMenuPo>
     * @author lihaojie
     * @date 2023/01/02 10:46
     */
    @Override
    public List<UmsMenuPo> getMenuListByRoleId(Long roleId) {
        List<UmsMenuPo> menuPoList = umsRoleDao.getMenuListByRoleId(roleId);
        return menuPoList;
    }

    /**
     * 根据角色Id获取资源列表
     *
     * @param roleId 角色id
     * @return java.util.List<com.niu.mall.mbg.po.UmsResourcePo>
     * @author lihaojie
     * @date 2023/01/02 10:57
     */
    @Override
    public List<UmsResourcePo> getResourceByRoleId(Long roleId) {
        List<UmsResourcePo> resourcePoList = null;
        try {
            //获取资源信息
            resourcePoList = umsRoleDao.getResourceByRoleId(roleId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resourcePoList;
    }
    /**
     * 给角色分配菜单
     *
     * @param roleId 角色id
     * @param menuIds 菜单id
     * @return int
     * @author lihaojie
     * @date 2023/01/02 11:06
     */
    @Override
    public int allocMenu(Long roleId, List<Long> menuIds) {
        int count=0;
        try {
            //1.先删除以前的菜单
            menuRelationDao.delete(new QueryWrapper<UmsRoleMenuRelationPo>().eq("role_id", roleId));
            //2.插入新的菜单 ->将resourceIds集合封装为UmsRoleResourceRelationPo集合并插入
            menuIds
                    .stream()
                    .map(menuId-> new UmsRoleMenuRelationPo().setRoleId(roleId).setMenuId(menuId))
                    .collect(Collectors.toList())
                    .forEach(umsRoleMenuRelationPo -> menuRelationDao.insert(umsRoleMenuRelationPo));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //3.更新条数
        count=menuIds.size();
        return count;
    }

    @Override
    public int allocResource(Long roleId, List<Long> resourceIds) {
        int count=0;
        try {
            //1.先删除以前的菜单
            resourceRelationDao.delete(new QueryWrapper<UmsRoleResourceRelationPo>().eq("role_id", roleId));
            //2.插入新的菜单 ->将resourceIds集合封装为UmsRoleResourceRelationPo集合并插入
            resourceIds
                    .stream()
                    .map(resourceId -> new UmsRoleResourceRelationPo().setRoleId(roleId).setResourceId(resourceId))
                    .collect(Collectors.toList())
                    .forEach(umsRoleResourceRelationPo -> resourceRelationDao.insert(umsRoleResourceRelationPo));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //3.更新条数
        count=resourceIds.size();
        return count;
    }

}
