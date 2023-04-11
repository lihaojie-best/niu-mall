package com.niu.mall.controller;

import com.niu.mall.service.CmsPrefrenceAreaService;
import com.niu.mall.common.api.Result;
import com.niu.mall.po.CmsPrefrenceAreaPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 优选专区 前端控制器
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@RestController
@Api(tags = "CmsPrefrenceAreaController", description = "商品优选管理")
@RequestMapping("/prefrenceArea")
public class CmsPrefrenceAreaController {
    @Autowired
    private CmsPrefrenceAreaService prefrenceAreaService;

    @ApiOperation("获取所有商品优选")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<CmsPrefrenceAreaPo>> listAll() {
        List<CmsPrefrenceAreaPo> prefrenceAreaList = prefrenceAreaService.list();
        return  Result.success(prefrenceAreaList);
    }
}
