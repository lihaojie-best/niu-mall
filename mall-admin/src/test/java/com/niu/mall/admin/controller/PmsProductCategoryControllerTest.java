package com.niu.mall.admin.controller;

import com.niu.mall.admin.dto.PmsProductCategoryWithChildrenDto;
import com.niu.mall.admin.param.PmsProductCategoryParam;
import com.niu.mall.common.api.CommonPage;
import com.niu.mall.common.api.Result;
import com.niu.mall.mbg.po.PmsProductCategoryPo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(Runner.class)
class PmsProductCategoryControllerTest {
    @Autowired
    private PmsProductCategoryController controller;

    /**
     * 新增测试
     *
     * @return void
     * @author lihaojie
     * @date 2022/12/21 16:19
     */
    @Test
    void creat() {
        ArrayList<Long> list = new ArrayList<>();
        list.add(24L);
        PmsProductCategoryParam productCategoryParam = new PmsProductCategoryParam(0L, "TestName", "件", 1, 1, 1, null, "test", "描述", list);
        controller.creat(productCategoryParam);
    }

    /**
     * 更新测试
     *
     * @return void
     * @author lihaojie
     * @date 2022/12/21 16:19
     */
    @Test
    void update() {
        ArrayList<Long> list = new ArrayList<>();
        list.add(24L);
        PmsProductCategoryParam productCategoryParam = new PmsProductCategoryParam(0L, "TestName", "件", 1, 1, 1, null, "test", "描述", list);

    }

    /**
     * 通过父分类id
     *
     * @return void
     * @author lihaojie
     * @date 2022/12/21 15:55
     */
    @Test
    void listByCategoryParentId() {
        Result<CommonPage<PmsProductCategoryPo>> result = controller.listByCategoryParentId(1L, 1, 5);
        List<PmsProductCategoryPo> list = result.getData().getList();
        list.forEach(System.out::println);
    }

    /**
     * 根据id查询测试
     *
     * @return void
     * @author lihaojie
     * @date 2022/12/21 16:20
     */
    @Test
    void getById() {
        Result<PmsProductCategoryPo> result = controller.getById(1L);
        System.out.println("result.getData().toString() = " + result.getData().toString());
    }

    /**
     * 查询目标分类及其子分类
     *
     * @return void
     * @author lihaojie
     * @date 2022/12/21 16:20
     */
    @Test
    void getWithChildren() {
        //1.获取统一返回值
        Result<List<PmsProductCategoryWithChildrenDto>> withChildren = controller.getWithChildren();
        //2，获取结果数据
        List<PmsProductCategoryWithChildrenDto> productCategoryWithChildrenDtoList = withChildren.getData();
        //3.打印
        productCategoryWithChildrenDtoList.forEach(item -> {
            System.out.println("item = " + item);
            //打印子类
            item.getChildrenProductCategoryPoList().forEach(System.out::println);
        });
    }

    /**
     * 更新导航栏状态
     *
     * @return void
     * @author lihaojie
     * @date 2022/12/21 16:21
     */
    @Test
    void updateNavStatus() {
        ArrayList<Long> ids = new ArrayList<>();
        ids.add(1L);
        Result result = controller.updateNavStatus(ids, 0);
        System.out.println("updateNavStatus = " + result.getData());
    }

    /**
     * 更新显示状态
     *
     * @return void
     * @author lihaojie
     * @date 2022/12/21 16:21
     */
    @Test
    void updateShowStatus() {
        ArrayList<Long> ids = new ArrayList<>();
        ids.add(1L);
        Result result = controller.updateShowStatus(ids, 0);
        System.out.println("updateShowStatus = " + result.getData());
    }

    /**
     * 删除测试
     *
     * @return void
     * @author lihaojie
     * @date 2022/12/21 16:21
     */
    @Test
    void delete() {
        Result result = controller.delete(53L);
        System.out.println("delete = " + result.getData());
    }
}