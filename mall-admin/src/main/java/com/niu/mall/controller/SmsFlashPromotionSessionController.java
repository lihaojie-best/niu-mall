package com.niu.mall.controller;

import com.niu.mall.common.api.Result;
import com.niu.mall.dto.SmsFlashPromotionSessionDto;
import com.niu.mall.po.SmsFlashPromotionSessionPo;
import com.niu.mall.service.SmsFlashPromotionSessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.JdbcUpdateAffectedIncorrectNumberOfRowsException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 限时购场次表 前端控制器
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@RestController
@RequestMapping("/flashSession")
@Api(tags = "SmsFlashPromotionSessionController", value = "限时购场次管理")
public class SmsFlashPromotionSessionController {
    @Autowired
    private SmsFlashPromotionSessionService service;

    @ApiOperation("/添加场次")
    @PostMapping("/create")
    public Result create(@RequestBody SmsFlashPromotionSessionPo sessionPo) {
        int count = service.create(sessionPo);
        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.failed("添加失败");
        }
    }

    @ApiOperation("修改场次")
    @PostMapping("/update/{id}")
    public Result<Integer> update(@PathVariable Long id, @RequestBody SmsFlashPromotionSessionPo promotionSessionPo) {

        //更新
        int count = service.update(id, promotionSessionPo);
        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.failed("修改失败");
        }
    }

    @ApiOperation("修改启用状态")
    @PostMapping("/update/status/{id}")
    public Result<String> updateStatusById(@PathVariable(value = "id") Long id, @RequestParam Integer status) {
        //
        boolean b = service.updateById(new SmsFlashPromotionSessionPo().setId(id).setStatus(status));
        //判断更新是否成功
        if (b) {
            return Result.success("启动状态修改成功");
        } else {
            return Result.failed("启动状态修改失败");
        }

    }

    @ApiOperation("删除场次")
    @PostMapping("/delete/{id}")
    public Result<String> delete(@PathVariable("id") Long id) {
        //调用删除方法
        boolean b = service.removeById(id);
        // 判断是否删除完成
        if (b) {
            return Result.success("删除成功");
        } else {
            return Result.failed("删除失败");
        }
    }

    @ApiOperation("获取场次详情")
    @GetMapping("/{id}")
    public Result<SmsFlashPromotionSessionPo> getItem(@PathVariable("id") Long id) {
        SmsFlashPromotionSessionPo sessionPo = service.getById(id);
        return Result.success(sessionPo);
    }

    @ApiOperation("获取全部场次")
    @GetMapping("/list")
    public Result<List<SmsFlashPromotionSessionPo>> list() {
        List<SmsFlashPromotionSessionPo> list = service.list();
        return Result.success(list);
    }

    @ApiOperation("获取全部可选场次及其数量")
    @GetMapping("/selectList")
    public Result<List<SmsFlashPromotionSessionDto>> selectList(Long flashPromotionId) {
        List<SmsFlashPromotionSessionDto> list =service.list(flashPromotionId);
        return Result.success(list);
    }
}
