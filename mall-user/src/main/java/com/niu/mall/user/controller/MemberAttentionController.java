package com.niu.mall.user.controller;

import com.niu.mall.common.api.CommonPage;
import com.niu.mall.common.api.Result;
import com.niu.mall.user.domain.MemberBrandAttention;
import com.niu.mall.user.service.MemberAttentionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 会员关注品牌管理Controller
 * Created by lihaojie on 2023/8/2.
 */
@Controller
@Api(tags = "MemberAttentionController", description = "会员关注品牌管理")
@RequestMapping("/member/attention")
public class MemberAttentionController {
    @Autowired
    private MemberAttentionService memberAttentionService;
    @ApiOperation("添加品牌关注")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result add(@RequestBody MemberBrandAttention memberBrandAttention) {
        int count = memberAttentionService.add(memberBrandAttention);
        if(count>0){
            return Result.success(count);
        }else{
            return Result.failed();
        }
    }

    @ApiOperation("取消品牌关注")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(Long brandId) {
        int count = memberAttentionService.delete(brandId);
        if(count>0){
            return Result.success(count);
        }else{
            return Result.failed();
        }
    }

    @ApiOperation("显示当前用户品牌关注列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Result<CommonPage<MemberBrandAttention>> list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Page<MemberBrandAttention> page = memberAttentionService.list(pageNum,pageSize);
        return Result.success(CommonPage.restPage(page));
    }

    @ApiOperation("显示品牌关注详情")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public Result<MemberBrandAttention> detail(@RequestParam Long brandId) {
        MemberBrandAttention memberBrandAttention = memberAttentionService.detail(brandId);
        return Result.success(memberBrandAttention);
    }

    @ApiOperation("清空当前用户品牌关注列表")
    @RequestMapping(value = "/clear", method = RequestMethod.POST)
    @ResponseBody
    public Result clear() {
        memberAttentionService.clear();
        return Result.success(null);
    }
}
