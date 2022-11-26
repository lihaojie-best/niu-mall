package com.niu.mall.admin.controller;

import com.niu.mall.admin.dao.PmsProductDao;
import com.niu.mall.admin.dto.PmsProductDto;
import com.niu.mall.admin.dto.PmsProductQueryDto;
import com.niu.mall.common.api.CommonPage;
import com.niu.mall.common.api.Result;
import com.niu.mall.mbg.po.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(Runner.class)
class PmsProductControllerTest {

    @Autowired
    private PmsProductController pmsProductController;

    /**
     * 商品管理创建商品测试
     *
     * @return void
     * @author lihaojie
     * @date 2022/11/21 16:14
     */
    @Test
    void creat() {
        //Dto创建
        PmsProductDto productDto = new PmsProductDto();
        //商品信息
        PmsProductPo productPo = new PmsProductPo(null, 1L, 7L, 0L, 1L,
                "女式超柔软拉毛运动开衫1", "pic", "No86577", 0, 0, 0, 0,
                0, 0, 0, null, null, 0, 100, 0, "test",
                "test", null, 100, 0, "件", null, 0, null,
                "test", "test", "test", "test", "test", "test", "test",
                null, null, 0, 0, "test", "test");
        //商品阶梯价格设置
        PmsProductLadderPo pmsProductLadderPo = new PmsProductLadderPo(null, null, 0, new BigDecimal(1), new BigDecimal(1));
        List<PmsProductLadderPo> productLadderList = new ArrayList<>();
        productLadderList.add(pmsProductLadderPo);
        //商品阶梯价格设置
        PmsProductFullReductionPo productFullReductionPo = new PmsProductFullReductionPo(null, null, null, null);
        List<PmsProductFullReductionPo> productFullReductionPoList = new ArrayList<>();
        productFullReductionPoList.add(productFullReductionPo);
        //商品会员价格设置
        PmsMemberPricePo memberPricePo = new PmsMemberPricePo(null, null, null, null, null);
        List<PmsMemberPricePo> memberPricePoList = new ArrayList<>();
        memberPricePoList.add(memberPricePo);
        //商品的sku库存信息
        PmsSkuStockPo skuStockPo = new PmsSkuStockPo(null, null, "202002250035008", null, null, null, null, null, null, null, null);
        List<PmsSkuStockPo> skuStockPoList = new ArrayList<>();
        skuStockPoList.add(skuStockPo);
        //商品参数及自定义规格属性
        PmsProductAttributeValuePo productAttributeValuePo = new PmsProductAttributeValuePo(null, null, null, null);
        List<PmsProductAttributeValuePo> productAttributeValuePoList = new ArrayList<>();
        productAttributeValuePoList.add(productAttributeValuePo);
        //专题和商品关系
        CmsSubjectProductRelationPo productRelationPo = new CmsSubjectProductRelationPo(null, null, null);
        List<CmsSubjectProductRelationPo> subjectProductRelationPoList = new ArrayList<>();
        subjectProductRelationPoList.add(productRelationPo);
        //优选专区和商品的关系
        CmsPrefrenceAreaProductRelationPo prefrenceAreaProductRelationPo = new CmsPrefrenceAreaProductRelationPo(null, null, null);
        List<CmsPrefrenceAreaProductRelationPo> prefrenceAreaProductRelationPoList = new ArrayList<>();
        prefrenceAreaProductRelationPoList.add(prefrenceAreaProductRelationPo);

        //将productPo参数拷贝到productDto
        BeanUtils.copyProperties(productPo, productDto);
        productDto.setProductLadderList(productLadderList);
        productDto.setProductFullReductionList(productFullReductionPoList);
        productDto.setMemberPriceList(memberPricePoList);
        productDto.setSkuStockList(skuStockPoList);
        productDto.setProductAttributeValueList(productAttributeValuePoList);
        productDto.setSubjectProductRelationList(subjectProductRelationPoList);
        productDto.setPrefrenceAreaProductRelationList(prefrenceAreaProductRelationPoList);
        pmsProductController.creat(productDto);

    }

    /**
     * 更新商品时信息回显测试
     *
     * @return void
     * @author lihaojie
     * @date 2022/11/21 16:58
     */
    @Test
    void getUpdateInfo() {
        Result<PmsProductDto> updateInfo = pmsProductController.getUpdateInfo(40L);
        PmsProductDto data = updateInfo.getData();
        //打印
        PmsProductPo productPo=new PmsProductPo();
        BeanUtils.copyProperties(data,productPo);
        System.out.println(productPo);
        data.getSkuStockList().forEach(System.out::println);
        data.getProductFullReductionList().forEach(System.out::println);
        data.getProductAttributeValueList().forEach(System.out::println);
        data.getMemberPriceList().forEach(System.out::println);
        data.getPrefrenceAreaProductRelationList().forEach(System.out::println);
        data.getProductLadderList().forEach(System.out::println);

 /*     List<PmsProductLadderPo> productLadderList = data.getProductLadderList();
        for (PmsProductLadderPo pmsProductLadderPo : productLadderList) {
            System.out.println(pmsProductLadderPo);
        }
        List<PmsMemberPricePo> memberPriceList = data.getMemberPriceList();
        for (PmsMemberPricePo memberPricePo : memberPriceList) {
            System.out.println(memberPricePo);
        }
        List<CmsPrefrenceAreaProductRelationPo> prefrenceAreaProductRelationList = data.getPrefrenceAreaProductRelationList();
        for (CmsPrefrenceAreaProductRelationPo prefrenceAreaProductRelationPo : prefrenceAreaProductRelationList) {
            System.out.println(prefrenceAreaProductRelationPo);
        }
        List<PmsProductAttributeValuePo> productAttributeValueList = data.getProductAttributeValueList();
        for (PmsProductAttributeValuePo productAttributeValuePo : productAttributeValueList) {
            System.out.println(productAttributeValuePo);
        }
        List<PmsProductFullReductionPo> productFullReductionList = data.getProductFullReductionList();
        for (PmsProductFullReductionPo productFullReductionPo : productFullReductionList) {
            System.out.println(productFullReductionPo);
        }
        List<PmsSkuStockPo> skuStockList = data.getSkuStockList();
        for (PmsSkuStockPo skuStockPo : skuStockList) {
            System.out.println(skuStockPo);
        }
        System.out.println(data);*/
    }

    /**
     * 更新商品测试
     *
     * @return void
     * @author lihaojie
     * @date 2022/11/21 17:05
     */
    @Test
    void update() {
        //Dto创建
        PmsProductDto productDto = new PmsProductDto();
        //商品信息
        PmsProductPo productPo = new PmsProductPo(null, 1L, 7L, 0L, 1L,
                "lhj", "pic", "No86577", 0, 0, 0, 0,
                0, 0, 0, null, null, 0, 100, 0, "test",
                "test", null, 100, 0, "件", null, 0, null,
                "test", "test", "test", "test", "test", "test", "test",
                null, null, 0, 0, "test", "test");
        //商品阶梯价格设置
        PmsProductLadderPo pmsProductLadderPo = new PmsProductLadderPo(null, null, 0, new BigDecimal(1), new BigDecimal(1));
        List<PmsProductLadderPo> productLadderList = new ArrayList<>();
        productLadderList.add(pmsProductLadderPo);
        //商品阶梯价格设置
        PmsProductFullReductionPo productFullReductionPo = new PmsProductFullReductionPo(null, null, null, null);
        List<PmsProductFullReductionPo> productFullReductionPoList = new ArrayList<>();
        productFullReductionPoList.add(productFullReductionPo);
        //商品会员价格设置
        PmsMemberPricePo memberPricePo = new PmsMemberPricePo(null, null, null, null, null);
        List<PmsMemberPricePo> memberPricePoList = new ArrayList<>();
        memberPricePoList.add(memberPricePo);
        //商品的sku库存信息
        PmsSkuStockPo skuStockPo = new PmsSkuStockPo(null, null, "202002250035008", null, null, null, null, null, null, null, null);
        List<PmsSkuStockPo> skuStockPoList = new ArrayList<>();
        skuStockPoList.add(skuStockPo);
        //商品参数及自定义规格属性
        PmsProductAttributeValuePo productAttributeValuePo = new PmsProductAttributeValuePo(null, null, null, null);
        List<PmsProductAttributeValuePo> productAttributeValuePoList = new ArrayList<>();
        productAttributeValuePoList.add(productAttributeValuePo);
        //专题和商品关系
        CmsSubjectProductRelationPo productRelationPo = new CmsSubjectProductRelationPo(null, null, null);
        List<CmsSubjectProductRelationPo> subjectProductRelationPoList = new ArrayList<>();
        subjectProductRelationPoList.add(productRelationPo);
        //优选专区和商品的关系
        CmsPrefrenceAreaProductRelationPo prefrenceAreaProductRelationPo = new CmsPrefrenceAreaProductRelationPo(null, null, null);
        List<CmsPrefrenceAreaProductRelationPo> prefrenceAreaProductRelationPoList = new ArrayList<>();
        prefrenceAreaProductRelationPoList.add(prefrenceAreaProductRelationPo);

        //将productPo参数拷贝到productDto
        BeanUtils.copyProperties(productPo, productDto);
        productDto.setProductLadderList(productLadderList);
        productDto.setProductFullReductionList(productFullReductionPoList);
        productDto.setMemberPriceList(memberPricePoList);
        productDto.setSkuStockList(skuStockPoList);
        productDto.setProductAttributeValueList(productAttributeValuePoList);
        productDto.setSubjectProductRelationList(subjectProductRelationPoList);
        productDto.setPrefrenceAreaProductRelationList(prefrenceAreaProductRelationPoList);
        pmsProductController.update(40L, productDto);
    }

    /**
     * 分页查询测试
     *
     * @return void
     * @author lihaojie
     * @date 2022/11/24 21:00
     */
    @Test
    void list() {
        PmsProductQueryDto productQueryDto =new PmsProductQueryDto();
        productQueryDto.setBrandId(1L);
        Result result = pmsProductController.list(productQueryDto, 10, 1);
        CommonPage<PmsProductDto> data = (CommonPage<PmsProductDto>) result.getData();
        List<PmsProductDto> pmsProductDtoList = data.getList();
        pmsProductDtoList.forEach(System.out::println);
    }
    /**
     * 模糊查询测试
     *
     * @author lihaojie
     * @date 2022/11/26 08:14
     * @return void
     */
    @Test
    void simpleList() {
        Result<List<PmsProductPo>> result = pmsProductController.simpleList("test");
        List<PmsProductPo> data = result.getData();
        data.forEach(System.out::println);
    }
    /**
     * 批量跟新商品审核状态
     *
     * @author lihaojie
     * @date 2022/11/26 08:15
     * @return void
     */
    @Test
    void updateVerifyStatusBatchIds(){
        List<Long> ids=new ArrayList<>();
        ids.add(0,1L);
        ids.add(1,2L);
        ids.add(2,4L);
        Result result = pmsProductController.updateVerifyStatusBatchIds(ids, 0, "TEST");
    }
    /**
     * 批量上下架商品
     *
     * @author lihaojie
     * @date 2022/11/26 08:36
     * @return void
     */
    @Test
    void updatePublishStatus(){
        List<Long> ids=new ArrayList<>();
        ids.add(0,1L);
        ids.add(1,2L);
        ids.add(2,3L);
        Result result = pmsProductController.updatePublishStatus(ids, 0);
    }
    /**
     * 批量修改商品的推荐状态
     *
     * @author lihaojie
     * @date 2022/11/26 08:48
     * @return void
     */
    @Test
    void updateRecommendStatus(){
        List<Long> ids=new ArrayList<>();
        ids.add(0,1L);
        ids.add(1,2L);
        ids.add(2,3L);
        Result result = pmsProductController.updateRecommendStatus(ids, 0);
    }
    /**
     * 批量更新新品状态
     *
     * @author lihaojie
     * @date 2022/11/26 09:08
     * @return void
     */
    @Test
    void updateNewStatus(){
        List<Long> ids=new ArrayList<>();
        ids.add(0,1L);
        ids.add(1,2L);
        ids.add(2,3L);
        Result result = pmsProductController.updateNewStatus(ids, 0);
    }
    /**
     * 更新删除状态
     *
     * @author lihaojie
     * @date 2022/11/26 09:13
     * @return void
     */
    @Test
    void updateDeleteStatus(){
        List<Long> ids=new ArrayList<>();
        ids.add(0,1L);
        ids.add(1,2L);
        ids.add(2,3L);
        Result result = pmsProductController.updateDeleteStatus(ids, 0);
    }

}