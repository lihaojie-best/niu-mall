package com.niu.mall.admin.controller;

import com.niu.mall.common.api.Result;
import com.niu.mall.mbg.po.PmsProductAttributeCategoryPo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * PmsProductAttributeCategoryController测试
 *
 * @author lihaojie
 * @date 2022/12/10 21:49
 **/
@SpringBootTest
@RunWith(Runner.class)
class PmsProductAttributeCategoryControllerTest {
    @Autowired(required = false)
    private PmsProductAttributeCategoryController controller;

    /**
     * 创建商品属性分类
     *
     * @return void
     * @author lihaojie
     * @date 2022/12/10 21:51
     */
    @Test
    void creat() {
        Result<Boolean> result = controller.creat("test11");
        System.out.println("result.getData() = " + result.getData());
    }

    /**
     * 删除单个商品属性分类
     *
     * @return void
     * @author lihaojie
     * @date 2022/12/10 21:51
     */
    @Test
    void deleteById() {
    }

    /**
     * 分页查询商品属性分类
     *
     * @return void
     * @author lihaojie
     * @date 2022/12/10 21:51
     */
    @Test
    void getAll() {
    }

    /**
     * 获取单个商品属性分类信息
     *
     * @return void
     * @author lihaojie
     * @date 2022/12/10 21:50
     */
    @Test
    void getById() {
    }

    /**
     * 获取所有商品属性分类及其下属性
     *
     * @return void
     * @author lihaojie
     * @date 2022/12/10 21:50
     */
    @Test
    void getListWithAttr() {
    }
}