package com.niu.mall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niu.mall.common.api.CommonPage;
import com.niu.mall.common.api.Result;
import com.niu.mall.po.SmsHomeRecommendSubjectPo;
import com.niu.mall.service.SmsHomeRecommendSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.executor.ResultExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * <p>
 * 首页推荐专题表 前端控制器
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@RestController
@RequestMapping("/home/recommendSubject")
@Api(tags = "SmsHomeRecommendSubjectController", value = "首页专题推荐管理")
public class SmsHomeRecommendSubjectController {
    @Autowired
    private SmsHomeRecommendSubjectService service;

    @ApiOperation("添加首页推荐专题")
    @PostMapping(value = "/create")
    public Result creat(@RequestBody List<SmsHomeRecommendSubjectPo> subjectPoList) {
        boolean b = service.saveBatch(subjectPoList);
        if (b) {
            return Result.success("添加成功");
        }else {
            return Result.failed("添加失败");
        }
    }

    @ApiOperation("修改推荐排序")
    @PostMapping(value = "/update/sort/{id}")
    public Result updateSort(@PathVariable("id") Long id, @RequestParam Integer sort) {
        boolean b = service.update(new SmsHomeRecommendSubjectPo().setSort(sort), new QueryWrapper<SmsHomeRecommendSubjectPo>().eq("id", id));
        if (b) {
            return Result.success("修改成功");
        }else {
            return Result.failed("修改失败");
        }
    }
    @ApiOperation("批量删除推荐")
    @PostMapping(value = "/delete")
    public Result delete(@RequestParam("ids") List<Long> ids) {
        boolean b = service.removeBatchByIds(ids);
        if (b) {
            return Result.success("删除成功");
        }else {
            return Result.failed("删除失败");
        }
    }
    @ApiOperation("批量修改推荐状态")
    @PostMapping(value = "/update/recommendStatus")
    public Result updateRecommendStatus(@RequestParam("ids") List<Long> ids, @RequestParam Integer recommendStatus) {
        boolean b = service.update(new SmsHomeRecommendSubjectPo().setRecommendStatus(recommendStatus), new QueryWrapper<SmsHomeRecommendSubjectPo>().in("id", ids));
        if (b) {
            return Result.success("修改成功");
        }else {
            return Result.failed("修改失败");
        }
    }
    @ApiOperation("分页查询推荐")
    @GetMapping(value = "/list")
    public Result<CommonPage<SmsHomeRecommendSubjectPo>> list(@RequestParam(value = "subjectName", required = false) String subjectName,
                                                              @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsHomeRecommendSubjectPo> homeRecommendSubjectList = service.list(subjectName, recommendStatus, pageSize, pageNum);
        return Result.success(CommonPage.restPage(homeRecommendSubjectList));

    }
}
