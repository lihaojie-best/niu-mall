package com.niu.mall.user.controller;


import com.niu.mall.common.api.Result;
import com.niu.mall.user.domain.CartPromotionItem;
import com.niu.mall.user.domain.SmsCouponHistoryDetail;
import com.niu.mall.user.po.SmsCouponHistoryPo;
import com.niu.mall.user.po.SmsCouponPo;
import com.niu.mall.user.service.OmsCartItemService;
import com.niu.mall.user.service.UmsMemberCouponService;
import com.niu.mall.user.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员优惠券管理Controller
 * Created by lihaojie on 2023/8/29.
 */
@Controller
@Api(tags = "UmsMemberCouponController", description = "用户优惠券管理")
@RequestMapping("/member/coupon")
public class UmsMemberCouponController {
    @Autowired
    private UmsMemberCouponService memberCouponService;
    @Autowired
    private OmsCartItemService cartItemService;
    @Autowired
    private UmsMemberService memberService;

    @ApiOperation("领取指定优惠券")
    @RequestMapping(value = "/add/{couponId}", method = RequestMethod.POST)
    @ResponseBody
    public Result add(@PathVariable Long couponId) {
        memberCouponService.add(couponId);
        return Result.success(null,"领取成功");
    }

    @ApiOperation("获取会员优惠券历史列表")
    @ApiImplicitParam(name = "useStatus", value = "优惠券筛选类型:0->未使用；1->已使用；2->已过期",
            allowableValues = "0,1,2", paramType = "query", dataType = "integer")
    @RequestMapping(value = "/listHistory", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<SmsCouponHistoryPo>> listHistory(@RequestParam(value = "useStatus", required = false) Integer useStatus) {
        List<SmsCouponHistoryPo> couponHistoryList = memberCouponService.listHistory(useStatus);
        return Result.success(couponHistoryList);
    }

    @ApiOperation("获取会员优惠券列表")
    @ApiImplicitParam(name = "useStatus", value = "优惠券筛选类型:0->未使用；1->已使用；2->已过期",
            allowableValues = "0,1,2", paramType = "query", dataType = "integer")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<SmsCouponPo>> list(@RequestParam(value = "useStatus", required = false) Integer useStatus) {
        List<SmsCouponPo> couponList = memberCouponService.list(useStatus);
        return Result.success(couponList);
    }

    @ApiOperation("获取登录会员购物车的相关优惠券")
    @ApiImplicitParam(name = "type", value = "使用可用:0->不可用；1->可用",
            defaultValue = "1", allowableValues = "0,1", paramType = "path", dataType = "integer")
    @RequestMapping(value = "/list/cart/{type}", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<SmsCouponHistoryDetail>> listCart(@PathVariable Integer type) {
        List<CartPromotionItem> cartPromotionItemList = cartItemService.listPromotion(memberService.getCurrentMember().getId(), null);
        List<SmsCouponHistoryDetail> couponHistoryList = memberCouponService.listCart(cartPromotionItemList, type);
        return Result.success(couponHistoryList);
    }

    @ApiOperation("获取当前商品相关优惠券")
    @RequestMapping(value = "/listByProduct/{productId}", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<SmsCouponPo>> listByProduct(@PathVariable Long productId) {
        List<SmsCouponPo> couponHistoryList = memberCouponService.listByProduct(productId);
        return Result.success(couponHistoryList);
    }
}
