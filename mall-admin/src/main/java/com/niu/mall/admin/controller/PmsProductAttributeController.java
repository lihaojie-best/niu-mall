package com.niu.mall.admin.controller;

import com.niu.mall.admin.dto.PmsProductAttrInfoDto;
import com.niu.mall.admin.param.PmsProductAttributeParam;
import com.niu.mall.admin.service.PmsProductAttributeService;
import com.niu.mall.common.api.CommonPage;
import com.niu.mall.common.api.Result;
import com.niu.mall.common.exception.ApiException;
import com.niu.mall.mbg.po.PmsProductAttributePo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品属性管理
 *
 * @author lihaojie
 * @date 2022/12/12 20:56
 **/
@Api(tags = "PmsProductAttributeController", description = "商品属性管理")
@RestController
@RequestMapping("/pmsProductAttribute")
public class PmsProductAttributeController {
    @Autowired(required = false)
    private PmsProductAttributeService attributeService;

    /**
     * 添加商品属性信息
     *
     * @param pmsProductAttributeParam 商品属性参数实体类
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2022/12/12 21:24
     */
    @ApiOperation("添加商品属性信息")
    @PostMapping("/creat")
    public Result creat(@RequestBody PmsProductAttributeParam pmsProductAttributeParam) {
        int count = attributeService.creat(pmsProductAttributeParam);
        //判断业务是否完成
        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.failed();
        }
    }

    /**
     * 批量删除商品属性
     *
     * @param ids 商品属性id集合
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2022/12/14 12:10
     */
    @ApiOperation("批量删除商品属性")
    @PostMapping("/deleteByIds")
    public Result deleteByIds(@RequestParam List<Long> ids) {
        int count = attributeService.delete(ids);
        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.failed();
        }
    }

    /**
     * 修改商品属性信息
     *
     * @param id                    更新目标id
     * @param productAttributeParam 更新参数实体类
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2022/12/18 10:46
     */
    @ApiOperation("修改商品属性信息")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable Long id, @RequestBody PmsProductAttributeParam productAttributeParam) {
        //1.param --转化--> po
        PmsProductAttributePo productAttributePo = new PmsProductAttributePo().setId(id);
        BeanUtils.copyProperties(productAttributeParam, productAttributePo);
        //2.更新
        boolean updateById = attributeService.updateById(productAttributePo);
        //3.判断更新情况
        if (updateById) {
            return Result.success(updateById);
        } else {
            return Result.failed();
        }
    }

    /**
     * 查询单个商品属性
     *
     * @param id 查询id
     * @return com.niu.mall.common.api.Result<com.niu.mall.mbg.po.PmsProductAttributePo>
     * @author lihaojie
     * @date 2022/12/18 12:05
     */
    @ApiOperation("查询单个商品属性")
    @GetMapping("/{id}")
    public Result<PmsProductAttributePo> getById(@PathVariable Long id) {
        //1.声明
        PmsProductAttributePo pmsProductAttributePo;
        try {
            //2.查找赋值
            pmsProductAttributePo = attributeService.getById(id);
            //3.成功就返回
            return Result.success(pmsProductAttributePo);
        } catch (Exception e) {
            //将异常抛出，由全局异常处理器捕获处理
            throw new ApiException(e.getMessage(), e.getCause());
        }
    }

    /**
     * 根据商品分类的id获取商品属性及属性分类
     *
     * @param productCategoryId 商品分类id
     * @return com.niu.mall.common.api.Result<java.util.List < com.niu.mall.admin.dto.PmsProductAttrInfoDto>>
     * @author lihaojie
     * @date 2022/12/18 12:45
     */
    @ApiOperation("根据商品分类的id获取商品属性及属性分类")
    @GetMapping("/attrInfo/{productCategoryId}")
    public Result<List<PmsProductAttrInfoDto>> getAttrInfo(@PathVariable Long productCategoryId) {
        List<PmsProductAttrInfoDto> productAttrInfoDtoList;
        try {
            productAttrInfoDtoList = attributeService.getProductAttrInfo(productCategoryId);
        } catch (Exception e) {
            throw new ApiException(e);
        }
        return Result.success(productAttrInfoDtoList);
    }

    /**
     * 根据分类查询属性列表或参数列表
     *
     * @param cid      商品分类id
     * @param type     0表示属性，1表示参数
     * @param pageSize 分页大小
     * @param pageNum  分页当前页数
     * @return com.niu.mall.common.api.Result<com.niu.mall.common.api.CommonPage < com.niu.mall.mbg.po.PmsProductAttributePo>>
     * @author lihaojie
     * @date 2022/12/18 13:23
     */
    @ApiOperation("根据分类查询属性列表或参数列表")
    @GetMapping("/list/{cid}")
    public Result<CommonPage<PmsProductAttributePo>> getList(@PathVariable Long cid,
                                                             @RequestParam(value = "type") Integer type,
                                                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        List<PmsProductAttributePo> productAttributePoList;
        try {
            productAttributePoList = attributeService.getList(cid, type, pageSize, pageNum);
        } catch (Exception e) {
            throw new ApiException(e.getMessage(), e.getCause());
        }
        return Result.success(CommonPage.restPage(productAttributePoList));
    }
}
