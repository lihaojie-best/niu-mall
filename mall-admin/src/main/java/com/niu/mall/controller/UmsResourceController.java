package com.niu.mall.controller;

import com.niu.mall.service.UmsResourceService;
import com.niu.mall.common.api.CommonPage;
import com.niu.mall.common.api.Result;
import com.niu.mall.po.UmsResourcePo;
import com.niu.mall.security.component.DynamicSecurityMetadataSource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台资源表 前端控制器
 *
 * @author lihaojie
 * @date 2023/01/02 15:13
 **/
@RestController
@Api(tags = "UmsResourceController", description = "后台资源管理")
@RequestMapping("/resource")
public class UmsResourceController {

    @Autowired
    private UmsResourceService resourceService;
    @Autowired
    private DynamicSecurityMetadataSource dynamicSecurityMetadataSource;

    @ApiOperation("添加后台资源")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Result create(@RequestBody UmsResourcePo umsResource) {
        int count = resourceService.create(umsResource);
        // 后台资源规则被缓存在了一个Map对象之中，所以当后台资源发生变化时，我们需要清空缓存的数据
        dynamicSecurityMetadataSource.clearDataSource();
        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.failed();
        }
    }

    @ApiOperation("修改后台资源")
    @PostMapping("/update/{id}")
    public Result updateById(@PathVariable Long id, @RequestBody UmsResourcePo resourcePo) {
        int count = resourceService.updateById(id, resourcePo);
        dynamicSecurityMetadataSource.clearDataSource();
        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.failed("修改失败");
        }
    }

    @ApiOperation("根据ID获取资源详情")
    @GetMapping("/getById/{id}")
    public Result<UmsResourcePo> getBuId(@PathVariable Long id) {
        UmsResourcePo umsResourcePo = null;
        try {
            //查询
            umsResourcePo = resourceService.getById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Result.success(umsResourcePo);
    }

    @ApiOperation("根据ID删除后台资源")
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable Long id) {
        int count = resourceService.deleteById(id);
        dynamicSecurityMetadataSource.clearDataSource();
        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.failed("删除失败");
        }
    }
    @ApiOperation("分页模糊查询后台资源")
    @GetMapping("/list")
    @ResponseBody
    public Result<CommonPage<UmsResourcePo>> list(@RequestParam(required = false) Long categoryId,
                                                        @RequestParam(required = false) String nameKeyword,
                                                        @RequestParam(required = false) String urlKeyword,
                                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UmsResourcePo> resourceList = resourceService.list(categoryId,nameKeyword, urlKeyword, pageSize, pageNum);
        return Result.success(CommonPage.restPage(resourceList));
    }
    @ApiOperation("查询所有后台资源")
    @GetMapping("/listAll")
    @ResponseBody
    public Result<List<UmsResourcePo>> listAll() {
        List<UmsResourcePo> resourceList = null;
        try {
            //获取资源
            resourceList = resourceService.list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Result.success(resourceList);
    }
}
