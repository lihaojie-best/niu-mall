package com.niu.mall.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import cn.hutool.core.util.StrUtil;
import com.niu.mall.admin.dao.*;
import com.niu.mall.admin.dto.PmsProductDto;
import com.niu.mall.admin.dto.PmsProductQueryDto;
import com.niu.mall.admin.service.PmsProductService;
import com.niu.mall.common.api.CommonPage;
import com.niu.mall.mbg.po.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductDao, PmsProductPo> implements PmsProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsProductServiceImpl.class);
    @Autowired
    private PmsProductDao productDao;
    @Autowired
    private PmsProductLadderDao productLadderDao;
    @Autowired
    private PmsProductFullReductionDao productFullReductionDao;
    @Autowired
    private PmsMemberPriceDao memberPriceDao;
    @Autowired
    private PmsSkuStockDao skuStockDao;
    @Autowired
    private PmsProductAttributeValueDao productAttributeValueDao;
    @Autowired
    private CmsSubjectProductRelationDao subjectProductRelationDao;
    @Autowired
    private CmsPrefrenceAreaProductRelationDao prefrenceAreaProductRelationDao;
    @Autowired
    private PmsProductVertifyRecordDao productVertifyRecordDao;

    /**
     * 创建商品
     *
     * @param productDto
     * @return int
     * @author lihaojie
     * @date 2022/11/21 19:08
     */
    @Override
    public int creat(PmsProductDto productDto) {
        int flag;
        PmsProductPo productPo = new PmsProductPo();
        //插入商品信息
        BeanUtils.copyProperties(productDto, productPo);
        productDao.insert(productPo);
        //获取商品id
        long productId = productPo.getId();
        //商品阶梯价格设置
        List<PmsProductLadderPo> productLadderPoList = productDto.getProductLadderList();
        relateAndInsertList(productLadderDao, productLadderPoList, productId);
        //商品阶梯价格设置
        List<PmsProductFullReductionPo> productFullReductionPoList = productDto.getProductFullReductionList();
        relateAndInsertList(productFullReductionDao, productFullReductionPoList, productId);
        // 商品会员价格设置
        List<PmsMemberPricePo> memberPricePoList = productDto.getMemberPriceList();
        relateAndInsertList(memberPriceDao, memberPricePoList, productId);
        //商品的sku库存信息
        List<PmsSkuStockPo> skuStockPoList = productDto.getSkuStockList();
        relateAndInsertList(skuStockDao, skuStockPoList, productId);
        //商品参数及自定义规格属性
        List<PmsProductAttributeValuePo> productAttributeValueList = productDto.getProductAttributeValueList();
        relateAndInsertList(productAttributeValueDao, productAttributeValueList, productId);
        //专题和商品关系
        List<CmsSubjectProductRelationPo> subjectProductRelationList = productDto.getSubjectProductRelationList();
        relateAndInsertList(subjectProductRelationDao, subjectProductRelationList, productId);
        //优选专区和商品的关系
        List<CmsPrefrenceAreaProductRelationPo> prefrenceAreaProductRelationList = productDto.getPrefrenceAreaProductRelationList();
        relateAndInsertList(prefrenceAreaProductRelationDao, prefrenceAreaProductRelationList, productId);
        flag = 1;
        return flag;
    }

    /**
     * 更新商品时回显数据
     *
     * @param id
     * @return com.niu.mall.mbg.po.PmsProductPo
     * @author lihaojie
     * @date 2022/11/21 16:54
     */
    @Override
    public PmsProductDto getUpdateInfo(Long id) {
        PmsProductDto productDto = new PmsProductDto();
        //1.查product
        PmsProductPo productPo = productDao.selectById(id);
        BeanUtils.copyProperties(productPo, productDto);
        //2.根据商品id查询相关表
        QueryWrapper whereWrapper =new QueryWrapper<>().eq("product_id",id);
        //商品阶梯价格设置
        List<PmsProductLadderPo> productLadderPoList = productLadderDao.selectList(whereWrapper);
        productDto.setProductLadderList(productLadderPoList);
        //商品满减价格设置
        List<PmsProductFullReductionPo> productFullReductionPoList =productFullReductionDao.selectList(whereWrapper);
        productDto.setProductFullReductionList(productFullReductionPoList);
        //商品会员价格设置
        List<PmsMemberPricePo> memberPricePoList = memberPriceDao.selectList(whereWrapper);
        productDto.setMemberPriceList(memberPricePoList);
        //商品的sku库存信息
        List<PmsSkuStockPo> skuStockList = skuStockDao.selectList(whereWrapper);
        productDto.setSkuStockList(skuStockList);
        //商品参数及自定义规格属性
        List<PmsProductAttributeValuePo> productAttributeValuePoList = productAttributeValueDao.selectList(whereWrapper);
        productDto.setProductAttributeValueList(productAttributeValuePoList);
        //专题和商品关系
        List<CmsSubjectProductRelationPo> subjectProductRelationPoList = subjectProductRelationDao.selectList(whereWrapper);
        productDto.setSubjectProductRelationList(subjectProductRelationPoList);
        //优选专区和商品的关系
        List<CmsPrefrenceAreaProductRelationPo> prefrenceAreaProductRelationPoList = prefrenceAreaProductRelationDao.selectList(whereWrapper);
        productDto.setPrefrenceAreaProductRelationList(prefrenceAreaProductRelationPoList);
        return productDto;
    }

    /**
     * 建立和插入关系表操作，对product的关联数据进行插入
     *
     * @param dao
     * @param dataList
     * @param productId
     * @return void
     * @author lihaojie
     * @date 2022/11/21 18:57
     */
    private void relateAndInsertList(Object dao, List dataList, Long productId) {
        try {
            //dataList数据为空直接返回
            if (CollectionUtils.isEmpty(dataList)) return;
            for (Object item : dataList) {
                //通过代理获得setId方法，并将id设置为null
                Method setId = item.getClass().getMethod("setId", Long.class);
                setId.invoke(item, (Long) null);
                //将ProductId设置为新增Product返回的id
                Method setProductId = item.getClass().getMethod("setProductId", Long.class);
                setProductId.invoke(item, productId);
            }
            //得到dao曾中批量插入方法
            Method insertList = dao.getClass().getMethod("insertList", List.class);
            //invoke方法是运行函数
            insertList.invoke(dao, dataList);

        } catch (Exception e) {
            LOGGER.warn("创建产品出错:{}", e);
            throw new RuntimeException(e.getMessage());
        }
    }
    /**
     * 更新商品信息
     *
     * @author lihaojie
     * @date 2022/11/23 21:09
     * @param productId
     * @param productDto
     * @return int
     */
    @Override
    public int updateById(Long productId, PmsProductDto productDto) {
        int count=-1;
        //更新商品
        PmsProductPo productPo=productDto;
        productPo.setId(productId);
        //productDao.update(productPo,new QueryWrapper<PmsProductPo>().eq("id",productId));
        productDao.updateById(productPo);
        //1.根据productId先删除有关表再插入
        QueryWrapper whereWrapper =new QueryWrapper<>().eq("product_id",productId);
        //商品阶梯价格设置
        productLadderDao.delete(whereWrapper);
        List<PmsProductLadderPo> productLadderPoList = productDto.getProductLadderList();
        relateAndInsertList(productLadderDao, productLadderPoList, productId);
        //商品阶梯价格设置
        productFullReductionDao.delete(whereWrapper);
        List<PmsProductFullReductionPo> productFullReductionPoList = productDto.getProductFullReductionList();
        relateAndInsertList(productFullReductionDao, productFullReductionPoList, productId);
        // 商品会员价格设置
        memberPriceDao.delete(whereWrapper);
        List<PmsMemberPricePo> memberPricePoList = productDto.getMemberPriceList();
        relateAndInsertList(memberPriceDao, memberPricePoList, productId);
        //商品的sku库存信息
        skuStockDao.delete(whereWrapper);
        List<PmsSkuStockPo> skuStockPoList = productDto.getSkuStockList();
        relateAndInsertList(skuStockDao, skuStockPoList, productId);
        //商品参数及自定义规格属性
        productAttributeValueDao.delete(whereWrapper);
        List<PmsProductAttributeValuePo> productAttributeValueList = productDto.getProductAttributeValueList();
        relateAndInsertList(productAttributeValueDao, productAttributeValueList, productId);
        //专题和商品关系
        subjectProductRelationDao.delete(whereWrapper);
        List<CmsSubjectProductRelationPo> subjectProductRelationList = productDto.getSubjectProductRelationList();
        relateAndInsertList(subjectProductRelationDao, subjectProductRelationList, productId);
        //优选专区和商品的关系
        prefrenceAreaProductRelationDao.delete(whereWrapper);
        List<CmsPrefrenceAreaProductRelationPo> prefrenceAreaProductRelationList = productDto.getPrefrenceAreaProductRelationList();
        relateAndInsertList(prefrenceAreaProductRelationDao, prefrenceAreaProductRelationList, productId);
        count=1;
        return count;
    }
    /**
     * 分页查询商品
     *
     * @author lihaojie
     * @date 2022/11/24 15:29
     * @param pageSize
     * @param pageNum
     * @param productQueryDto
     * @return com.github.pagehelper.Page<com.niu.mall.mbg.po.PmsProductPo>
     */
    @Override
    public CommonPage<PmsProductDto> list(Integer pageSize, Integer pageNum, PmsProductQueryDto productQueryDto) {
        //开启分页助手
        PageHelper.startPage(pageNum,pageSize);
        //根据传入的productQueryDto生成审查map
        Map whereMap=new HashMap<>();
        if (productQueryDto.getPublishStatus() != null) {
            whereMap.put("publish_status",productQueryDto.getPublishStatus());
        }
        if (productQueryDto.getVerifyStatus() != null) {
            whereMap.put("verify_status",productQueryDto.getVerifyStatus());
        }
        if (!StrUtil.isEmpty(productQueryDto.getKeyword())) {
            whereMap.put("keywords",productQueryDto.getKeyword());
        }
        if (!StrUtil.isEmpty(productQueryDto.getProductSn())) {
            whereMap.put("product_sn",productQueryDto.getProductSn());
        }
        if (!StrUtil.isEmpty(productQueryDto.getProductSn())) {
            whereMap.put("brand_id",productQueryDto.getBrandId());
        }
        if (productQueryDto.getProductCategoryId() != null) {
            whereMap.put("product_category_id",productQueryDto.getProductCategoryId());
        }

        //查询商品信息
        List<PmsProductPo> productPoList = productDao.selectByMap(whereMap);
        //将productPoList 转化为 productDtoList 统一返回结果类型
        List<PmsProductDto> productDtoList=productPoList.stream().map(productPo -> {
            PmsProductDto productDto =new PmsProductDto();
            BeanUtils.copyProperties(productPo,productDto);
            return productDto;
        }).collect(Collectors.toList());
        return CommonPage.restPage(productDtoList);
    }
    /**
     *   批量修改审核状态
     *
     * @author lihaojie
     * @date 2022/11/24 22:16
     * @param ids
     * @param verifyStatus
     * @param detail
     * @return int
     */
    @Override
    public int updateVerifyStatusBatchIds(List<Long> ids, Integer verifyStatus, String detail) {
        int count=-1;
        //1，根据id更新verifyStatus
        PmsProductPo productPo=new PmsProductPo().setVerifyStatus(verifyStatus);
        QueryWrapper whereWrapper = new QueryWrapper();
        whereWrapper.in("id",ids);
        productDao.update(productPo,whereWrapper);
        //2.填写审核记录表
        List<PmsProductVertifyRecordPo> list = new ArrayList<>();
        for (Long id : ids) {
            PmsProductVertifyRecordPo record = new PmsProductVertifyRecordPo();
            record.setProductId(id);
            record.setCreateTime(new Date());
            record.setDetail(detail);
            record.setStatus(verifyStatus);
            record.setVertifyMan("test");
            list.add(record);
        };
        productVertifyRecordDao.insertList(list);
        count=1;
        return count;
    }

}
