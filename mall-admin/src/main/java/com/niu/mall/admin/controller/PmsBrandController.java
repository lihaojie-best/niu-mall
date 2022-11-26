package com.niu.mall.admin.controller;

import com.niu.mall.admin.service.PmsBrandService;
import com.niu.mall.common.api.Result;
import com.niu.mall.mbg.po.PmsBrandPo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 品牌表 前端控制器
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@RestController
@RequestMapping("/pmsBrandPo")
public class PmsBrandController {
    @Autowired
    private PmsBrandService brandService;

    /**
     * 获取全部品牌列表
     *
     * @author lihaojie
     * @date 2022/11/26 12:48
     * @return com.niu.mall.common.api.Result
     */
    @ApiOperation(value = "获取全部品牌列表")
    @GetMapping(value = "/listAll")
    @ResponseBody
    public Result getList(){
        List<PmsBrandPo> list = brandService.list();
        return Result.success(list);
    }
    @ApiOperation("创建品牌")
    @PostMapping("/create")
    @ResponseBody
    public Result creat(@RequestBody PmsBrandPo brand){
        return null;
    }

}
