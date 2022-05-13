package com.niu.mall.admin.controller;


import com.niu.mall.admin.dto.ProductAttributeParamDto;
import com.niu.mall.admin.model.ProductAttribute;
import com.niu.mall.admin.service.ProductAttributeService;
import com.niu.mall.common.api.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/productAttribute")
public class ProductAttributeController {
    //注入productAttribute服务层
    @Autowired
    private ProductAttributeService productAttributeService;


    @ApiOperation("更新商品属性")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    //更新商品属性
    public Result update(@RequestBody ProductAttributeParamDto productAttribute) {
        //调用服务层方法 通过id更新商品属性  通过传入的ProductAttributeParamDto类获得ProductAttribute类,在service层会调用获取该类id来更新属性
        int count = productAttributeService.updateAttributeById(productAttribute.getProductAttribute());
        //更新成功后count>0
        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.failed();
        }
    }

    @ApiOperation("查询单个商品属性")
    @RequestMapping(value = "/getItem", method = RequestMethod.GET)
    @ResponseBody
    //获取通过id单个商品的属性值
    public Result getAttribute(Long id) {
        //直接调用服务层getItem方法获得商品属性，并将其封装到productAttribute类中
        ProductAttribute productAttribute = productAttributeService.findAttributeById(id);

        return Result.success(productAttribute);
    }

}
