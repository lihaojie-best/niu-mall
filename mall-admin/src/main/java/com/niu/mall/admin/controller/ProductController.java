package com.niu.mall.admin.controller;


import com.niu.mall.admin.dto.ProductParamDto;
import com.niu.mall.admin.model.Product;
import com.niu.mall.admin.service.ProductService;
import com.niu.mall.common.api.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/*
商品管理
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     *  创建商品
     *   形参：ProductParamDto
     */
    @ApiOperation("创建商品控制类")
    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public Result create(@ApiParam("商品参数,内封装ProductAttribute,ProductBasic,productDetails") @RequestBody Product product) {

        return productService.createProduct(product);
    }

    /**
     * 更新商品
     * @Param：id
     * */
    @ApiOperation("更新商品控制类")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public Result update( @RequestBody Product product) {
        return productService.update(product);
    }

    @ApiOperation("查询商品控制类")
    @RequestMapping(value = "/getByProductId/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result getByProductId(@PathVariable long id) {
        return  productService.getByProductId(id);
    }

}
