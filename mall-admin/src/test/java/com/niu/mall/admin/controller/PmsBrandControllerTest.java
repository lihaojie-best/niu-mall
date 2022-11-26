package com.niu.mall.admin.controller;

import com.niu.mall.common.api.Result;
import com.niu.mall.mbg.po.PmsBrandPo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(Runner.class)
class PmsBrandControllerTest {
    @Autowired
    PmsBrandController controller;
    @Test
    void getList() {
        Result result = controller.getList();
        List<PmsBrandPo> data = (List<PmsBrandPo>) result.getData();
        data.forEach(System.out::println);
    }
}