package com.niu.mall.admin.controller;

import com.niu.mall.admin.param.OmsMoneyInfoParam;
import com.niu.mall.admin.param.OmsOrderDeliveryParam;
import com.niu.mall.admin.param.OmsOrderQueryParam;
import com.niu.mall.admin.param.OmsReceiverInfoParam;
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
class OmsOrderControllerTest {
    @Autowired
    private OmsOrderController controller;
    /**
     * 查询订单测试
     *
     * @author lihaojie
     * @date 2023/01/17 12:38
     */
    @Test
    void list() {
        OmsOrderQueryParam omsOrderQueryParam = new OmsOrderQueryParam();
        controller.list(omsOrderQueryParam,1,5);
    }
    /**
     * 批量发货
     *
     * @author lihaojie
     * @date 2023/01/17 13:03
     */
    @Test
    void delivery() {
        List<OmsOrderDeliveryParam> deliveryParamList = new ArrayList<>();
        OmsOrderDeliveryParam param1 = new OmsOrderDeliveryParam();
        param1.setOrderId(21L);
        param1.setDeliveryCompany("顺丰物流");
        param1.setDeliverySn("201707196398311");
        controller.delivery(deliveryParamList);
        OmsOrderDeliveryParam param2 = new OmsOrderDeliveryParam();
        param2.setOrderId(22L);
        param2.setDeliveryCompany("顺丰物流");
        param2.setDeliverySn("201707196398312");
        deliveryParamList.add(param1);
        deliveryParamList.add(param2);
        controller.delivery(deliveryParamList);
    }
    /**
     * 批量关闭订单
     *
     * @return void
     * @author lihaojie
     * @date 2023/01/17 13:13
     */
    @Test
    void close() {
        List<Long> ids= new ArrayList<>();
        ids.add(12L);
        ids.add(13L);
        controller.close(ids,"test1");
    }

    @Test
    void delete() {
        List<Long> ids= new ArrayList<>();
        ids.add(30L);
        controller.delete(ids);
    }
    /**
     * 获取用户详情
     *
     * @author lihaojie
     * @date 2023/01/17 13:27
     */
    @Test
    void detail() {
        controller.detail(12L);
    }

    @Test
    void updateReceiverInfo() {
        OmsReceiverInfoParam omsReceiverInfoParam = new OmsReceiverInfoParam();
        omsReceiverInfoParam.setOrderId(13L);
        omsReceiverInfoParam.setReceiverCity("test");
        controller.updateReceiverInfo(omsReceiverInfoParam);
    }

    @Test
    void testUpdateReceiverInfo() {
        OmsMoneyInfoParam omsMoneyInfoParam = new OmsMoneyInfoParam();
        omsMoneyInfoParam.setOrderId(13L);
        omsMoneyInfoParam.setStatus(1);
        controller.updateReceiverInfo(omsMoneyInfoParam);
    }

    @Test
    void updateNote() {
        controller.updateNote(12L, "test1", 0);
    }
}