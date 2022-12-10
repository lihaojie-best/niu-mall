package com.niu.mall.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.niu.mall.admin.dao.PmsBrandDao;
import com.niu.mall.admin.service.PmsBrandService;
import com.niu.mall.common.exception.ApiException;
import com.niu.mall.mbg.po.PmsBrandPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 品牌表 服务实现类
 *
 * @author lihaojie
 * @date 2022/12/07 23:00
 **/
@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandDao, PmsBrandPo> implements PmsBrandService {
    @Autowired
    private PmsBrandDao brandDao;

    /**
     * 创建品牌
     *
     * @param brand 品牌实体类
     * @return int
     * @author lihaojie
     * @date 2022/12/07 11:13
     */
    @Override
    public int creat(PmsBrandPo brand) {
        int insert = 0;
        try {
            //将更新参数的id设置为空
            brand.setId(null);
            insert = brandDao.insert(brand);
        } catch (Exception e) {
            throw new ApiException(e);
        }
        return insert;
    }

    /**
     * 模糊查询
     *
     * @param keyword  关键字
     * @param pageNum  当前页
     * @param pageSize 页面大小
     * @return java.util.List<com.niu.mall.mbg.po.PmsBrandPo>
     * @author lihaojie
     * @date 2022/12/06 21:24
     */
    @Override
    public List<PmsBrandPo> listByKeyword(String keyword, int pageNum, int pageSize) {
        try {
            Page<Object> objectPage = PageHelper.startPage(pageNum, pageSize);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        QueryWrapper whereWrapper = new QueryWrapper<>();
        if (!StrUtil.isEmpty(keyword)) {
            whereWrapper.like("name", keyword);
        }
        List<PmsBrandPo> pmsBrandPoList = brandDao.selectList(whereWrapper);
        return pmsBrandPoList;
    }
}
