package com.niu.mall.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niu.mall.service.PmsSkuStockService;
import com.niu.mall.common.api.Result;
import com.niu.mall.po.PmsSkuStockPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * sku的库存 前端控制器
 *
 * @author lihaojie
 * @date 2022/12/21 16:40
 **/
@RestController
@Api(tags = "PmsSkuStockController", description = "sku商品库存管理")
@RequestMapping("/sku")
public class PmsSkuStockController {
    @Autowired
    private PmsSkuStockService service;

    /**
     * 根据商品ID及sku编码模糊搜索sku库存
     *
     * @param pid     产品id
     * @param keyword sku_code的关键词
     * @return com.niu.mall.common.api.Result<java.util.List < PmsSkuStockPo>>
     * @author lihaojie
     * @date 2022/12/21 17:01
     */
    @ApiOperation("根据商品ID及sku编码模糊搜索sku库存")
    @GetMapping("/{pid}")
    public Result<List<PmsSkuStockPo>> getList(@PathVariable Long pid,
                                               @RequestParam(value = "keyword", required = false) String keyword) {
        QueryWrapper<PmsSkuStockPo> whereWrapper = new QueryWrapper<PmsSkuStockPo>().eq("product_id", pid);
        //如果keyword不为空,则要新增模糊查询条件
        if (!StrUtil.isEmpty(keyword)) {
            whereWrapper.and(wp -> {
                wp.like("sku_code", keyword);
            });
        }
        List<PmsSkuStockPo> pmsSkuStockPoList = service.list(whereWrapper);
        return Result.success(pmsSkuStockPoList);
    }

    /**
     * 批量更新sku库存信息
     *
     * @param pid          商品id
     * @param skuStockList sku集合
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2022/12/21 19:27
     */
    @ApiOperation("批量更新sku库存信息")
    @PostMapping("/update/{pid}")
    public Result update(@PathVariable Long pid, @RequestBody List<PmsSkuStockPo> skuStockList) {
        int count = service.update(pid, skuStockList);
        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.failed();
        }
    }
}
