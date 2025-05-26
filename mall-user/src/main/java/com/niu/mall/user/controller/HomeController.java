package com.niu.mall.user.controller;


import com.niu.mall.common.api.Result;
import com.niu.mall.user.domain.HomeContentResult;
import com.niu.mall.user.po.CmsSubjectPo;
import com.niu.mall.user.po.PmsProductCategoryPo;
import com.niu.mall.user.po.PmsProductPo;
import com.niu.mall.user.service.HomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 首页内容管理Controller
 * Created by lihaojie on 2023/1/28.
 */
@Controller
@Api(tags = "HomeController", description = "首页内容管理")
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private HomeService homeService;

    @ApiOperation("首页内容信息展示")
    @RequestMapping(value = "/content", method = RequestMethod.GET)
    @ResponseBody
    public Result<HomeContentResult> content() {
        HomeContentResult contentResult = homeService.content();
        return Result.success(contentResult);
    }

    @ApiOperation("分页获取推荐商品")
    @RequestMapping(value = "/recommendProductList", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<PmsProductPo>> recommendProductList(@RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
                                                           @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<PmsProductPo> productList = homeService.recommendProductList(pageSize, pageNum);
        return Result.success(productList);
    }

    @ApiOperation("获取首页商品分类")
    @RequestMapping(value = "/productCateList/{parentId}", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<PmsProductCategoryPo>> getProductCateList(@PathVariable Long parentId) {
        List<PmsProductCategoryPo> productCategoryList = homeService.getProductCateList(parentId);
        return Result.success(productCategoryList);
    }

    @ApiOperation("根据分类获取专题")
    @RequestMapping(value = "/subjectList", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<CmsSubjectPo>> getSubjectList(@RequestParam(required = false) Long cateId,
                                                     @RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
                                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<CmsSubjectPo> subjectList = homeService.getSubjectList(cateId,pageSize,pageNum);
        return Result.success(subjectList);
    }

    @ApiOperation("分页获取人气推荐商品")
    @RequestMapping(value = "/hotProductList", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<PmsProductPo>> hotProductList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                         @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize) {
        List<PmsProductPo> productList = homeService.hotProductList(pageNum,pageSize);
        return Result.success(productList);
    }

    @ApiOperation("分页获取新品推荐商品")
    @RequestMapping(value = "/newProductList", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<PmsProductPo>> newProductList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                         @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize) {
        List<PmsProductPo> productList = homeService.newProductList(pageNum,pageSize);
        return Result.success(productList);
    }
}
