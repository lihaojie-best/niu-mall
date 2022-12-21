package com.niu.mall.admin.controller;

import com.niu.mall.admin.dto.PmsProductAttrInfoDto;
import com.niu.mall.admin.param.PmsProductAttributeParam;
import com.niu.mall.common.api.CommonPage;
import com.niu.mall.common.api.Result;
import com.niu.mall.mbg.po.PmsProductAttributePo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 商品属性管理测试
 *
 * @author lihaojie
 * @date 2022/12/18 15:05
 **/
@SpringBootTest
@RunWith(Runner.class)
class PmsProductAttributeControllerTest {
    @Autowired(required = false)
    private PmsProductAttributeController controller;
    /**
     * 创建测试
     *
     * @return void
     * @author lihaojie
     * @date 2022/12/18 15:05
     */
    @Test
    void creat() {
        PmsProductAttributeParam productAttributeParam=new PmsProductAttributeParam(1L,"test",1,1,"test",1,1,1,1,1,1);
        Result result = controller.creat(productAttributeParam);
        System.out.println("result.getData() = " + result.getData());
    }
    /**
     * 批量删除商品属性
     *
     * @return void
     * @author lihaojie
     * @date 2022/12/18 15:11
     */
    @Test
    void delete() {
        List<Long> ids=new ArrayList<>();
        ids.add(54L);
        controller.deleteByIds(ids);
    }
    /**
     * 属性更新测试
     *
     * @return void
     * @author lihaojie
     * @date 2022/12/18 15:45
     */
    @Test
    void update() {
        PmsProductAttributeParam productAttributeParam=new PmsProductAttributeParam(1L,"testUpdate",1,1,"test",1,1,1,1,1,1);
        Result result = controller.update(54L, productAttributeParam);
        System.out.println("result.getData() = " + result.getData());
    }
    /**
     * 根据id查询测试
     *
     * @return void
     * @author lihaojie
     * @date 2022/12/18 15:51
     */
    @Test
    void getById() {
        Result<PmsProductAttributePo> result = controller.getById(54L);
        System.out.println("result.getData() = " + result.getData().toString());
    }
    /**
     * 根据商品分类的id获取商品属性及属性分类
     *
     * @return void
     * @author lihaojie
     * @date 2022/12/18 15:54
     */
    @Test
    void getAttrInfo() {
        Result<List<PmsProductAttrInfoDto>> result = controller.getAttrInfo(1L);
        List<PmsProductAttrInfoDto> data = result.getData();
        data.forEach(item -> System.out.println(item.toString()));
    }
    /**
     *
     *
     * @return void
     * @author lihaojie
     * @date 2022/12/18 15:15
     */
    @Test
    void getList() {
        Result<CommonPage<PmsProductAttributePo>> result = controller.getList(0L, 1, 3, 2);
        CommonPage<PmsProductAttributePo> data = result.getData();
        System.out.println("data.getTotalPage() = " + data.getTotalPage());
        System.out.println("data.getPageSize() = " + data.getPageSize());
        System.out.println("data.getTotal() = " + data.getTotal());
        data.getList().forEach(item ->System.out.println(item.toString()));
    }
}