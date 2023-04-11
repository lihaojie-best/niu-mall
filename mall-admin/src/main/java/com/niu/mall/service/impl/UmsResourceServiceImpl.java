package com.niu.mall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.niu.mall.service.UmsAdminCacheService;
import com.niu.mall.po.UmsResourcePo;
import com.niu.mall.dao.UmsResourceDao;
import com.niu.mall.service.UmsResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * 后台资源表 服务实现类
 *
 * @author lihaojie
 * @date 2023/01/02 18:42
 **/
@Service
public class UmsResourceServiceImpl extends ServiceImpl<UmsResourceDao, UmsResourcePo> implements UmsResourceService {
    @Autowired
    private UmsResourceDao resourceDao;
    @Autowired
    private UmsAdminCacheService adminCacheService;

    /**
     * 添加后台资源
     *
     * @param umsResource 后台资源实体类
     * @return int
     * @author lihaojie
     * @date 2023/01/02 18:41
     */
    @Override
    public int create(UmsResourcePo umsResource) {
        //补全创建日期
        umsResource.setCreateTime(new Date());
        int count = 0;
        try {
            //插入
            count = resourceDao.insert(umsResource);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    /**
     * 修改后台资源
     *
     * @param id         资源id
     * @param resourcePo 资源更新实体类
     * @return int
     * @author lihaojie
     * @date 2023/01/02 18:47
     */
    @Override
    public int updateById(Long id, UmsResourcePo resourcePo) {
        int count = 0;
        try {
            //补全id
            resourcePo.setId(id);
            //更新
            count = resourceDao.updateById(resourcePo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    /**
     * 根据id删除
     *
     * @param id id
     * @return int
     * @author lihaojie
     * @date 2023/01/02 18:57
     */
    @Override
    public int deleteById(Long id) {
        int count = 0;
        try {
            //删除数据库
            count = resourceDao.deleteById(id);
            //删除缓存
            adminCacheService.delResourceListByResource(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    /**
     * 分页模糊查询后台资源
     *
     * @param categoryId  分类id
     * @param nameKeyword name关键字
     * @param urlKeyword  url关键字
     * @param pageSize    分页大小
     * @param pageNum     当前页
     * @return java.util.List<UmsResourcePo>
     * @author lihaojie
     * @date 2023/01/02 19:10
     */
    @Override
    public List<UmsResourcePo> list(@RequestParam(required = false) Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        QueryWrapper<UmsResourcePo> whereWrapper = new QueryWrapper<>();
        if (categoryId != null) {
            whereWrapper.eq("id", categoryId);
        }
        if (nameKeyword != null && !nameKeyword.isEmpty()) {
            whereWrapper.like("name", nameKeyword);
        }
        if (urlKeyword != null && !urlKeyword.isEmpty()) {
            whereWrapper.like("url", urlKeyword);
        }
        return resourceDao.selectList(whereWrapper);
    }

    /**
     * 查询全部
     *
     * @return java.util.List<UmsResourcePo>
     * @author lihaojie
     * @date 2023/01/16 13:21
     */
    @Override
    public List<UmsResourcePo> listAll() {
        return resourceDao.getAll();
    }

}
