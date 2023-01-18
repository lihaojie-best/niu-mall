package com.niu.mall.admin.controller;

import com.niu.mall.mbg.po.OmsOrderSettingPo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(Runner.class)
class OmsOrderSettingControllerTest {
    @Autowired
    private OmsOrderSettingController controller;
    @Test
    void getItem() {
        controller.getItem(1L);
    }

    @Test
    void update() {
        OmsOrderSettingPo omsOrderSettingPo = new OmsOrderSettingPo();
        omsOrderSettingPo.setFlashOrderOvertime(120);
        controller.update(1L, omsOrderSettingPo);
    }
}