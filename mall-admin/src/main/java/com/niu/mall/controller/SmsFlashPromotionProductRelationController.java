package com.niu.mall.controller;

import com.niu.mall.common.api.CommonPage;
import com.niu.mall.common.api.Result;
import com.niu.mall.dao.SmsFlashPromotionProductRelationDao;
import com.niu.mall.po.SmsFlashPromotionProductRelationPo;
import com.niu.mall.service.SmsFlashPromotionProductRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Relation;
import java.util.List;

/**
 * <p>
 * 商品限时购与商品关系表 前端控制器
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@RestController
@RequestMapping("/flashProductRelation")
@Api(tags = "SmsFlashPromotionProductRelationController", value = "限时购和商品关系管理")
public class SmsFlashPromotionProductRelationController {

    @Autowired
    private SmsFlashPromotionProductRelationService service;

    @ApiOperation("批量选择商品添加关联")
    @PostMapping("/creat")
    public Result<String> creat(@RequestBody List<SmsFlashPromotionProductRelationPo> relationPoList) {
        boolean b = false;
        try {
            //操作数据库
            b = service.saveBatch(relationPoList);
        } catch (Exception e) {
            // 返回异常
            return Result.failed(e.getMessage());
        }
        // 如果没有异常 判断插入是否成功
        if (b) {
            return Result.success("创建成功");
        }
        return Result.failed("创建失败");
    }

    @ApiOperation("修改关联信息")
    @PostMapping("/update/{id}")
    public Result<String> updateById(@PathVariable(value = "id") Long id, @RequestBody SmsFlashPromotionProductRelationPo relationPo) {
        boolean b = false;
        try {
            //更新
            b = relationPo.setId(id).updateById();
        } catch (Exception e) {
            return Result.failed(e.getMessage());
        }
        if (b) {
            return Result.success("");
        }
        return Result.failed();
    }

    @ApiOperation("删除关联")
    @PostMapping("/delete/{id}")
    public Result<String> delete(@PathVariable(value = "id") Long id) {
        boolean b = false;
        try {
            b = service.removeById(id);
        } catch (Exception e) {
            return Result.failed(e.getMessage());
        }
        if (b) {
            return Result.success("删除成功");
        }
        return Result.failed();
    }

    @ApiOperation("获取管理商品促销信息")
    @GetMapping("/{id}")
    public Result<SmsFlashPromotionProductRelationPo> getItem(@PathVariable(value = "id") Long id) {
        //变量声明
        SmsFlashPromotionProductRelationPo relationPo = null;
        try {
            //查询
            relationPo = service.getById(id);
        } catch (Exception e) {
            return Result.failed(e.getMessage());
        }
        return Result.success(relationPo);
    }

    @ApiOperation("分页查询不同场次关联及商品信息")
    @GetMapping("/list")
    public Result<CommonPage<SmsFlashPromotionProductRelationPo>> list(@RequestParam(value = "flashPromotionId") Long flashPromotionId,
                                                                       @RequestParam(value = "flashPromotionSessionId") Long flashPromotionSessionId,
                                                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsFlashPromotionProductRelationPo> list = null;
        try {
            list = service.list(flashPromotionId, flashPromotionSessionId, pageSize, pageNum);
        } catch (Exception e) {
            return Result.failed(e.getMessage());
        }
        return Result.success(CommonPage.restPage(list));
    }
}



