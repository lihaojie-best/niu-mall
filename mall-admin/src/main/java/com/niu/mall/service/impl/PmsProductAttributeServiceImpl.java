package com.niu.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.niu.mall.dao.PmsProductAttributeCategoryDao;
import com.niu.mall.dto.PmsProductAttrInfoDto;
import com.niu.mall.param.PmsProductAttributeParam;
import com.niu.mall.common.exception.ApiException;
import com.niu.mall.po.PmsProductAttributeCategoryPo;
import com.niu.mall.po.PmsProductAttributePo;
import com.niu.mall.dao.PmsProductAttributeDao;
import com.niu.mall.service.PmsProductAttributeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品属性参数表 服务实现类
 *
 * @author lihaojie
 * @date 2022/12/18 13:10
 **/
@Service
public class PmsProductAttributeServiceImpl extends ServiceImpl<PmsProductAttributeDao, PmsProductAttributePo> implements PmsProductAttributeService {
    @Autowired
    private PmsProductAttributeDao attributeDao;
    @Autowired
    private PmsProductAttributeCategoryDao attributeCategoryDao;

    /**
     * 添加商品属性信息
     *
     * @param pmsProductAttributeParam 商品属性参数实体类
     * @return boolean
     * @author lihaojie
     * @date 2022/12/12 21:24
     */
    @Override
    public int creat(PmsProductAttributeParam pmsProductAttributeParam) {

        int count;
        try {
            //1.增加Attribute
            //1.1 将vo -> po
            PmsProductAttributePo productAttributePo = new PmsProductAttributePo();
            BeanUtils.copyProperties(pmsProductAttributeParam, productAttributePo);
            //1.2 调用dao,添加商品属性
            count = attributeDao.insert(productAttributePo);
            //2.新增商品属性以后需要更新商品属性分类数量
            PmsProductAttributeCategoryPo pmsProductAttributeCategory = attributeCategoryDao.selectById(productAttributePo.getProductAttributeCategoryId());
            if (productAttributePo.getType() == 0) {
                pmsProductAttributeCategory.setAttributeCount(pmsProductAttributeCategory.getAttributeCount() + 1);
            } else if (productAttributePo.getType() == 1) {
                pmsProductAttributeCategory.setParamCount(pmsProductAttributeCategory.getParamCount() + 1);
            }
            attributeCategoryDao.updateById(pmsProductAttributeCategory);
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    /**
     * 批量删除商品属性
     *
     * @param ids 商品属性id集合
     * @return boolean
     * @author lihaojie
     * @date 2022/12/12 22:34
     */
    @Override
    public int delete(List<Long> ids) {
        //1.获取属性分类
        PmsProductAttributePo attributePo = attributeDao.selectById(ids.get(0));
        //2.获取属性类型
        int type = attributePo.getType();
        //3获取并修改属性分类
        PmsProductAttributeCategoryPo pmsProductAttributeCategoryPo = attributeCategoryDao.selectById(attributePo.getProductAttributeCategoryId());
        //4.删除分类
        int count = 0;
        try {
            count = attributeDao.deleteBatchIds(ids);
        } catch (Exception e) {

            throw new RuntimeException(e);
        }
        //5.修改属性分类数量
        if (type == 0) {
            //当type==0时 分count超出分类总数，与不超出
            if (pmsProductAttributeCategoryPo.getAttributeCount() >= count) {
                pmsProductAttributeCategoryPo.setAttributeCount(pmsProductAttributeCategoryPo.getAttributeCount() - count);
            } else {
                pmsProductAttributeCategoryPo.setAttributeCount(0);
            }
        } else {
            //type ==1  分count超出分类总数，与不超出
            if (pmsProductAttributeCategoryPo.getParamCount() >= count) {
                pmsProductAttributeCategoryPo.setParamCount(pmsProductAttributeCategoryPo.getParamCount() - count);
            } else {
                pmsProductAttributeCategoryPo.setParamCount(0);
            }

        }
        //6.返回删除的行数
        return count;
    }
    /**
     * 根据商品分类的id获取商品属性及属性分类
     *
     * @param productCategoryId 商品属性分类id
     * @return java.util.List<PmsProductAttrInfoDto>
     * @author lihaojie
     * @date 2022/12/18 13:08
     */
    @Override
    public List<PmsProductAttrInfoDto> getProductAttrInfo(Long productCategoryId) {
        return attributeDao.getProductAttrInfo(productCategoryId);
    }
    /**
     * 根据分类查询属性列表或参数列表
     *
     * @param cid 商品分类id
     * @param type 0表示属性，1表示参数
     * @param pageSize 分页大小
     * @param pageNum 分页当前页数
     * @return java.util.List<PmsProductAttributePo>
     * @author lihaojie
     * @date 2022/12/18 13:24
     */
    @Override
    public List<PmsProductAttributePo> getList(Long cid, Integer type, Integer pageSize, Integer pageNum) {
        //1.开启分页助手
        PageHelper.startPage(pageNum,pageSize);
        //2.根据 cid and type 获取list集合
        //2.1 构建哇仍然疲弱他
        QueryWrapper<PmsProductAttributePo> whereWrapper = new QueryWrapper<PmsProductAttributePo>()
                .eq("product_attribute_category_id",cid).eq("type",type);
        //2.2 select
        List<PmsProductAttributePo> pmsProductAttributePoList = null;
        try {
            pmsProductAttributePoList = attributeDao.selectList(whereWrapper);
        } catch (Exception e) {
            throw new ApiException(e);
        }
        //3.返回集合
        return pmsProductAttributePoList;
    }

}
