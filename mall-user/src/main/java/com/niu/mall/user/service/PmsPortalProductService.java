package com.niu.mall.user.service;



import com.niu.mall.user.domain.PmsPortalProductDetail;
import com.niu.mall.user.domain.PmsProductCategoryNode;
import com.niu.mall.user.po.PmsProductPo;

import java.util.List;

/**
 * 前台商品管理Service
 * Created by lihaojie on 2023/4/6.
 */
public interface PmsPortalProductService {
    /**
     * 综合搜索商品
     */
    List<PmsProductPo> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize, Integer sort);

    /**
     * 以树形结构获取所有商品分类
     */
    List<PmsProductCategoryNode> categoryTreeList();

    /**
     * 获取前台商品详情
     */
    PmsPortalProductDetail detail(Long id);
}
