package com.niu.mall.controller;

import com.niu.mall.service.OmsCompanyAddressService;
import com.niu.mall.common.api.Result;
import com.niu.mall.po.OmsCompanyAddressPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 公司收发货地址表 前端控制器
 *
 * @author lihaojie
 * @date 2023/01/18 12:27
 **/
@RestController
@Api(tags = "OmsCompanyAddressController", description = "收货地址管理")
@RequestMapping("/companyAddress")
public class OmsCompanyAddressController {
    @Autowired
    private OmsCompanyAddressService companyAddressService;

    @ApiOperation("获取所有收货地址")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<OmsCompanyAddressPo>> list() {
        List<OmsCompanyAddressPo> companyAddressList = companyAddressService.list();
        return Result.success(companyAddressList);
    }
}
