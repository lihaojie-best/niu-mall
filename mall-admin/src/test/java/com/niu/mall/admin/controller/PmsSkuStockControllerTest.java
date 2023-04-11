package com.niu.mall.admin.controller;

import com.niu.mall.common.api.Result;
import com.niu.mall.controller.PmsSkuStockController;
import com.niu.mall.po.PmsSkuStockPo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(Runner.class)
class PmsSkuStockControllerTest {
    @Autowired
    private PmsSkuStockController controller;
    /**
     * 根据商品ID及sku编码模糊搜索sku库存
     * 
     * @return void
     * @author lihaojie
     * @date 2022/12/21 19:29
     */
    @Test
    void getList() {
        Result<List<PmsSkuStockPo>> result = controller.getList(27L, "001");
        List<PmsSkuStockPo> pmsSkuStockPoList = result.getData();
        pmsSkuStockPoList.forEach(System.out::println);
    }
    /**
     * 批量更新sku库存信息
     *
     * @return void
     * @author lihaojie
     * @date 2022/12/21 19:33
     */
    @Test
    void update() {
        List<PmsSkuStockPo> list=new ArrayList<>();
        list.add(new PmsSkuStockPo(null,49L,"20210300858",new BigDecimal(229.99),5,5,"照片",1,new BigDecimal(393),null,null));
        Result result = controller.update(49L,list);
        System.out.println("update = " + result.getData());
    }
}