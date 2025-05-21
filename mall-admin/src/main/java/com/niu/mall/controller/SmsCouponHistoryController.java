package com.niu.mall.controller;

import com.niu.mall.common.api.CommonPage;
import com.niu.mall.common.api.Result;
import com.niu.mall.po.SmsCouponHistoryPo;
import com.niu.mall.service.SmsCouponHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 优惠券使用、领取历史表 前端控制器
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Api(tags = "SmsCouponHistoryController",description = "优惠卷领取使用历史前端控制器")
@RestController
@RequestMapping("/couponHistoryPo")
public class SmsCouponHistoryController {
    @Autowired
    private SmsCouponHistoryService couponHistoryService;

    @ApiOperation("根据优惠卷id 使用转台， 订单编号进行分页查询领取记录")
    @GetMapping("/list")
    public Result<CommonPage<SmsCouponHistoryPo>> list(@RequestParam(value = "couponId", required = false) Long couponId,
                                                       @RequestParam(value = "useStatus", required = false) Integer useStatus,
                                                       @RequestParam(value = "orderSn", required = false) String orderSn,
                                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {


        List<SmsCouponHistoryPo> list = couponHistoryService.list(couponId, useStatus, orderSn, pageNum, pageSize);
        return Result.success(CommonPage.restPage(list));

    }


}
