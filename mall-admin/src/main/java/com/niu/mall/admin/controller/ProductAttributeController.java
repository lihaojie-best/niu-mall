package com.niu.mall.admin.controller;


import com.niu.mall.admin.dto.ProductAttributeParam;
import com.niu.mall.admin.model.ProductAttribute;
import com.niu.mall.admin.service.ProductAttributeService;
import com.niu.mall.common.api.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/productAttribute")
public class ProductAttributeController {
    @Autowired
    private ProductAttributeService productAttributeService;

    @ApiOperation("更新商品属性")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestBody ProductAttributeParam productAttribute) {
        int count = productAttributeService.updateAttributeById(productAttribute.getProductAttribute());


        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.failed();
        }
    }

    @ApiOperation("查询单个商品属性")
    @RequestMapping(value = "/getItem", method = RequestMethod.GET)
    @ResponseBody
    public Result getAttribute(Long id) {
        ProductAttribute productAttribute = productAttributeService.getItem(id);

        return Result.success(productAttribute);
    }

}
