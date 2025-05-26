package com.niu.mall.user.service;

import com.niu.mall.user.domain.HomeContentResult;
import com.niu.mall.user.po.CmsSubjectPo;
import com.niu.mall.user.po.PmsProductCategoryPo;
import com.niu.mall.user.po.PmsProductPo;

import java.util.List;

/**
 * 首页内容管理Service
 * Created by lihaojie on 2023/1/28.
 */
public interface HomeService {

    /**
     * 获取首页内容
     */
    HomeContentResult content();

    /**
     * 首页商品推荐
     */
    List<PmsProductPo> recommendProductList(Integer pageSize, Integer pageNum);

    /**
     * 获取商品分类
     * @param parentId 0:获取一级分类；其他：获取指定二级分类
     */
    List<PmsProductCategoryPo> getProductCateList(Long parentId);

    /**
     * 根据专题分类分页获取专题
     * @param cateId 专题分类id
     */
    List<CmsSubjectPo> getSubjectList(Long cateId, Integer pageSize, Integer pageNum);

    /**
     * 分页获取人气推荐商品
     */
    List<PmsProductPo> hotProductList(Integer pageNum, Integer pageSize);

    /**
     * 分页获取新品推荐商品
     */
    List<PmsProductPo> newProductList(Integer pageNum, Integer pageSize);
}
