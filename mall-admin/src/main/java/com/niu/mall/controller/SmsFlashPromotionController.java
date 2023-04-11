package com.niu.mall.controller;

import com.niu.mall.common.api.CommonPage;
import com.niu.mall.common.api.Result;
import com.niu.mall.po.SmsFlashPromotionPo;
import com.niu.mall.service.SmsFlashPromotionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 限时购表 前端控制器
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@RestController
@Api(tags = "SmsFlashPromotionController", value = "限时购表 前端控制器")
@RequestMapping("/flash")
public class SmsFlashPromotionController {
    @Autowired
    private SmsFlashPromotionService service;
    /**
     * 创建活动
     *
     * @param flashPromotionPo  活动表的实体类
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2023/04/08 14:26
     */
    @ApiOperation("创建活动")
    @PostMapping("/create")
    public Result creat(@RequestBody SmsFlashPromotionPo flashPromotionPo) {
        int count = service.insert(flashPromotionPo);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed("创建失败");
    }
    /**
     * 编剧活动表
     *
     * @param id 活动id
     * @param flashPromotionPo  活动实体类
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2023/04/08 14:26
     */
    @ApiOperation("更新活动表")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable Long id, @RequestBody SmsFlashPromotionPo flashPromotionPo) {
        boolean b = service.updateById(flashPromotionPo.setId(id));
        if (b) {
            return Result.success("更新成功");
        }
        return Result.failed("更新失败");
    }
    /**
     * 根据id删除活动
     *
     * @param id  活动id
     * @return com.niu.mall.common.api.Result<java.lang.String>
     * @author lihaojie
     * @date 2023/04/08 14:26
     */
    @ApiOperation("删除活动")
    @PostMapping("/delete/{id}")
    public Result<String> delete(@PathVariable Long id) {
        boolean b = service.removeById(id);
        if (b) {
            return Result.success("删除成功");
        }
        return Result.failed("删除失败");
    }
    /**
     * 修改上下线状态
     *
     * @param id 活动id
     * @param status  修改后的最终状态
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2023/04/08 14:25
     */
    @ApiOperation("修改上下线状态")
    @PostMapping("/update/status/{id}")
    public Result<String> update(@PathVariable Long id, Integer status) {
        int count = service.update(id, status);
        if (count > 0) {
            return Result.success("活动在线状态更新成功");
        }
        return Result.failed("活动在线状态更新失败,请重试");
    }
    /**
     * 获取活动详情
     *
     * @param id  活动id
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2023/04/08 14:24
     */
    @ApiOperation("获取活动详情")
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        //声明
        SmsFlashPromotionPo smsFlashPromotionPo;
        try {
            //查询
            smsFlashPromotionPo = new SmsFlashPromotionPo().selectById(id);
        } catch (Exception e) {
            // 捕获异常 返回
            return Result.failed();
        }
        return Result.success(smsFlashPromotionPo);
    }
    /**
     * 根据关键词分页查询活动
     *
     * @param keyword 关键词
     * @param pageSize 分页大小
     * @param pageNum  当前页
     * @return com.niu.mall.common.api.Result<com.niu.mall.common.api.CommonPage<com.niu.mall.po.SmsFlashPromotionPo>>
     * @author lihaojie
     * @date 2023/04/08 14:23
     */
    @ApiOperation("根据活动名称分页查询")
    @GetMapping("/list")
    public Result<CommonPage<SmsFlashPromotionPo>> getList(@RequestParam(value = "keyword", required = false) String keyword,
                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsFlashPromotionPo> list = null;
        try {
            // 查询list
            list = service.list(keyword, pageSize, pageNum);
        } catch (Exception e) {
            // 将异常信息返回
            return Result.failed(e.getMessage());
        }
        //查询成功返回
        return Result.success(CommonPage.restPage(list));
    }
}
