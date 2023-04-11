package com.niu.mall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niu.mall.common.api.CommonPage;
import com.niu.mall.common.api.Result;
import com.niu.mall.po.SmsHomeRecommendProductPo;
import com.niu.mall.service.SmsHomeRecommendProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 人气推荐商品表 前端控制器
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@RestController
@RequestMapping("/home/recommendProduct")
@Api(tags = "SmsHomeRecommendProductController", value = "首页人气推荐管理")
public class SmsHomeRecommendProductController {
    @Autowired
    private SmsHomeRecommendProductService recommendProductService;


    @ApiOperation("添加首页推荐")
    @PostMapping(value = "/create")
    public Result create(@RequestBody List<SmsHomeRecommendProductPo> homeRecommendProductList) {
        boolean saveBatch = recommendProductService.saveBatch(homeRecommendProductList);
        if (saveBatch) {
            return Result.success("添加成功");
        }else {
            return Result.failed("添加失败");
        }
    }

    @ApiOperation("修改推荐排序")
    @PostMapping(value = "/update/sort/{id}")
    public Result updateSort(@PathVariable Long id, Integer sort) {
        int count = recommendProductService.updateSort(id, sort);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    @ApiOperation("批量删除推荐")
    @PostMapping(value = "/delete")
    public Result delete(@RequestParam("ids") List<Long> ids) {
        boolean b = recommendProductService.removeBatchByIds(ids);
        if (b) {
            return Result.success("删除成功");
        }else {
            return Result.failed("删除失败");
        }
    }

    @ApiOperation("批量修改推荐状态")
    @PostMapping(value = "/update/recommendStatus")
    public Result updateRecommendStatus(@RequestParam("ids") List<Long> ids, @RequestParam Integer recommendStatus) {
        boolean b = recommendProductService.update(new SmsHomeRecommendProductPo().setRecommendStatus(recommendStatus), new QueryWrapper<SmsHomeRecommendProductPo>().in("id", ids));
        if (b) {
            return Result.success("修改成功");
        }else {
            return Result.failed("修改失败");
        }
    }

    @ApiOperation("分页查询推荐")
    @GetMapping(value = "/list")
    public Result<CommonPage<SmsHomeRecommendProductPo>> list(@RequestParam(value = "productName", required = false) String productName,
                                                                  @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsHomeRecommendProductPo> homeRecommendProductList = recommendProductService.list(productName, recommendStatus, pageSize, pageNum);
        return Result.success(CommonPage.restPage(homeRecommendProductList));
    }
}
