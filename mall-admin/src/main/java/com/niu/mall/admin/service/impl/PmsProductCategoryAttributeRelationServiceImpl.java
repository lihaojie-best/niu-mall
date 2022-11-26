package com.niu.mall.admin.service.impl;

import com.niu.mall.mbg.po.PmsProductCategoryAttributeRelationPo;
import com.niu.mall.admin.dao.PmsProductCategoryAttributeRelationDao;
import com.niu.mall.admin.service.PmsProductCategoryAttributeRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类） 服务实现类
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Service
public class PmsProductCategoryAttributeRelationServiceImpl extends ServiceImpl<PmsProductCategoryAttributeRelationDao, PmsProductCategoryAttributeRelationPo> implements PmsProductCategoryAttributeRelationService {

}
