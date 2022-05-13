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

    /*
    创建商品
        形参：ProductParamDto
     */
    @ApiOperation("创建商品控制类")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Result create(@ApiParam("商品参数,内封装ProductAttribute,ProductBasic,productDetails") @RequestBody ProductParamDto productParamDto) {
        //调用productService的create方法创建商品，并将结果给count
        int count = productService.create(productParamDto);
        //count》0 创建成功，反之失败
        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.failed();
        }
    }

    /*
     * 更新商品 Param：id，
     * */
    @ApiOperation("更新商品控制类")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(@PathVariable Long id, @RequestBody ProductParamDto productParam) {
        int count = productService.update(id, productParam);
        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.failed();
        }
    }

    @ApiOperation("查询商品控制类")
    @RequestMapping(value = "/getByProductId", method = RequestMethod.GET)
    @ResponseBody
    public Result<Product> getByProductId(long id) {
        Product product = productService.getByProductId(id);
        return Result.success(product);
    }

}
