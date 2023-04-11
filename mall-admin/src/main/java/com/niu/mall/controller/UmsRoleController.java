package com.niu.mall.controller;

import com.niu.mall.common.api.CommonPage;
import com.niu.mall.common.api.Result;
import com.niu.mall.po.UmsMenuPo;
import com.niu.mall.po.UmsResourcePo;
import com.niu.mall.po.UmsRolePo;
import com.niu.mall.service.UmsRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台用户角色管理Controller
 *
 * @author lihaojie
 * @date 2023/01/01 21:08
 **/
@RestController
@Api(tags = "UmsRoleController", value = "后台用户角色管理")
@RequestMapping("/role")
public class UmsRoleController {

    @Autowired
    private UmsRoleService roleService;

    @ApiOperation("添加角色")
    @PostMapping("/create")
    public Result creat(@RequestBody UmsRolePo rolePo) {
        int count = roleService.creat(rolePo);
        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.failed("创建失败");
        }
    }

    @ApiOperation("修改角色")
    @PostMapping("/update/{id}")
    public Result updateById(@PathVariable("id") Long id, @RequestBody UmsRolePo rolePo) {
        int count = roleService.updateById(id, rolePo);
        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.failed("更新失败");
        }
    }

    @ApiOperation("批量删除角色")
    @PostMapping("/delete")
    public Result deleteByIds(@RequestParam("ids") List<Long> ids) {
        int count = roleService.deleteBatchByIds(ids);
        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.failed("删除失败");
        }
    }

    @ApiOperation("获取所有角色")
    @GetMapping("/listAll")
    public Result<List<UmsRolePo>> listAll() {
        List<UmsRolePo> roleList = null;
        try {
            //获取全部
            roleList = roleService.list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Result.success(roleList);
    }

    @ApiOperation("根据角色名称分页获取角色列表")
    @GetMapping("/list")
    public Result<CommonPage<UmsRolePo>> listByRoleName(@RequestParam(value = "keyword", required = false) String keyword,
                                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UmsRolePo> roleList = roleService.list(keyword, pageSize, pageNum);
        return Result.success(CommonPage.restPage(roleList));
    }

    @ApiOperation("修改角色状态")
    @PostMapping("/updateStatus/{id}")
    public Result updateStatus(@PathVariable Long id, @RequestParam(value = "status") Integer status) {
        int count = roleService.updateStatus(id, status);
        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.failed("修改角色状态");
        }
    }

    @ApiOperation("获取角色相关菜单")
    @GetMapping("/listMenu/{roleId}")
    public Result<List<UmsMenuPo>> listMenu(@PathVariable Long roleId) {
        List<UmsMenuPo> menuPoList = roleService.getMenuListByRoleId(roleId);

        return Result.success(menuPoList);
    }

    @ApiOperation("获取角色相关资源")
    @GetMapping("/listResource/{roleId}")
    public Result listResourceByRoleId(@PathVariable Long roleId) {
        List<UmsResourcePo> resourcePoList = roleService.getResourceByRoleId(roleId);
        return Result.success(resourcePoList);
    }

    @ApiOperation("给角色分配菜单")
    @PostMapping("/allocMenu")

    public Result allocMenu(@RequestParam Long roleId, @RequestParam List<Long> menuIds) {
        int count = roleService.allocMenu(roleId, menuIds);
        return Result.success(count);
    }

    @ApiOperation("给角色分配资源")
    @PostMapping("/allocResource")
    public Result allocResource(@RequestParam Long roleId, @RequestParam List<Long> resourceIds) {
        int count = roleService.allocResource(roleId, resourceIds);
        return Result.success(count);
    }

}
