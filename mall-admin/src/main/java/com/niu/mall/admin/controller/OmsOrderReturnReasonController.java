package com.niu.mall.admin.controller;

import com.niu.mall.admin.service.OmsOrderReturnReasonService;
import com.niu.mall.common.api.CommonPage;
import com.niu.mall.common.api.Result;
import com.niu.mall.mbg.po.OmsOrderReturnReasonPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 退货原因表 前端控制器
 *
 * @author lihaojie
 * @date 2023/01/17 17:46
 **/
@RestController
@Api(tags = "OmsOrderReturnReasonController", value = "退货原因管理")
@RequestMapping("/returnReason")
public class OmsOrderReturnReasonController {
    @Autowired
    private OmsOrderReturnReasonService orderReturnReasonService;

    @ApiOperation("添加退货原因")
    @PostMapping("/create")
    public Result create(@RequestBody OmsOrderReturnReasonPo returnReason) {
        int count = orderReturnReasonService.create(returnReason);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    @ApiOperation("修改退货原因")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable Long id, @RequestBody OmsOrderReturnReasonPo returnReason) {
        int count = orderReturnReasonService.update(id, returnReason);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    @ApiOperation("批量删除退货原因")
    @PostMapping("/delete")
    public Result delete(@RequestParam("ids") List<Long> ids) {
        int count = orderReturnReasonService.delete(ids);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    @ApiOperation("分页查询退货原因")
    @GetMapping("/list")
    public Result<CommonPage<OmsOrderReturnReasonPo>> list(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                           @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<OmsOrderReturnReasonPo> reasonList = orderReturnReasonService.list(pageSize, pageNum);
        return Result.success(CommonPage.restPage(reasonList));
    }

    @ApiOperation("获取单个退货原因详情信息")
    @GetMapping("/{id}")
    public Result<OmsOrderReturnReasonPo> getItem(@PathVariable Long id) {
        OmsOrderReturnReasonPo reason = orderReturnReasonService.getItem(id);
        return Result.success(reason);
    }

    @ApiOperation("修改退货原因启用状态")
    @PostMapping("/update/status")
    public Result updateStatus(@RequestParam(value = "status") Integer status,
                                     @RequestParam("ids") List<Long> ids) {
        int count = orderReturnReasonService.updateStatus(ids, status);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }
}
