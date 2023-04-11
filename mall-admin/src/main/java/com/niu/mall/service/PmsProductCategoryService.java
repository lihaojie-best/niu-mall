package com.niu.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.niu.mall.dto.PmsProductCategoryWithChildrenDto;
import com.niu.mall.param.PmsProductCategoryParam;
import com.niu.mall.po.PmsProductCategoryPo;

import java.util.List;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
public interface PmsProductCategoryService extends IService<PmsProductCategoryPo> {
    /**
     * 新增商品分类
     *
     * @param productCategoryParam 新增商品实体类
     * @return int
     * @author lihaojie
     * @date 2022/12/18 19:05
     */
    int create(PmsProductCategoryParam productCategoryParam);

    /**
     * 修改商品分类
     *
     * @param id                   商品分类id
     * @param productCategoryParam 修改参数
     * @return int
     * @author lihaojie
     * @date 2022/12/18 20:11
     */
    int update(Long id, PmsProductCategoryParam productCategoryParam);

    /**
     * 根据分类的父级id分页查询子分类
     *
     * @param parentId 父分类id
     * @param pageNum  当前分页
     * @param pageSize 分页大小
     * @return java.util.List<PmsProductCategoryPo>
     * @author lihaojie
     * @date 2022/12/19 10:45
     */
    List<PmsProductCategoryPo> listByParentId(Long parentId, Integer pageNum, Integer pageSize);

    /**
     * 查询所有一级分类及子分类
     *
     * @return java.util.List<PmsProductCategoryWithChildrenDto>
     * @author lihaojie
     * @date 2022/12/19 11:13
     */
    List<PmsProductCategoryWithChildrenDto> getWithChildren();
}
