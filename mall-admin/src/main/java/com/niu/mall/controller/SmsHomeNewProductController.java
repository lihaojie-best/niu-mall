package com.niu.mall.controller;

import com.niu.mall.common.api.CommonPage;
import com.niu.mall.common.api.Result;
import com.niu.mall.po.SmsHomeBrandPo;
import com.niu.mall.po.SmsHomeNewProductPo;
import com.niu.mall.service.SmsHomeNewProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 新鲜好物表 前端控制器
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@RestController
@RequestMapping("/home/newProduct")
@Api(tags = "SmsHomeNewProductController", value = "首页新品管理")
public class SmsHomeNewProductController {
    @Autowired
    private SmsHomeNewProductService homeNewProductService;

    @ApiOperation("添加首页新品")
    @PostMapping(value = "/create")
    public Result create(@RequestBody List<SmsHomeNewProductPo> homeNewProductList) {
        boolean b = homeNewProductService.saveBatch(homeNewProductList);
        if (b) {
            return Result.success("添加成功");
        }else {
            return Result.failed("添加失败");
        }
    }
    @ApiOperation("修改首页新品排序")
    @PostMapping(value = "/update/sort/{id}")
    @ResponseBody
    public Result updateSort(@PathVariable Long id, Integer sort) {
        SmsHomeNewProductPo smsHomeNewProductPo = new SmsHomeNewProductPo().setId(id).setSort(sort);
        boolean b= homeNewProductService.updateById(smsHomeNewProductPo);
        if (b) {
            return Result.success("修改成功");
        }else {
            return Result.failed("修改失败");
        }
    }

    @ApiOperation("批量删除首页新品")
    @PostMapping(value = "/delete")
    public Result delete(@RequestParam("ids") List<Long> ids) {
        boolean b = homeNewProductService.removeBatchByIds(ids);
        if (b) {
            return Result.success("批量删除成功");
        } else {
            return Result.failed("批量删除失败");
        }
    }
    @ApiOperation("批量修改首页新品状态")
    @PostMapping(value = "/update/recommendStatus")

    public Result updateRecommendStatus(@RequestParam("ids") List<Long> ids, @RequestParam Integer recommendStatus) {
        int count = homeNewProductService.updateRecommendStatus(ids, recommendStatus);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    @ApiOperation("分页查询首页新品")
    @GetMapping(value = "/list")
    public Result<CommonPage<SmsHomeNewProductPo>> list(@RequestParam(value = "productName", required = false) String productName,
                                                            @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                                            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsHomeNewProductPo> homeNewProductList = homeNewProductService.list(productName, recommendStatus, pageSize, pageNum);
        return Result.success(CommonPage.restPage(homeNewProductList));
    }
}
