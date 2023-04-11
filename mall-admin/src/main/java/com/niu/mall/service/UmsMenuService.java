package com.niu.mall.service;

import com.niu.mall.dto.UmsMenuNodeDto;
import com.niu.mall.po.UmsMenuPo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 后台菜单表 服务类
 *
 * @author lihaojie
 * @date 2023/01/02 15:11
 **/
public interface UmsMenuService extends IService<UmsMenuPo> {
    /**
     * 新增菜单
     *
     * @param umsMenuPo 菜单实体类
     * @return int
     * @author lihaojie
     * @date 2023/01/02 14:21
     */
    int create(UmsMenuPo umsMenuPo);
    /**
     * 修改后台菜单
     *
     * @param id 菜单id
     * @param umsMenu 菜单实体类
     * @return int
     * @author lihaojie
     * @date 2023/01/02 14:28
     */
    int update(Long id, UmsMenuPo umsMenu);
    /**
     * 根据上级分类id分页查询子菜单
     *
     * @param parentId 上级分类id
     * @param pageSize 分页大小
     * @param pageNum 当前分页数
     * @return java.util.List<UmsMenuPo>
     * @author lihaojie
     * @date 2023/01/02 14:35
     */
    List<UmsMenuPo> list(Long parentId, Integer pageSize, Integer pageNum);
    /**
     * 树形结构返回所有菜单列表
     *
     * @return java.util.List<UmsMenuNodeDto>
     * @author lihaojie
     * @date 2023/01/02 14:48
     */
    List<UmsMenuNodeDto> treeList();

    /**
     * 修改菜单显示状态
     *
     * @param id 菜单id
     * @param hidden 显示状态
     * @return int
     * @author lihaojie
     * @date 2023/01/02 15:09
     */
    int updateHidden(Long id, Integer hidden);
}
