package com.niu.mall.admin.controller;

import com.niu.mall.controller.SmsCouponController;
import com.niu.mall.param.SmsCouponParam;
import com.niu.mall.common.api.Result;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(Runner.class)
class SmsCouponControllerTest {

    @Autowired
    private SmsCouponController couponController;
    @Test
    void creatCoupon() {


    }

    @Test
    void updateCoupon() {
    }

    @Test
    void deleteCoupon() {
    }

    @Test
    void getItem() {
        Result<SmsCouponParam> result = couponController.getItem(2L);
        SmsCouponParam param = result.getData();
        System.out.println("param = " + param.toString());
    }

    @Test
    void list() {
    }
}