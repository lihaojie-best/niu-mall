package com.niu.mall.admin.controller;

import cn.hutool.core.collection.CollUtil;
import com.niu.mall.admin.param.UmsAdminParam;
import com.niu.mall.admin.param.UmsAdminLoginParam;
import com.niu.mall.admin.param.UmsUpdateAdminPasswordParam;
import com.niu.mall.admin.service.UmsAdminService;
import com.niu.mall.admin.service.UmsRoleService;
import com.niu.mall.common.api.CommonPage;
import com.niu.mall.common.api.Result;
import com.niu.mall.mbg.po.UmsAdminPo;
import com.niu.mall.mbg.po.UmsRolePo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 后台用户表 前端控制器
 *
 * @author lihaojie
 * @date 2022/12/21 21:18
 **/
@RestController
@Api(tags = "UmsAdminController", description = "后台用户管理")
@RequestMapping("/admin")
public class UmsAdminController {
    @Autowired
    private UmsAdminService adminService;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Autowired
    private UmsRoleService roleService;

    /**
     * 用户注册
     *
     * @param umsAdminParam 用户注册参数
     * @return com.niu.mall.common.api.Result<com.niu.mall.mbg.po.UmsAdminPo>
     * @author lihaojie
     * @date 2022/12/21 21:27
     */
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Result<UmsAdminPo> register(@RequestBody @Validated UmsAdminParam umsAdminParam) {
        UmsAdminPo umsAdminPo = adminService.register(umsAdminParam);
        if (umsAdminPo == null) {
            return Result.failed();
        } else {
            return Result.success(umsAdminPo);
        }
    }
    /**
     * 登录并返回token
     *
     * @param umsAdminLoginParam 用户登录参数
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2022/12/30 23:17
     */
    @ApiOperation(value = "登录并返回token")
    @PostMapping("/login")
    public Result login(@RequestBody @Validated UmsAdminLoginParam umsAdminLoginParam) {
        //1.调用login服务
        String token = adminService.login(umsAdminLoginParam);
        //2.判断token是否为null
        if (token == null) {
            return Result.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return Result.success(tokenMap);
    }
    /**
     * 刷新token
     *
     * @param request HttpServletRequest请求
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2022/12/30 23:17
     */
    @ApiOperation(value = "刷新token")
    @GetMapping("/refreshToken")
    public Result refreshToken(HttpServletRequest request) {
        //在请求中获取oldToken
        String oldToken = request.getHeader(tokenHeader);
        //如果oldToken未过期还可以刷新token
        String refreshToken = adminService.refreshToken(oldToken);
        //未得到token将代表着token过期
        if (refreshToken == null) {
            Result.failed("token已过期");
        }
        //将刷新后的token封装为map并返回
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return Result.success(tokenMap);
    }
    /**
     * 获取当前用户信息
     *
     * @param principal Spring Security 基本信息
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2022/12/30 23:17
     */
    @ApiOperation("获取当前用户信息")
    @GetMapping("/info")
    public Result getInfo(Principal principal) {
        //1.判断principal有没有，没有则没有注册登录
        if (principal == null) {
            return Result.unauthorized(null);
        }
        //2.根据principal获取当前用户名
        String name = principal.getName();
        //3.根据用户名获用户全部信息
        UmsAdminPo umsAdminPo = adminService.getAdminByUsername(name);
        //4.将用户信息进一步封装为map
        Map<String, Object> data = new HashMap<>();
        data.put("username", umsAdminPo.getUsername());
        //后台菜单列表
        data.put("menus", roleService.getMenuList(umsAdminPo.getId()));
        //封装头像地址
        data.put("icon", umsAdminPo.getIcon());
        List<UmsRolePo> rolePoList = adminService.getRoleList(umsAdminPo.getId());
        if (CollUtil.isNotEmpty(rolePoList)) {
            List<String> roles = rolePoList.stream().map(UmsRolePo::getName).collect(Collectors.toList());
            data.put("roles", roles);
        }
        return Result.success(data);
    }
    /**
     * 登出功能
     *
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2022/12/30 23:18
     */
    @ApiOperation(value = "登出功能")
    @PostMapping("logout")
    public Result logout() {
        return Result.success(null);
    }
    /**
     * 根据用户名或姓名分页获取用户列表
     *
     * @param keyword 关键词
     * @param pageNum 当前页
     * @param pageSize 页面大小
     * @return com.niu.mall.common.api.Result<com.niu.mall.common.api.CommonPage<com.niu.mall.mbg.po.UmsAdminPo>>
     * @author lihaojie
     * @date 2022/12/30 23:18
     */
    @ApiOperation("根据用户名或姓名分页获取用户列表")
    @GetMapping("/list")
    public Result<CommonPage<UmsAdminPo>> list(@RequestParam(value = "keyword",required = false) String keyword,
                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        List<UmsAdminPo> adminList = adminService.list(keyword, pageSize, pageNum);
        return Result.success(CommonPage.restPage(adminList));
    }
    /**
     * 获取指定用户信息
     *
     * @param id 用户id
     * @return com.niu.mall.common.api.Result<com.niu.mall.mbg.po.UmsAdminPo>
     * @author lihaojie
     * @date 2022/12/30 23:19
     */
    @ApiOperation("获取指定用户信息")
    @GetMapping("/{id}")
    public Result<UmsAdminPo> getItem(@PathVariable Long id) {
        UmsAdminPo umsAdminPo = adminService.getById(id);
        return Result.success(umsAdminPo);
    }
    /**
     * 修改指定用户信息
     *
     * @param id 用户id
     * @param adminPo 修改参数
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2022/12/30 23:19
     */
    @ApiOperation("修改指定用户信息")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable Long id, @RequestBody UmsAdminPo adminPo) {
        int count = adminService.update(id, adminPo);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed("修改失败");
    }
    /**
     * 修改指定用户密码
     *
     * @param updateAdminPasswordParam 修改密码参数类
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2022/12/30 23:19
     */
    @ApiOperation("修改指定用户密码")
    @PostMapping("/updatePassword")
    public Result updatePassword(@RequestBody @Validated UmsUpdateAdminPasswordParam updateAdminPasswordParam){
        int status = adminService.updatePassword(updateAdminPasswordParam);
        if (status > 0) {
            return Result.success(status);
        } else if (status == -1) {
            return Result.failed("提交参数不合法");
        } else if (status == -2) {
            return Result.failed("找不到该用户");
        } else if (status == -3) {
            return Result.failed("旧密码错误");
        } else {
            return Result.failed();
        }
    }
    /**
     * 删除指定用户信息
     *
     * @param id 用户id
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2022/12/30 23:20
     */
    @ApiOperation("删除指定用户信息")
    @PostMapping("/delete/{id}")
    public Result deleteById(@PathVariable Long id){
        int count = adminService.delete(id);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }
    /**
     * 修改帐号状态
     *
     * @param id 用户id
     * @param status 用户状态
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2022/12/30 23:20
     */
    @ApiOperation("修改帐号状态")
    @PostMapping("/updateStatus/{id}")
    public Result updateStatus(@PathVariable Long id,@RequestParam Integer status){
        UmsAdminPo umsAdminPo = new UmsAdminPo().setStatus(status);
        int count = adminService.update(id, umsAdminPo);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }
    /**
     * 给用户分配角色
     *
     * @param adminId 用户id
     * @param roleIds 角色id列表
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2022/12/30 23:20
     */
    @ApiOperation("给用户分配角色")
    @PostMapping("/role/update")
    public Result updateRole(@RequestParam("adminId") Long adminId,
                                   @RequestParam("roleIds") List<Long> roleIds) {
        int count = adminService.updateRole(adminId, roleIds);
        if (count >= 0) {
            return Result.success(count);
        }
        return Result.failed();
    }
    /**
     * 获取指定用户的角色
     *
     * @param adminId 用户id
     * @return com.niu.mall.common.api.Result<java.util.List<com.niu.mall.mbg.po.UmsRolePo>>
     * @author lihaojie
     * @date 2022/12/30 23:21
     */
    @ApiOperation("获取指定用户的角色")
    @GetMapping("/role/{adminId}")
    @ResponseBody
    public Result<List<UmsRolePo>> getRoleList(@PathVariable Long adminId) {
        List<UmsRolePo> roleList = adminService.getRoleList(adminId);
        return Result.success(roleList);
    }

}