package com.niu.mall.user.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.niu.mall.user.dao.*;
import com.niu.mall.user.domain.PmsPortalProductDetail;
import com.niu.mall.user.domain.PmsProductCategoryNode;
import com.niu.mall.user.po.*;
import com.niu.mall.user.service.PmsPortalProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static jdk.nashorn.api.scripting.ScriptUtils.convert;

/**
 * 前台订单管理Service实现类
 * Created by lihaojie on 2023/4/6.
 */
@Service
public class PmsPortalProductServiceImpl implements PmsPortalProductService {
    @Autowired
    private PmsProductDao productMapper;
    @Autowired
    private PmsProductCategoryDao productCategoryMapper;
    @Autowired
    private PmsBrandDao brandMapper;
    @Autowired
    private PmsProductAttributeDao productAttributeMapper;
    @Autowired
    private PmsProductAttributeValueDao productAttributeValueMapper;
    @Autowired
    private PmsSkuStockDao skuStockMapper;
    @Autowired
    private PmsProductLadderDao productLadderMapper;
    @Autowired
    private PmsProductFullReductionDao productFullReductionMapper;
    @Autowired
    private PortalProductDao portalProductDao;

    @Override
    public List<PmsProductPo> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize, Integer sort) {
        PageHelper.startPage(pageNum, pageSize);
        QueryWrapper<PmsProductPo> wrapper = new QueryWrapper<>();
        wrapper.eq("delete_status", 0);

        if (StrUtil.isNotEmpty(keyword)) {
            wrapper.like("name", keyword);
        }
        if (brandId != null) {
            wrapper.eq("brand_id", brandId);
        }
        if (productCategoryId != null) {
            wrapper.eq("product_category_id", productCategoryId);
        }

        // 排序规则
        if (sort != null) {
            switch (sort) {
                case 1:
                    wrapper.orderByDesc("id");
                    break;
                case 2:
                    wrapper.orderByDesc("sale");
                    break;
                case 3:
                    wrapper.orderByAsc("price");
                    break;
                case 4:
                    wrapper.orderByDesc("price");
                    break;
            }
        }

        return productMapper.selectList(wrapper);

    }

    @Override
    public List<PmsProductCategoryNode> categoryTreeList() {
        // 查询所有商品分类
        List<PmsProductCategoryPo> allList = productCategoryMapper.selectList(null);

        // 构建树形结构，只保留 parentId = 0 的根节点
        List<PmsProductCategoryNode> result = allList.stream()
                .filter(item -> item.getParentId() == 0)
                .map(item -> (PmsProductCategoryNode) convert(item, allList))
                .collect(Collectors.toList());

        return result;

    }

    @Override
    public PmsPortalProductDetail detail(Long id) {
        PmsPortalProductDetail result = new PmsPortalProductDetail();
        //获取商品信息
        PmsProductPo product = productMapper.selectById(id);
        result.setProduct(product);
        //获取品牌信息
        PmsBrandPo brand = brandMapper.selectById(product.getBrandId());
        result.setBrand(brand);
        //获取商品属性信息
        // 获取商品属性列表
        List<PmsProductAttributePo> productAttributeList = productAttributeMapper.selectList(
                new LambdaQueryWrapper<PmsProductAttributePo>()
                        .eq(PmsProductAttributePo::getProductAttributeCategoryId, product.getProductAttributeCategoryId())
        );
        result.setProductAttributeList(productAttributeList);

// 获取商品属性值信息
        if (CollUtil.isNotEmpty(productAttributeList)) {
            List<Long> attributeIds = productAttributeList.stream()
                    .map(PmsProductAttributePo::getId)
                    .collect(Collectors.toList());

            List<PmsProductAttributeValuePo> productAttributeValueList = productAttributeValueMapper.selectList(
                    new LambdaQueryWrapper<PmsProductAttributeValuePo>()
                            .eq(PmsProductAttributeValuePo::getProductId, product.getId())
                            .in(PmsProductAttributeValuePo::getProductAttributeId, attributeIds)
            );
            result.setProductAttributeValueList(productAttributeValueList);
        }

// 获取商品SKU库存信息
        List<PmsSkuStockPo> skuStockList = skuStockMapper.selectList(
                new LambdaQueryWrapper<PmsSkuStockPo>()
                        .eq(PmsSkuStockPo::getProductId, product.getId())
        );
        result.setSkuStockList(skuStockList);

// 商品阶梯价格设置（promotionType == 3）
        if (product.getPromotionType() == 3) {
            List<PmsProductLadderPo> productLadderList = productLadderMapper.selectList(
                    new LambdaQueryWrapper<PmsProductLadderPo>()
                            .eq(PmsProductLadderPo::getProductId, product.getId())
            );
            result.setProductLadderList(productLadderList);
        }

// 商品满减价格设置（promotionType == 4）
        if (product.getPromotionType() == 4) {
            List<PmsProductFullReductionPo> productFullReductionList = productFullReductionMapper.selectList(
                    new LambdaQueryWrapper<PmsProductFullReductionPo>()
                            .eq(PmsProductFullReductionPo::getProductId, product.getId())
            );
            result.setProductFullReductionList(productFullReductionList);
        }

        //商品可用优惠券
        result.setCouponList(portalProductDao.getAvailableCouponList(product.getId(), product.getProductCategoryId()));
        return result;
    }


    /**
     * 初始对象转化为节点对象
     */
    private PmsProductCategoryNode covert(PmsProductCategoryPo item, List<PmsProductCategoryPo> allList) {
        PmsProductCategoryNode node = new PmsProductCategoryNode();
        BeanUtils.copyProperties(item, node);
        List<PmsProductCategoryNode> children = allList.stream()
                .filter(subItem -> subItem.getParentId().equals(item.getId()))
                .map(subItem -> covert(subItem, allList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}
