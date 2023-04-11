package com.niu.mall.controller;

import com.niu.mall.param.SmsCouponParam;
import com.niu.mall.service.SmsCouponService;
import com.niu.mall.common.api.CommonPage;
import com.niu.mall.common.api.Result;
import com.niu.mall.common.exception.ApiException;
import com.niu.mall.po.SmsCouponPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 优惠券表 前端控制器
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Api(tags = "SmsCouponController", description = "优惠券管理")
@RestController
@RequestMapping("/coupon")
public class SmsCouponController {
    @Autowired
    private SmsCouponService couponService;

    /**
     * 添加优惠卷
     *
     * @param couponParam  优惠卷实体类
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2023/04/05 19:49
     */
    @ApiOperation("添加优惠卷")
    @PostMapping("/create")
    public Result creatCoupon(@RequestBody SmsCouponParam couponParam) {
        int count = couponService.create(couponParam);
        if (count > 0) {
            return Result.success(count);
        }else {
            return Result.failed();
        }
    }
    /**
     * 更新优惠卷
     *
     * @param smsCouponParam  优惠卷实体类
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2023/04/06 09:43
     */
    @ApiOperation("更新优惠卷")
    @PostMapping("/update")
    public Result updateCoupon(@RequestBody SmsCouponParam smsCouponParam,Long id) {
        int count = couponService.update(smsCouponParam,id);
        if (count > 0) {
            return Result.success(count);
        }else {
            return Result.failed();
        }
    }
    /**
     * 根据couponId删除Coupon
     *
     * @param id  couponId
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2023/04/06 10:18
     */
    @ApiOperation("删除优惠卷")
    @PostMapping("/delete/{id}")
    public Result deleteCoupon(@PathVariable Long id) {
        //删除
        int count = couponService.delete(id);
        if (count > 0) {
            return Result.success(count);
        }else{
            return Result.failed();
        }
    }
    /**
     * 获取单个优惠券的详细信息
     *
     * @param id  CouponId
     * @return com.niu.mall.common.api.Result<SmsCouponParam>
     * @author lihaojie
     * @date 2023/04/06 10:33
     */
    @ApiOperation("获取单个优惠券的详细信息")
    @GetMapping("/{id}")
    public Result<SmsCouponParam> getItem(@PathVariable Long id) {
        SmsCouponParam couponParam = null;
        couponParam = couponService.selectById(id);
        return Result.success(couponParam);
    }
    /**
     * 根据优惠券名称和类型分页获取优惠券列表
     *
     * @param name 分页名称
     * @param type 分页类型
     * @param pageSize 分页大小
     * @param pageNum  当前分页数量
     * @return com.niu.mall.common.api.Result<com.niu.mall.common.api.CommonPage<SmsCouponPo>>
     * @author lihaojie
     * @date 2023/04/06 10:50
     */
    @ApiOperation("根据优惠券名称和类型分页获取优惠券列表")
    @GetMapping("/list")
    public Result<CommonPage<SmsCouponPo>> list(
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "type",required = false) Integer type,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsCouponPo> couponList = null;
        try {
            //查询数据库
            couponList = couponService.list(name,type,pageSize,pageNum);
        } catch (Exception e) {
            //抛出自定义异常
            throw new ApiException(e);
        }
        return Result.success(CommonPage.restPage(couponList));
    }

}
