package com.niu.mall.admin.controller;

import com.niu.mall.admin.dto.OmsOrderReturnApplyResultDto;
import com.niu.mall.admin.param.OmsReturnApplyQueryParam;
import com.niu.mall.admin.param.OmsUpdateStatusParam;
import com.niu.mall.admin.service.OmsOrderReturnApplyService;
import com.niu.mall.common.api.CommonPage;
import com.niu.mall.common.api.Result;
import com.niu.mall.mbg.po.OmsOrderReturnApplyPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单退货申请 前端控制器
 *
 * @author lihaojie
 * @date 2023/01/17 16:44
 **/
@RestController
@Api(tags = "OmsOrderReturnApplyController", value = "订单退货申请管理")
@RequestMapping("/returnApply")
public class OmsOrderReturnApplyController {
    @Autowired
    private OmsOrderReturnApplyService returnApplyService;

    @ApiOperation("分页查询退货申请")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Result<CommonPage<OmsOrderReturnApplyPo>> list(OmsReturnApplyQueryParam queryParam,
                                                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<OmsOrderReturnApplyPo> returnApplyList = returnApplyService.list(queryParam, pageSize, pageNum);
        return Result.success(CommonPage.restPage(returnApplyList));
    }

    @ApiOperation("批量删除退货申请")
    @PostMapping("/delete")
    public Result delete(@RequestParam("ids") List<Long> ids) {
        int count = returnApplyService.delete(ids);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    @ApiOperation("获取退货申请详情")
    @GetMapping("/{id}")
    public Result getItem(@PathVariable Long id) {
        OmsOrderReturnApplyResultDto result = returnApplyService.getItem(id);
        return Result.success(result);
    }

    @ApiOperation("修改退货申请状态")
    @PostMapping("/update/status/{id}")
    public Result updateStatus(@PathVariable Long id, @RequestBody OmsUpdateStatusParam statusParam) {
        int count = returnApplyService.updateStatus(id, statusParam);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

}
