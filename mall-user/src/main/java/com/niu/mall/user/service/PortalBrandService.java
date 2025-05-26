package com.niu.mall.user.service;

import com.niu.mall.common.api.CommonPage;
import com.niu.mall.user.po.PmsBrandPo;
import com.niu.mall.user.po.PmsProductPo;

import java.util.List;

/**
 * 前台品牌管理Service
 * Created by lihaojie on 2023/5/15.
 */
public interface PortalBrandService {
    /**
     * 分页获取推荐品牌
     */
    List<PmsBrandPo> recommendList(Integer pageNum, Integer pageSize);

    /**
     * 获取品牌详情
     */
    PmsBrandPo detail(Long brandId);

    /**
     * 分页获取品牌关联商品
     */
    CommonPage<PmsProductPo> productList(Long brandId, Integer pageNum, Integer pageSize);
}
