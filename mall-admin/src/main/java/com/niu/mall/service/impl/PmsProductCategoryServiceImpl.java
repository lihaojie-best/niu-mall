package com.niu.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.niu.mall.dao.PmsProductCategoryAttributeRelationDao;
import com.niu.mall.dao.PmsProductCategoryDao;
import com.niu.mall.dao.PmsProductDao;
import com.niu.mall.dto.PmsProductCategoryWithChildrenDto;
import com.niu.mall.param.PmsProductCategoryParam;
import com.niu.mall.service.PmsProductCategoryService;
import com.niu.mall.common.exception.ApiException;
import com.niu.mall.po.PmsProductCategoryAttributeRelationPo;
import com.niu.mall.po.PmsProductCategoryPo;
import com.niu.mall.po.PmsProductPo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 产品分类 服务实现类
 *
 * @author lihaojie
 * @date 2022/12/18 20:39
 **/
@Service
public class PmsProductCategoryServiceImpl extends ServiceImpl<PmsProductCategoryDao, PmsProductCategoryPo> implements PmsProductCategoryService {

    @Autowired
    private PmsProductCategoryDao productCategoryDao;
    @Autowired
    private PmsProductDao productDao;
    @Autowired
    private PmsProductCategoryAttributeRelationDao categoryAttributeRelationDao;

    /**
     * 新增商品分类
     *
     * @param productCategoryParam 新增商品实体类
     * @return int
     * @author lihaojie
     * @date 2022/12/18 19:06
     */
    @Override
    public int create(PmsProductCategoryParam productCategoryParam) {
        //1.将param --转换--> po
        PmsProductCategoryPo productCategoryPo = new PmsProductCategoryPo();
        BeanUtils.copyProperties(productCategoryParam, productCategoryPo);
        //2.补全数量属性 ==0
        productCategoryPo.setProductCount(0);
        //3.没有父分类时设为一级分类
        setCategoryLevel(productCategoryPo);
        //4.创建筛选属性关联
        List<Long> productAttributeIdList = productCategoryParam.getProductAttributeIdList();
        if (!CollectionUtils.isEmpty(productAttributeIdList)) {
            insertRelationList(productCategoryPo.getId(), productAttributeIdList);
        }
        return productCategoryDao.insert(productCategoryPo);
    }

    /**
     * 修改商品分类
     *
     * @param id                   商品分类id
     * @param productCategoryParam 修改参数
     * @return int
     * @author lihaojie
     * @date 2022/12/18 20:12
     */
    @Override
    public int update(Long id, PmsProductCategoryParam productCategoryParam) {
        //1.类型转换
        PmsProductCategoryPo productCategoryPo = new PmsProductCategoryPo();
        BeanUtils.copyProperties(productCategoryParam, productCategoryPo);
        //根据id更新时,需要再实体类中查询
        productCategoryPo.setId(id);
        //2.设置级别
        setCategoryLevel(productCategoryPo);
        //3.根据id查找名字,对比是否更新,更新了需要修改有关产品的分类名称
        //3.1获取数据库中的名字
        PmsProductCategoryPo productCategoryPoDb = productCategoryDao.selectById(id);

        //3.2 对比名字是否发生变化
        if (!productCategoryPoDb.getName().equals(productCategoryPo.getName())) {
            //3.3 根据商品分类称查找并更新商品分类名
            //3.3.1 设置查询标准
            QueryWrapper<PmsProductPo> whereWrapper = new QueryWrapper<PmsProductPo>()
                    .eq("product_category_name", productCategoryPoDb.getName());
            PmsProductPo productPo = new PmsProductPo().setProductCategoryName(productCategoryPo.getName());
            int update = productDao.update(productPo, whereWrapper);
        }
        //4.同时更新筛选属性的信息
        //4.1 如果修改参数中getProductAttributeIdList不为空,则先删除以前的getProductAttributeIdList,再插入新的getProductAttributeIdList
        if (!CollectionUtils.isEmpty(productCategoryParam.getProductAttributeIdList())) {
            //根据id删除以前的
            categoryAttributeRelationDao.delete(new QueryWrapper<PmsProductCategoryAttributeRelationPo>().
                    eq("product-category_id", id));
            //插入
            insertRelationList(id, productCategoryParam.getProductAttributeIdList());
        } else {
            //4.2 如果getProductAttributeIdList为空,则止需要删除以前的getProductAttributeIdList
            categoryAttributeRelationDao.delete(new QueryWrapper<PmsProductCategoryAttributeRelationPo>().
                    eq("product-category_id", id));
        }
        return productCategoryDao.updateById(productCategoryPo);
    }

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
    @Override
    public List<PmsProductCategoryPo> listByParentId(Long parentId, Integer pageNum, Integer pageSize) {
        //1.开启分页助手
        PageHelper.startPage(pageNum, pageSize);
        //2.根据parentId查询PmsProductCategoryPo
        List<PmsProductCategoryPo> productCategoryPoList = null;
        try {
            productCategoryPoList = productCategoryDao.selectList(new QueryWrapper<PmsProductCategoryPo>().eq("parent_id", parentId));
        } catch (Exception e) {
            throw new ApiException(e);
        }
        //3.返回结果
        return productCategoryPoList;
    }

    /**
     * 查询所有一级分类及子分类
     *
     * @return java.util.List<PmsProductCategoryWithChildrenDto>
     * @author lihaojie
     * @date 2022/12/19 11:14
     */
    @Override
    public List<PmsProductCategoryWithChildrenDto> getWithChildren() {
        List<PmsProductCategoryWithChildrenDto> productCategoryWithChildrenDtoList = null;
        try {
            productCategoryWithChildrenDtoList = productCategoryDao.listWithChildren();
        } catch (Exception e) {
            throw new ApiException(e);
        }
        return productCategoryWithChildrenDtoList;
    }

    /**
     * 根据分类的parentId设置分类的level
     *
     * @param productCategoryPo 参数
     * @author lihaojie
     * @date 2022/12/18 19:53
     */
    private void setCategoryLevel(PmsProductCategoryPo productCategoryPo) {
        //没有父分类时为一级分类
        if (productCategoryPo.getParentId() == 0) {
            productCategoryPo.setLevel(0);
        } else {
            //有父分类时选择根据父分类level设置
            PmsProductCategoryPo parentCategory = productCategoryDao.selectOne(new QueryWrapper<PmsProductCategoryPo>().eq("parent_id", productCategoryPo.getParentId()));
            if (parentCategory != null) {
                productCategoryPo.setLevel(parentCategory.getLevel() + 1);
            } else {
                productCategoryPo.setLevel(0);
            }
        }
    }

    /**
     * 批量插入商品分类与筛选属性关系表
     *
     * @param productCategoryId      商品分类id
     * @param productAttributeIdList 相关商品筛选属性id集合
     */
    private void insertRelationList(Long productCategoryId, List<Long> productAttributeIdList) {
        List<PmsProductCategoryAttributeRelationPo> relationList = new ArrayList<>();
        for (Long productAttrId : productAttributeIdList) {
            PmsProductCategoryAttributeRelationPo relation = new PmsProductCategoryAttributeRelationPo();
            relation.setProductAttributeId(productAttrId);
            relation.setProductCategoryId(productCategoryId);
            relationList.add(relation);
        }
        PmsProductCategoryAttributeRelationDao.insertList(relationList);
    }

}
