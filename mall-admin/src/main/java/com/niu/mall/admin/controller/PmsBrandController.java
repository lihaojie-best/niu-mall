package com.niu.mall.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niu.mall.admin.service.PmsBrandService;
import com.niu.mall.common.api.CommonPage;
import com.niu.mall.common.api.Result;
import com.niu.mall.mbg.po.PmsBrandPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 品牌表 前端控制器
 *
 * @author lihaojie
 * @date 2022/12/07 10:49
 **/
@Api(tags = "PmsBrandController", description = "商品品牌管理")
@RestController
@RequestMapping("/brand")
public class PmsBrandController {
    @Autowired
    private PmsBrandService brandService;

    /**
     * 创建品牌
     *
     * @param brand 品牌实体类
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2022/12/07 08:31
     */
    @ApiOperation("创建品牌")
    @PostMapping("/create")
    @ResponseBody
    public Result creat(@Validated @RequestBody @ApiParam("品牌实体类") PmsBrandPo brand) {
        int count = brandService.creat(brand);
        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.failed("创建失败");
        }
    }


    /**
     * 删除品牌
     *
     * @param id 品牌id
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2022/12/07 10:31
     */
    @ApiOperation("删除品牌")
    @PostMapping("/delete/{id}")
    @ResponseBody
    public Result delete(@PathVariable long id) {
        boolean flag = brandService.removeById(id);
        if (flag) {
            return Result.success(flag);
        } else {
            return Result.failed();
        }
    }

    /**
     * 批量删除
     *
     * @param ids 品牌id集合
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2022/12/07 10:32
     */
    @ApiOperation("批量删除品牌")
    @PostMapping("/delete/batch")
    @ResponseBody
    public Result deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean flag = brandService.removeBatchByIds(ids);
        if (flag) {
            return Result.success(flag);
        } else {
            return Result.failed();
        }
    }

    /**
     * 更新品牌
     *
     * @param id      品牌id
     * @param brandPo 品牌实体类
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2022/12/07 10:30
     */
    @ApiOperation("更新品牌")
    @PostMapping("/update/{id}")
    @ResponseBody
    public Result update(@PathVariable long id,
                         @Validated @RequestBody PmsBrandPo brandPo) {
        QueryWrapper whereWrapper = new QueryWrapper<>().eq("id", id);
        boolean flag = brandService.update(brandPo, whereWrapper);
        if (flag) {
            return Result.success(flag);
        } else {
            return Result.failed();
        }
    }

    /**
     * 批量更新显示状态
     *
     * @param ids        品牌id集合
     * @param showStatus 显示状态
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2022/12/07 10:34
     */
    @ApiOperation("批量更新显示状态")
    @PostMapping("/update/showStatus")
    @ResponseBody
    public Result updateShowStatue(@RequestParam("ids") List<Long> ids,
                                   @RequestParam("showStatus") Integer showStatus) {
        QueryWrapper whereWrapper = new QueryWrapper<>().in("id", ids);
        boolean flag = brandService.update(new PmsBrandPo().setShowStatus(showStatus), whereWrapper);
        if (flag) {
            return Result.success(flag);
        } else {
            return Result.failed();
        }
    }

    /**
     * 批量更新厂家制造商状态
     *
     * @param ids           品牌id集合
     * @param factoryStatus 制造商状态
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2022/12/07 10:34
     */
    @ApiOperation("批量更新厂家制造商状态")
    @PostMapping("/update/factoryStatus")
    @ResponseBody
    public Result updateFactoryStatus(@RequestParam("ids") List<Long> ids,
                                      @RequestParam("factoryStatus") Integer factoryStatus) {
        boolean flag = brandService.update(new PmsBrandPo().setFactoryStatus(factoryStatus), new QueryWrapper<PmsBrandPo>().in("id", ids));
        if (flag) {
            return Result.success(flag);
        } else {
            return Result.failed();
        }
    }


    /**
     * 根据id查询品牌
     *
     * @param id 品牌id
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2022/12/07 10:33
     */
    @ApiOperation("根据id查询品牌")
    @GetMapping("/select/{id}")
    @ResponseBody
    public Result select(@PathVariable() Long id) {
        PmsBrandPo pmsBrandPo = brandService.getById(id);
        return Result.success(pmsBrandPo);
    }

    /**
     * 查询全部
     *
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2022/11/26 12:48
     */
    @ApiOperation(value = "查询全部")
    @GetMapping(value = "/listAll")
    @ResponseBody
    public Result getList() {
        List<PmsBrandPo> list = brandService.list();
        return Result.success(list);
    }

    /**
     * 模糊查询
     *
     * @param keyword  关键字，可以无
     * @param pageNum  分页当前页
     * @param pageSize 分页大小
     * @return com.niu.mall.common.api.Result<com.niu.mall.common.api.CommonPage < com.niu.mall.mbg.po.PmsBrandPo>>
     * @author lihaojie
     * @date 2022/12/07 10:32
     */
    @ApiOperation("模糊查询")
    @PostMapping("/list")
    @ResponseBody
    public Result<CommonPage<PmsBrandPo>> getList(@RequestParam(value = "keyword", required = false) String keyword,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                                  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<PmsBrandPo> brandPoList = brandService.listByKeyword(keyword, pageNum, pageSize);
        return Result.success(CommonPage.restPage(brandPoList));
    }


}
