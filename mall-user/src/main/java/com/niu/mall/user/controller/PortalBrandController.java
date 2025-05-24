package com.niu.mall.user.controller;

import com.niu.mall.common.api.CommonPage;
import com.niu.mall.common.api.Result;
import com.niu.mall.user.po.PmsBrandPo;
import com.niu.mall.user.po.PmsProductPo;
import com.niu.mall.user.service.PortalBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 首页品牌推荐管理Controller
 * Created by macro on 2020/5/15.
 */
@Controller
@Api(tags = "PortalBrandController", description = "前台品牌管理")
@RequestMapping("/brand")
public class PortalBrandController {

    @Autowired
    private PortalBrandService homeBrandService;

    @ApiOperation("分页获取推荐品牌")
    @RequestMapping(value = "/recommendList", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<PmsBrandPo>> recommendList(@RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<PmsBrandPo> brandList = homeBrandService.recommendList(pageNum, pageSize);
        return Result.success(brandList);
    }

    @ApiOperation("获取品牌详情")
    @RequestMapping(value = "/detail/{brandId}", method = RequestMethod.GET)
    @ResponseBody
    public Result<PmsBrandPo> detail(@PathVariable Long brandId) {
        PmsBrandPo brand = homeBrandService.detail(brandId);
        return Result.success(brand);
    }

    @ApiOperation("分页获取品牌相关商品")
    @RequestMapping(value = "/productList", method = RequestMethod.GET)
    @ResponseBody
    public Result<CommonPage<PmsProductPo>> productList(@RequestParam Long brandId,
                                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize) {
        CommonPage<PmsProductPo> result = homeBrandService.productList(brandId,pageNum, pageSize);
        return Result.success(result);
    }
}
