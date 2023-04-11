package com.niu.mall.controller;

import com.niu.mall.dto.OmsOrderDetailDto;
import com.niu.mall.param.OmsMoneyInfoParam;
import com.niu.mall.param.OmsOrderDeliveryParam;
import com.niu.mall.param.OmsOrderQueryParam;
import com.niu.mall.param.OmsReceiverInfoParam;
import com.niu.mall.service.OmsOrderService;
import com.niu.mall.common.api.CommonPage;
import com.niu.mall.common.api.Result;
import com.niu.mall.po.OmsOrderPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单表 前端控制器
 *
 * @author lihaojie
 * @date 2023/01/16 23:43
 **/
@RestController
@Api(tags = "OmsOrderController", description = "订单管理")
@RequestMapping("/order")
public class OmsOrderController {
    @Autowired
    private OmsOrderService orderService;

    @ApiOperation("查询订单")
    @GetMapping("/list")
    public Result<CommonPage<OmsOrderPo>> list(OmsOrderQueryParam queryParam,
                                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<OmsOrderPo> orderList = orderService.list(queryParam, pageSize, pageNum);
        return Result.success(CommonPage.restPage(orderList));
    }

    @ApiOperation("批量发货")
    @PostMapping("/update/delivery")
    public Result delivery(@RequestBody List<OmsOrderDeliveryParam> deliveryParamList) {
        int count = orderService.delivery(deliveryParamList);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    /**
     * 批量关闭订单
     *
     * @param ids  id列表
     * @param note 订单操作历史记录备注
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2023/01/17 13:24
     */
    @ApiOperation("批量关闭订单")
    @PostMapping("/update/close")
    public Result close(@RequestParam("ids") List<Long> ids, @RequestParam String note) {
        int count = orderService.close(ids, note);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    @ApiOperation("批量删除订单")
    @PostMapping("/delete")
    public Result delete(@RequestParam("ids") List<Long> ids) {
        int count = 0;
        try {
            count = orderService.getBaseMapper().deleteBatchIds(ids);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    @ApiOperation("获取订单详情：订单信息、商品信息、操作记录")
    @GetMapping("/{id}")
    @ResponseBody
    public Result<OmsOrderDetailDto> detail(@PathVariable Long id) {
        OmsOrderDetailDto orderDetailResult = orderService.detail(id);
        return Result.success(orderDetailResult);
    }

    @ApiOperation("修改收货人信息")
    @PostMapping("/update/receiverInfo")
    public Result updateReceiverInfo(@RequestBody OmsReceiverInfoParam receiverInfoParam) {
        int count = orderService.updateReceiverInfo(receiverInfoParam);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    @ApiOperation("修改订单费用信息")
    @PostMapping("/update/moneyInfo")
    public Result updateReceiverInfo(@RequestBody OmsMoneyInfoParam moneyInfoParam) {
        int count = orderService.updateMoneyInfo(moneyInfoParam);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    @ApiOperation("备注订单")
    @PostMapping("/update/note")
    public Result updateNote(@RequestParam("id") Long id,
                             @RequestParam("note") String note,
                             @RequestParam("status") Integer status) {
        int count = orderService.updateNote(id, note, status);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }
}
