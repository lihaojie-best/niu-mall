package com.niu.mall.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.niu.mall.common.api.CommonPage;
import com.niu.mall.user.dao.HomeDao;
import com.niu.mall.user.dao.PmsBrandDao;
import com.niu.mall.user.dao.PmsProductDao;
import com.niu.mall.user.po.PmsBrandPo;
import com.niu.mall.user.po.PmsProductPo;
import com.niu.mall.user.service.PortalBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 前台品牌管理Service实现类
 * Created by macro on 2020/5/15.
 */
@Service
public class PortalBrandServiceImpl implements PortalBrandService {
    @Autowired
    private HomeDao homeDao;
    @Autowired
    private PmsBrandDao brandDao;
    @Autowired
    private PmsProductDao productMapper;

    @Override
    public List<PmsBrandPo> recommendList(Integer pageNum, Integer pageSize) {
        int offset = (pageNum - 1) * pageSize;
        return homeDao.getRecommendBrandList(offset, pageSize);
    }

    @Override
    public PmsBrandPo detail(Long brandId) {
        return brandDao.selectById(brandId);
    }

    @Override
    public CommonPage<PmsProductPo> productList(Long brandId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<PmsProductPo> productList = productMapper.selectList(
                new LambdaQueryWrapper<PmsProductPo>()
                        .eq(PmsProductPo::getDeleteStatus, 0)
                        .eq(PmsProductPo::getBrandId, brandId)
        );

        return CommonPage.restPage(productList);
    }
}
