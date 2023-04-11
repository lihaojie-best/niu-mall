package com.niu.mall.controller;

import com.niu.mall.common.api.CommonPage;
import com.niu.mall.common.api.Result;
import com.niu.mall.po.SmsHomeBrandPo;
import com.niu.mall.service.SmsHomeBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.access.AbstractRemoteSlsbInvokerInterceptor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页推荐品牌表 前端控制器
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@RestController
@RequestMapping("/home/brand")
@Api(tags = "SmsHomeBrandController", value = "首页品牌管理")
public class SmsHomeBrandController {
    @Autowired
    private SmsHomeBrandService service;

    @ApiOperation("添加首页推荐品牌")
    @PostMapping("/create")
    public Result create(@RequestBody List<SmsHomeBrandPo> homeBrandPoList) {
        homeBrandPoList.forEach(homeBrandPo -> homeBrandPo.setRecommendStatus(1).setSort(0));
        boolean b = service.saveBatch(homeBrandPoList);
        if (b) {
            return Result.success("创建成功!!!!");
        } else {
            return Result.failed("创建失败");
        }
    }

    @ApiOperation("修改推荐品牌排序")
    @PostMapping(value = "/update/sort/{id}")
    public Result updateSort(@PathVariable(value = "id") Long id, Integer sort) {
        int count = service.updateSort(id, sort);
        if (count > 0) {
            return Result.success(count);
        }else {
            return Result.failed();
        }
    }
    @ApiOperation("批量删除推荐品牌")
    @PostMapping(value = "/delete")
    @ResponseBody
    public Result<String> delete(@RequestParam("ids") List<Long> ids) {
        boolean b = service.removeBatchByIds(ids);
        if (b) {
            return Result.success("批量删除推荐品牌成功");
        } else {

            return Result.failed("批量删除推荐品牌失败");
        }
    }

    @ApiOperation("批量修改推荐品牌状态")
    @PostMapping(value = "/update/recommendStatus")
    @ResponseBody
    public Result updateRecommendStatus(@RequestParam("ids") List<Long> ids, @RequestParam Integer recommendStatus) {
        int count = service.updateStatusBatchById(ids, recommendStatus);
        if (count > 0) {
            return Result.success("修改成功");
        } else {
            return Result.failed("修改失败");
        }
    }
    @ApiOperation("分页查询推荐品牌")
    @GetMapping(value = "/list")
    public Result<CommonPage<SmsHomeBrandPo>> list(@RequestParam(value = "brandName", required = false) String brandName,
                                                   @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsHomeBrandPo> homeBrandList = service.list(brandName, recommendStatus, pageSize, pageNum);
        return Result.success(CommonPage.restPage(homeBrandList));
    }
}

