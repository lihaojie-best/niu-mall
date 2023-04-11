package com.niu.mall.controller;

import com.niu.mall.service.OmsOrderSettingService;
import com.niu.mall.common.api.Result;
import com.niu.mall.po.OmsOrderSettingPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 订单设置表 前端控制器
 * 
 * @author lihaojie
 * @date 2023/01/17 16:33
 **/
@RestController
@Api(tags = "OmsOrderSettingController", description = "订单设置管理")
@RequestMapping("/orderSetting")
public class OmsOrderSettingController {
    @Autowired
    private OmsOrderSettingService orderSettingService;

    @ApiOperation("获取指定订单设置")
    @GetMapping("/{id}")
    public Result<OmsOrderSettingPo> getItem(@PathVariable Long id) {
        OmsOrderSettingPo orderSetting = orderSettingService.getById(id);
        return Result.success(orderSetting);
    }

    @ApiOperation("修改指定订单设置")
    @GetMapping("/update/{id}")
    public Result update(@PathVariable Long id, @RequestBody OmsOrderSettingPo orderSetting) {
        int count = orderSettingService.update(id,orderSetting);
        if(count>0){
            return Result.success(count);
        }
        return Result.failed();
    }
}
