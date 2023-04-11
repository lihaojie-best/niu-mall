package com.niu.mall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niu.mall.dto.PmsProductCategoryWithChildrenDto;
import com.niu.mall.param.PmsProductCategoryParam;
import com.niu.mall.service.PmsProductCategoryService;
import com.niu.mall.common.api.CommonPage;
import com.niu.mall.common.api.Result;
import com.niu.mall.common.exception.ApiException;
import com.niu.mall.po.PmsProductCategoryPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品属性分离管理Controller
 *
 * @author lihaojie
 * @date 2022/12/12 20:48
 **/
@Api(tags = "PmsProductAttributeController", description = "商品属性管理")
@RestController
@RequestMapping("/productCategory")
public class PmsProductCategoryController {
    @Autowired
    private PmsProductCategoryService productCategoryService;

    /**
     * 添加商品分类
     *
     * @param productCategoryParam 商品分类实体类
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2022/12/21 16:21
     */
    @ApiOperation("添加商品分类")
    @PostMapping("/create")
    public Result creat(@Validated @RequestBody PmsProductCategoryParam productCategoryParam) {
        int count;
        try {
            count = productCategoryService.create(productCategoryParam);
        } catch (Exception e) {
            throw new ApiException(e);
        }
        //判断是否创建成功
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    /**
     * 修改商品分类
     *
     * @param id                   商品分类id
     * @param productCategoryParam 修改参数
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2022/12/18 20:11
     */
    @ApiOperation("修改商品分类")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable Long id,
                         @Validated @RequestBody PmsProductCategoryParam productCategoryParam) {
        int count = 0;
        try {
            count = productCategoryService.update(id, productCategoryParam);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.failed();
        }

    }

    /**
     * 根据分类的父级id分页查询子分类
     *
     * @param parentId 父分类id
     * @param pageNum  当前分页
     * @param pageSize 分页大小
     * @return com.niu.mall.common.api.Result<com.niu.mall.common.api.CommonPage < PmsProductCategoryPo>>
     * @author lihaojie
     * @date 2022/12/19 10:40
     */
    @ApiOperation("根据分类的父级id分页查询子分类")
    @GetMapping("/list/{parentId}")
    public Result<CommonPage<PmsProductCategoryPo>> listByCategoryParentId(@PathVariable Long parentId
            , @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                           @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        List<PmsProductCategoryPo> productCategoryPoList = null;
        try {
            productCategoryPoList = productCategoryService.listByParentId(parentId, pageNum, pageSize);
        } catch (Exception e) {
            throw new ApiException(e);
        }
        return Result.success(CommonPage.restPage(productCategoryPoList));
    }

    /**
     * 根据分类id获取商品分类
     *
     * @param id 分类id
     * @return com.niu.mall.common.api.Result<PmsProductCategoryPo>
     * @author lihaojie
     * @date 2022/12/19 11:03
     */
    @ApiOperation("根据分类id获取商品分类")
    @GetMapping("/{id}")
    public Result<PmsProductCategoryPo> getById(@PathVariable Long id) {
        PmsProductCategoryPo pmsProductCategoryPo = null;
        try {
            //调用baseService
            pmsProductCategoryPo = productCategoryService.getById(id);
        } catch (Exception e) {
            throw new ApiException(e);
        }
        return Result.success(pmsProductCategoryPo);
    }

    /**
     * 查询所有一级分类及子分类
     *
     * @return com.niu.mall.common.api.Result<java.util.List < PmsProductCategoryWithChildrenDto>>
     * @author lihaojie
     * @date 2022/12/19 11:13
     */
    @ApiOperation("查询所有一级分类及子分类")
    @GetMapping("/list/withChildren")
    public Result<List<PmsProductCategoryWithChildrenDto>> getWithChildren() {
        List<PmsProductCategoryWithChildrenDto> productCategoryWithChildrenDtoList = null;
        try {
            productCategoryWithChildrenDtoList = productCategoryService.getWithChildren();
        } catch (Exception e) {
            throw new ApiException(e);
        }
        return Result.success(productCategoryWithChildrenDtoList);
    }

    /**
     * 修改导航栏显示状态
     *
     * @param ids       要修改显示状态的id列表
     * @param navStatus 显示状态 0 -> 不显示 1 -> 显示
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2022/12/21 16:22
     */
    @ApiOperation("修改导航栏显示状态")
    @PostMapping("/update/navStatus")
    public Result updateNavStatus(@RequestParam("ids") List<Long> ids, @RequestParam("navStatus") Integer navStatus) {
        boolean updateFlag = false;
        try {
            //参数1: updateEntity 参数2：whereWrapper
            updateFlag = productCategoryService.update(new PmsProductCategoryPo().setNavStatus(navStatus),
                    new QueryWrapper<PmsProductCategoryPo>().in("id", ids));
        } catch (Exception e) {
            //抛出自定义异常
            throw new ApiException(e);
        }
        //判断更新是否成功
        if (updateFlag) {
            return Result.success(true);
        } else {
            return Result.failed();
        }
    }

    /**
     * 修改显示状态
     *
     * @param ids        要修改状态的id列表
     * @param showStatus 显示状态 0 -> 不显示 1 -> 显示
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2022/12/21 16:24
     */
    @ApiOperation("修改显示状态")
    @PostMapping("/update/showStatus")
    public Result updateShowStatus(@RequestParam("ids") List<Long> ids, @RequestParam("showStatus") Integer showStatus) {
        boolean update = false;
        try {
            update = productCategoryService.update(new PmsProductCategoryPo().setShowStatus(showStatus),
                    new QueryWrapper<PmsProductCategoryPo>().in("id", ids));
        } catch (Exception e) {
            throw new ApiException(e);
        }
        if (update) {
            return Result.success(true);
        } else {
            return Result.failed();
        }
    }

    /**
     * 删除商品分类
     *
     * @param id 要删除的分类的id
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2022/12/21 16:26
     */
    @ApiOperation("删除商品分类")
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable Long id) {
        boolean deleteFlag = false;
        try {
            deleteFlag = productCategoryService.remove(new QueryWrapper<PmsProductCategoryPo>().eq("id", id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (deleteFlag) {
            return Result.success(true);
        } else {
            return Result.failed();
        }
    }
}
