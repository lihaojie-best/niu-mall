package com.niu.mall.admin.controller;


import com.niu.mall.admin.dto.ProductParam;
import com.niu.mall.admin.model.Product;
import com.niu.mall.admin.service.ProductService;
import com.niu.mall.common.api.CommonPage;
import com.niu.mall.common.api.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
商品管理
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation("创建商品")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Result create(@RequestBody ProductParam product) {
        int count = productService.create(product);
        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.failed();
        }
    }

    @ApiOperation("更新商品")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(@PathVariable Long id, @RequestBody ProductParam product) {
        int count = productService.update(id, product);
        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.failed();
        }
    }

    @ApiOperation("查询商品")
    @RequestMapping(value = "/getByProductId", method = RequestMethod.GET)
    @ResponseBody
    public Result<Product> getByProductId() {
        //@PathVariable Long id
      Product product = productService.getByProductId(1L);
        return Result.success(product);
    }

}
