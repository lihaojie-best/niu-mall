package com.niu.mall.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niu.mall.admin.dto.PmsProductAttributeCategoryDto;
import com.niu.mall.admin.service.PmsProductAttributeCategoryService;
import com.niu.mall.common.api.CommonPage;
import com.niu.mall.common.api.Result;
import com.niu.mall.mbg.po.PmsProductAttributeCategoryPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品属性分类管理Controller
 *
 * @author lihaojie
 * @date 2022/12/10 19:16
 **/
@RestController
@Api(tags = "PmsProductAttributeCategoryController", description = "商品属性分类管理")
@RequestMapping("/pmsProductAttributeCategoryPo")
public class PmsProductAttributeCategoryController {
    @Autowired
    private PmsProductAttributeCategoryService service;

    /**
     * 添加商品属性分类
     *
     * @param name 属性名称
     * @return com.niu.mall.common.api.Result<java.lang.Boolean>
     * @author lihaojie
     * @date 2022/12/10 20:55
     */
    @ApiOperation("添加商品属性分类")
    @PostMapping("/create")
    @ResponseBody
    public Result<Boolean> creat(@RequestParam String name) {
        boolean save = service.save(new PmsProductAttributeCategoryPo().setName(name));
        if (save) {
            return Result.success(save);
        }
        return Result.failed();
    }

    /**
     * 删除单个商品属性分类
     *
     * @param id 商品属性分类id
     * @return com.niu.mall.common.api.Result<java.lang.Boolean>
     * @author lihaojie
     * @date 2022/12/10 21:02
     */
    @ApiOperation("删除单个商品属性分类")
    @GetMapping("/delete/{id}")
    @ResponseBody
    public Result<Boolean> deleteById(@PathVariable Long id) {
       // QueryWrapper<PmsProductAttributeCategoryPo> whereWrapper = new QueryWrapper<PmsProductAttributeCategoryPo>().eq("id", id);
        boolean b = service.removeById(id);
        if (b) {
            return Result.success(b);
        }
        return Result.failed();
    }

    /**
     * 分页查询商品属性分类
     *
     * @param pageNum
     * @param pageSize
     * @return com.niu.mall.common.api.Result<com.niu.mall.common.api.CommonPage < com.niu.mall.mbg.po.PmsProductAttributeCategoryPo>>
     * @author lihaojie
     * @date 2022/12/10 21:47
     */
    @ResponseBody
    @ApiOperation("分页查询商品属性分类")
    @GetMapping("/getAll")
    public Result<CommonPage<PmsProductAttributeCategoryPo>> getAll(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        List<PmsProductAttributeCategoryPo> list = service.getAll(pageNum, pageSize);
        if (list.isEmpty()) {
            return Result.failed();
        }
        return Result.success(CommonPage.restPage(list));
    }

    /**
     * 获取单个商品属性分类信息
     *
     * @param id
     * @return com.niu.mall.common.api.Result<com.niu.mall.mbg.po.PmsProductAttributeCategoryPo>
     * @author lihaojie
     * @date 2022/12/10 21:47
     */
    @ResponseBody
    @ApiOperation("获取单个商品属性分类信息")
    @GetMapping("/getById/{id}")
    public Result<PmsProductAttributeCategoryPo> getById(@PathVariable Long id) {
        //声明pmsProductAttributeCategoryPo
        PmsProductAttributeCategoryPo pmsProductAttributeCategoryPo;
        try {
            //根据id获取pmsProductAttributeCategoryPo
            pmsProductAttributeCategoryPo = service.getById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Result.success(pmsProductAttributeCategoryPo);
    }

    /**
     * 获取所有商品属性分类及其下属性
     *
     * @return com.niu.mall.common.api.Result<java.util.List < com.niu.mall.admin.dto.PmsProductAttributeCategoryDto>>
     * @author lihaojie
     * @date 2022/12/10 21:47
     */
    @ResponseBody
    @ApiOperation("获取所有商品属性分类及其下属性")
    @GetMapping("/list/withAttr")
    public Result<List<PmsProductAttributeCategoryDto>> getListWithAttr() {
        List<PmsProductAttributeCategoryDto> pmsProductAttributeCategoryDtos;
        try {
            pmsProductAttributeCategoryDtos = service.getListWithAttr();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Result.success(pmsProductAttributeCategoryDtos);
    }
}
