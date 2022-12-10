package com.niu.mall.admin.controller;

import com.niu.mall.common.api.CommonPage;
import com.niu.mall.common.api.Result;
import com.niu.mall.mbg.po.PmsBrandPo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(Runner.class)
class PmsBrandControllerTest {
    @Autowired
    PmsBrandController controller;

    /**
     * 创建品牌
     *
     * @author lihaojie
     * @date 2022/12/07 10:58
     * @return void
     */
    @Test
    void creat() {
        PmsBrandPo brandPo = new PmsBrandPo(null, "test3", null, null, null, null, null, null, null, null, null);
        Result creat = controller.creat(brandPo);
        System.out.println(creat.toString());
        System.out.println(creat.getData());
    }
    /**
     * 删除商品
     *
     * @author lihaojie
     * @date 2022/12/07 11:04
     * @return void
     */
    @Test
    void delete() {
        Result delete = controller.delete(59);
        System.out.println("delete.getData() = " + delete.getData());
    }
    /**
     * 批量删除
     *
     * @author lihaojie
     * @date 2022/12/07 11:07
     * @return void
     */
    @Test
    void deleteBatch() {
        List<Long> ids=new ArrayList<>();
        ids.add(1,59L);
        ids.add(1,60L);
        ids.add(1,61L);
        Result result = controller.deleteBatch(ids);
        System.out.println("result.getData() = " + result.getData());
    }
    /**
     * 更新
     *
     * @author lihaojie
     * @date 2022/12/07 11:08
     * @return void
     */
    @Test
    void update() {
        Result test1 = controller.update(59, new PmsBrandPo().setName("Test1"));
        System.out.println("test1.getData() = " + test1.getData());
    }

    /**
     * 更新显示状态
     *
     * @author lihaojie
     * @date 2022/12/07 13:45
     * @return void
     */
    @Test
    void updateShowStatue() {
        Result test1 = controller.update(59, new PmsBrandPo().setShowStatus(1));
        System.out.println("test1.getData() = " + test1.getData());
    }
    /**
     * 批量更新厂家制造商状态
     *
     * @author lihaojie
     * @date 2022/12/07 13:48
     * @return void
     */
    @Test
    void updateFactoryStatus() {
        Result test1 = controller.update(59, new PmsBrandPo().setFactoryStatus(1));
        System.out.println("test1.getData() = " + test1.getData());
    }
    /**
     * 根据id查询品牌
     *
     * @author lihaojie
     * @date 2022/12/07 13:49
     * @return void
     */
    @Test
    void select() {
        Result select = controller.select(59L);
        System.out.println("select.getData() = " + select.getData());
    }
    /**
     * 查询全部
     *
     * @author lihaojie
     * @date 2022/12/07 13:50
     * @return void
     */
    @Test
    void testGetList() {
        Result list = controller.getList();
        List<PmsBrandPo> data = (List<PmsBrandPo>) list.getData();
        data.forEach(System.out::println);
    }
    /**
     * 模糊查询
     *
     * @author lihaojie
     * @date 2022/12/07 13:51
     * @return void
     */
    @Test
    void testGetByKey() {
        Result<CommonPage<PmsBrandPo>> result = controller.getList("e", 1, 2);
        CommonPage<PmsBrandPo> data = result.getData();
        data.getList().forEach(System.out::println);
        System.out.println("data.getPageNum() = " + data.getPageNum());
        System.out.println("data.getTotal() = " + data.getTotal());
        System.out.println("data.getPageSize() = " + data.getPageSize());
        System.out.println("data.getTotalPage() = " + data.getTotalPage());
    }
    /**
     * 查询全部
     *
     * @author lihaojie
     * @date 2022/12/07 10:57
     * @return void
     */
    @Test
    void getList() {
        Result result = controller.getList();
        List<PmsBrandPo> data = (List<PmsBrandPo>) result.getData();
        data.forEach(System.out::println);
    }
}