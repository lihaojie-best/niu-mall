package com.niu.mall.controller;

import com.niu.mall.dto.UmsMenuNodeDto;
import com.niu.mall.service.UmsMenuService;
import com.niu.mall.common.api.CommonPage;
import com.niu.mall.common.api.Result;
import com.niu.mall.po.UmsMenuPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台菜单表 前端控制器
 *
 * @author lihaojie
 * @date 2023/01/02 14:18
 **/
@RestController
@Api(tags = "UmsMenuController", description = "后台菜单管理")
@RequestMapping("/menu")
public class UmsMenuController {
    @Autowired
    private UmsMenuService menuService;

    @ApiOperation("添加后台菜单")
    @PostMapping("/create")
    public Result create(@RequestBody UmsMenuPo umsMenuPo) {
        int count = menuService.create(umsMenuPo);
        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.failed();
        }
    }

    @ApiOperation("修改后台菜单")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable Long id,
                               @RequestBody UmsMenuPo umsMenu) {
        int count = menuService.update(id, umsMenu);
        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.failed();
        }
    }
    @ApiOperation("根据ID获取菜单详情")
    @GetMapping("/{id}")
    @ResponseBody
    public Result<UmsMenuPo> getItem(@PathVariable Long id) {
        UmsMenuPo umsMenu = menuService.getById(id);
        return Result.success(umsMenu);
    }
    @ApiOperation("根据ID删除后台菜单")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@PathVariable Long id) {
        int count = 0;
        try {
            //删除菜单
            count = menuService.getBaseMapper().deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //判断删除是否成功
        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.failed("菜单删除失败");
        }
    }
    @ApiOperation("分页查询后台菜单")
    @GetMapping("/list/{parentId}")
    @ResponseBody
    public Result<CommonPage<UmsMenuPo>> list(@PathVariable Long parentId,
                                                    @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UmsMenuPo> menuList = menuService.list(parentId, pageSize, pageNum);
        return Result.success(CommonPage.restPage(menuList));
    }
    @ApiOperation("树形结构返回所有菜单列表")
    @GetMapping("/treeList")
    @ResponseBody
    public Result<List<UmsMenuNodeDto>> treeList() {
        List<UmsMenuNodeDto> list = menuService.treeList();
        return Result.success(list);
    }
    @ApiOperation("修改菜单显示状态")
    @PostMapping("/updateHidden/{id}")
    public Result updateHidden(@PathVariable Long id, @RequestParam("hidden") Integer hidden) {
        int count = menuService.updateHidden(id, hidden);
        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.failed();
        }
    }

}
