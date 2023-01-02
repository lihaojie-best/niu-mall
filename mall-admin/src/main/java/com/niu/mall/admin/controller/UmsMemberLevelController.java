package com.niu.mall.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niu.mall.admin.service.UmsMemberLevelService;
import com.niu.mall.common.api.Result;
import com.niu.mall.mbg.po.UmsMemberLevelPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员等级表 前端控制器
 *
 * @author lihaojie
 * @date 2023/01/02 14:16
 **/
@RestController
@RequestMapping("/memberLevel")
@Api(tags = "UmsMemberLevelController", value = "会员等级管理" )
public class UmsMemberLevelController {
    @Autowired
    private UmsMemberLevelService memberLevelService;
    @ApiOperation("查询所有会员等级")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<UmsMemberLevelPo>> list(@RequestParam("defaultStatus") Integer defaultStatus) {
        List<UmsMemberLevelPo> memberLevelList = memberLevelService.list(new QueryWrapper<UmsMemberLevelPo>().eq("default_status",defaultStatus));
        return Result.success(memberLevelList);
    }
}
