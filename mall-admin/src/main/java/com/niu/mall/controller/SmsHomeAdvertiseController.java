package com.niu.mall.controller;

import com.niu.mall.common.api.CommonPage;
import com.niu.mall.common.api.Result;
import com.niu.mall.po.SmsHomeAdvertisePo;
import com.niu.mall.service.SmsHomeAdvertiseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页轮播广告表 前端控制器
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@RestController
@RequestMapping("/home/advertise")
@Api(tags = "SmsHomeAdvertiseController", value = "首页轮播广告管理")
public class SmsHomeAdvertiseController {
    @Autowired
    private SmsHomeAdvertiseService advertiseService;

    @ApiOperation("添加广告")
    @PostMapping(value = "/create")
    public Result creat(@RequestBody SmsHomeAdvertisePo advertise) {
        boolean save = advertiseService.save(advertise);
        if (save) {
            return Result.success("创建成功");
        } else {
            return Result.failed("创建失败");
        }
    }

    @ApiOperation("删除广告")
    @PostMapping(value = "/delete")
    public Result delete(@RequestParam("ids") List<Long> ids) {
        boolean b = advertiseService.removeBatchByIds(ids);
        if (b) {
            return Result.success("删除成功");
        } else {
            return Result.failed("删除失败");
        }
    }

    @ApiOperation("修改上下线状态")
    @PostMapping(value = "/update/status/{id}")
    public Result updateStatus(@PathVariable("id") Long id, Integer status) {
        boolean b = advertiseService.updateById(new SmsHomeAdvertisePo().setId(id).setStatus(status));
        if (b) {
            return Result.success("修改成功");
        } else {
            return Result.failed("修改失败");
        }
    }

    @ApiOperation("获取广告详情")
    @GetMapping(value = "/{id}")
    public Result<SmsHomeAdvertisePo> getItem(@PathVariable("id") Long id) {
        SmsHomeAdvertisePo advertisePo = advertiseService.getById(id);
        return Result.success(advertisePo);
    }

    @ApiOperation("修改广告")
    @PostMapping(value = "/update/{id}")
    public Result update(@PathVariable(value = "id") Long id, @RequestBody SmsHomeAdvertisePo advertise) {
        boolean b = advertiseService.updateById(advertise.setId(id));
        if (b) {
            return Result.success("修改成功");
        } else {
            return Result.failed("修改失败");
        }
    }

    @ApiOperation("分页查询广告")
    @GetMapping(value = "/list")
    public Result<CommonPage<SmsHomeAdvertisePo>> list(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "type", required = false) Integer type,
                            @RequestParam(value = "endTime", required = false) String endTime,
                            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsHomeAdvertisePo> list = advertiseService.list(name, type, endTime, pageNum, pageSize);
        return Result.success(CommonPage.restPage(list));
    }
}
