package com.niu.mall.user.controller;

import com.niu.mall.common.api.Result;
import com.niu.mall.user.po.OmsCartItemPo;
import com.niu.mall.user.domain.CartProduct;
import com.niu.mall.user.domain.CartPromotionItem;
import com.niu.mall.user.service.OmsCartItemService;
import com.niu.mall.user.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 购物车管理Controller
 * Created by lihaojie on 2023/8/2.
 */
@Controller
@Api(tags = "OmsCartItemController", description = "购物车管理")
@RequestMapping("/cart")
public class OmsCartItemController {
    @Autowired
    private OmsCartItemService cartItemService;
    @Autowired
    private UmsMemberService memberService;

    @ApiOperation("添加商品到购物车")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result add(@RequestBody OmsCartItemPo cartItem) {
        int count = cartItemService.add(cartItem);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    @ApiOperation("获取当前会员的购物车列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<OmsCartItemPo>> list() {
        List<OmsCartItemPo> cartItemList = cartItemService.list(memberService.getCurrentMember().getId());
        return Result.success(cartItemList);
    }

    @ApiOperation("获取当前会员的购物车列表,包括促销信息")
    @RequestMapping(value = "/list/promotion", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<CartPromotionItem>> listPromotion(@RequestParam(required = false) List<Long> cartIds) {
        List<CartPromotionItem> cartPromotionItemList = cartItemService.listPromotion(memberService.getCurrentMember().getId(), cartIds);
        return Result.success(cartPromotionItemList);
    }

    @ApiOperation("修改购物车中指定商品的数量")
    @RequestMapping(value = "/update/quantity", method = RequestMethod.GET)
    @ResponseBody
    public Result updateQuantity(@RequestParam Long id,
                                       @RequestParam Integer quantity) {
        int count = cartItemService.updateQuantity(id, memberService.getCurrentMember().getId(), quantity);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    @ApiOperation("获取购物车中指定商品的规格,用于重选规格")
    @RequestMapping(value = "/getProduct/{productId}", method = RequestMethod.GET)
    @ResponseBody
    public Result<CartProduct> getCartProduct(@PathVariable Long productId) {
        CartProduct cartProduct = cartItemService.getCartProduct(productId);
        return Result.success(cartProduct);
    }

    @ApiOperation("修改购物车中商品的规格")
    @RequestMapping(value = "/update/attr", method = RequestMethod.POST)
    @ResponseBody
    public Result updateAttr(@RequestBody OmsCartItemPo cartItem) {
        int count = cartItemService.updateAttr(cartItem);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    @ApiOperation("删除购物车中的指定商品")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@RequestParam("ids") List<Long> ids) {
        int count = cartItemService.delete(memberService.getCurrentMember().getId(), ids);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    @ApiOperation("清空当前会员的购物车")
    @RequestMapping(value = "/clear", method = RequestMethod.POST)
    @ResponseBody
    public Result clear() {
        int count = cartItemService.clear(memberService.getCurrentMember().getId());
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }
}
