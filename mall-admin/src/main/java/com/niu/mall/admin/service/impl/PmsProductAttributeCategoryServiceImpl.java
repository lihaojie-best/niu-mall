package com.niu.mall.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.niu.mall.admin.dto.PmsProductAttributeCategoryDto;
import com.niu.mall.mbg.po.PmsProductAttributeCategoryPo;
import com.niu.mall.admin.dao.PmsProductAttributeCategoryDao;
import com.niu.mall.admin.service.PmsProductAttributeCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 服务实现类
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Service
public class PmsProductAttributeCategoryServiceImpl extends ServiceImpl<PmsProductAttributeCategoryDao, PmsProductAttributeCategoryPo> implements PmsProductAttributeCategoryService {

    @Autowired
    private PmsProductAttributeCategoryDao productAttributeCategoryDao;

    /**
     * 分页查询属性分类
     *
     * @param pageNum
     * @param pageSize
     * @return java.util.List<com.niu.mall.mbg.po.PmsProductAttributeCategoryPo>
     * @author lihaojie
     * @date 2022/12/10 21:39
     */
    @Override
    public List<PmsProductAttributeCategoryPo> getAll(Integer pageNum, Integer pageSize) {
        //声明PmsProductAttributeCategoryPo
        List<PmsProductAttributeCategoryPo> pmsProductAttributeCategoryPoList;
        try {
            //开启分页助手
            Page<PmsProductAttributeCategoryPo> pmsProductAttributeCategoryPoPage = PageHelper.startPage(pageNum, pageSize);
            //获取全部
            pmsProductAttributeCategoryPoList = productAttributeCategoryDao.selectList(new QueryWrapper<>());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return pmsProductAttributeCategoryPoList;
    }

    /**
     * 获取包含属性的属性分类
     *
     * @return java.util.List<com.niu.mall.admin.dto.PmsProductAttributeCategoryDto>
     * @author lihaojie
     * @date 2022/12/10 21:38
     */
    @Override
    public List<PmsProductAttributeCategoryDto> getListWithAttr() {
        List<PmsProductAttributeCategoryDto> pmsProductAttributeCategoryDtoList;
        try {
            pmsProductAttributeCategoryDtoList = productAttributeCategoryDao.getListWithAttr();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return pmsProductAttributeCategoryDtoList;
    }
}
