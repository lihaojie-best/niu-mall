package com.niu.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.niu.mall.dto.UmsMenuNodeDto;
import com.niu.mall.po.UmsMenuPo;
import com.niu.mall.dao.UmsMenuDao;
import com.niu.mall.service.UmsMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 后台菜单表 服务实现类
 *
 * @author lihaojie
 * @date 2023/01/02 15:12
 **/
@Service
public class UmsMenuServiceImpl extends ServiceImpl<UmsMenuDao, UmsMenuPo> implements UmsMenuService {
    @Autowired
    private UmsMenuDao menuDao;
    /**
     * 新增菜单
     *
     * @param umsMenuPo 菜单实体类
     * @return int
     * @author lihaojie
     * @date 2023/01/02 14:21
     */
    @Override
    public int create(UmsMenuPo umsMenuPo) {
        int count = 0;
        try {
            //1.补全创建时间
            umsMenuPo.setCreateTime(new Date());
            //2.更新菜单分类层级
            updateLevel(umsMenuPo);
            //3.插入菜单
            count = menuDao.insert(umsMenuPo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return count;
    }
    /**
     * 修改后台菜单
     *
     * @param id 菜单id
     * @param umsMenu 菜单实体类
     * @return int
     * @author lihaojie
     * @date 2023/01/02 14:28
     */
    @Override
    public int update(Long id, UmsMenuPo umsMenu) {
        int count = 0;
        try {
            //1.补全id
            umsMenu.setId(id);
            //2.更新菜单等级
            updateLevel(umsMenu);
            //3.插入
            count = menuDao.updateById(umsMenu);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return count;
    }
    /**
     * 根据上级分类id分页查询子菜单
     *
     * @param parentId 上级分类id
     * @param pageSize 分页大小
     * @param pageNum 当前分页数
     * @return java.util.List<UmsMenuPo>
     * @author lihaojie
     * @date 2023/01/02 14:36
     */
    @Override
    public List<UmsMenuPo> list(Long parentId, Integer pageSize, Integer pageNum) {
        List<UmsMenuPo> menuPoList = null;
        try {
            //开启分页助手
            PageHelper.startPage(pageNum, pageSize);
            //查询菜单
            menuPoList = menuDao.selectList(new QueryWrapper<UmsMenuPo>().eq("parent_id", parentId));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return menuPoList;
    }
    /**
     * 树形结构返回所有菜单列表
     *
     * @return java.util.List<UmsMenuNodeDto>
     * @author lihaojie
     * @date 2023/01/02 14:48
     */
    @Override
    public List<UmsMenuNodeDto> treeList() {
        //获取全部menu
        List<UmsMenuPo> menuPoList = menuDao.selectList(new QueryWrapper<>());
        List<UmsMenuNodeDto> umsMenuNodeDtoList = menuPoList.stream()
                //过滤出一级菜单
                .filter(menu -> menu.getParentId().equals(0L))
                //调用covertMenuNode将一级菜单转化为节点类并补充子菜单
                .map(menu -> covertMenuNode(menu, menuPoList))
                .collect(Collectors.toList());
        return umsMenuNodeDtoList;
    }
    /**
     * 修改菜单显示状态
     *
     * @param id 菜单id
     * @param hidden 显示状态
     * @return int
     * @author lihaojie
     * @date 2023/01/02 15:09
     */
    @Override
    public int updateHidden(Long id, Integer hidden) {
        UmsMenuPo umsMenu = new UmsMenuPo();
        umsMenu.setId(id);
        umsMenu.setHidden(hidden);
        int count = 0;
        try {
            //更新
            count = menuDao.updateById(umsMenu);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    /**
     * 修改菜单层级
     *
     * @param umsMenu 菜单
     * @author lihaojie
     * @date 2023/01/02 15:11
     */
    private void updateLevel(UmsMenuPo umsMenu) {
        if (umsMenu.getParentId() == 0) {
            //没有父菜单时为一级菜单
            umsMenu.setLevel(0);
        } else {
            //有父菜单时选择根据父菜单level设置
            UmsMenuPo parentMenu = menuDao.selectById(umsMenu.getParentId());
            if (parentMenu != null) {
                umsMenu.setLevel(parentMenu.getLevel() + 1);
            } else {
                umsMenu.setLevel(0);
            }
        }
    }
    /**
     * 菜单转化为节点类并补充子菜单
     *
     * @param menu 父菜单
     * @param menuList 所有菜单
     * @return UmsMenuNodeDto
     * @author lihaojie
     * @date 2023/01/02 15:07
     */
    private UmsMenuNodeDto covertMenuNode(UmsMenuPo menu, List<UmsMenuPo> menuList) {
        //创建一个节点
        UmsMenuNodeDto node = new UmsMenuNodeDto();
        BeanUtils.copyProperties(menu, node);
        //抽离出Children属性
        List<UmsMenuNodeDto> children = menuList.stream()
                .filter(subMenu -> subMenu.getParentId().equals(menu.getId()))
                .map(subMenu -> covertMenuNode(subMenu, menuList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}
