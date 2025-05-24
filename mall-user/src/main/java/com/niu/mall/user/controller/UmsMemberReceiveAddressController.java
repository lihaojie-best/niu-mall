package com.niu.mall.user.controller;

import com.niu.mall.common.api.Result;
import com.niu.mall.user.po.UmsMemberReceiveAddressPo;
import com.niu.mall.user.service.UmsMemberReceiveAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员收货地址管理Controller
 * Created by lihaojie on 2023/8/28.
 */
@Controller
@Api(tags = "UmsMemberReceiveAddressController", description = "会员收货地址管理")
@RequestMapping("/member/address")
public class UmsMemberReceiveAddressController {
    @Autowired
    private UmsMemberReceiveAddressService memberReceiveAddressService;

    @ApiOperation("添加收货地址")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result add(@RequestBody UmsMemberReceiveAddressPo address) {
        int count = memberReceiveAddressService.add(address);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    @ApiOperation("删除收货地址")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@PathVariable Long id) {
        int count = memberReceiveAddressService.delete(id);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    @ApiOperation("修改收货地址")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Result update(@PathVariable Long id, @RequestBody UmsMemberReceiveAddressPo address) {
        int count = memberReceiveAddressService.update(id, address);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    @ApiOperation("显示所有收货地址")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<UmsMemberReceiveAddressPo>> list() {
        List<UmsMemberReceiveAddressPo> addressList = memberReceiveAddressService.list();
        return Result.success(addressList);
    }

    @ApiOperation("获取收货地址详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result<UmsMemberReceiveAddressPo> getItem(@PathVariable Long id) {
        UmsMemberReceiveAddressPo address = memberReceiveAddressService.getItem(id);
        return Result.success(address);
    }
}
