package com.niu.mall.controller;

import com.niu.mall.service.UmsResourceCategoryService;
import com.niu.mall.common.api.Result;
import com.niu.mall.po.UmsResourceCategoryPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 资源分类表 前端控制器
 *
 * @author lihaojie
 * @date 2023/01/02 19:19
 **/
@RestController
@Api(tags = "UmsResourceCategoryController", description = "后台资源分类管理")
@RequestMapping("/resourceCategory")
public class UmsResourceCategoryController {
    @Autowired
    private UmsResourceCategoryService resourceCategoryService;

    @ApiOperation("查询所有后台资源分类")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<UmsResourceCategoryPo>> listAll() {
        List<UmsResourceCategoryPo> resourceList = null;
        try {
            //获取list
            resourceList = resourceCategoryService.list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Result.success(resourceList);
    }
    @ApiOperation("添加后台资源分类")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Result create(@RequestBody UmsResourceCategoryPo umsResourceCategory) {
        int count = 0;
        try {
            //插入
            count = resourceCategoryService.getBaseMapper().insert(umsResourceCategory);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.failed();
        }
    }

    @ApiOperation("修改后台资源分类")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Result update(@PathVariable Long id,
                               @RequestBody UmsResourceCategoryPo umsResourceCategory) {
        int count = 0;
        try {
            //修改
            count = resourceCategoryService.getBaseMapper().deleteById(umsResourceCategory.setId(id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //判断是否更新成功
        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.failed();
        }
    }

    @ApiOperation("根据ID删除后台资源")
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable Long id) {
        int count = 0;
        try {
            //删除
            count = resourceCategoryService.getBaseMapper().deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.failed();
        }
    }
}
