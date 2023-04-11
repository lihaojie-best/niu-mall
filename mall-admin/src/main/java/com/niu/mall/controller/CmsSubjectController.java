package com.niu.mall.controller;

import com.niu.mall.service.CmsSubjectService;
import com.niu.mall.common.api.CommonPage;
import com.niu.mall.common.api.Result;
import com.niu.mall.po.CmsSubjectPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 专题表 前端控制器
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@RestController
@Api(tags = "CmsSubjectController", description = "商品专题管理")
@RequestMapping("/subject")
public class CmsSubjectController {
    @Autowired
    private CmsSubjectService subjectService;

    @ApiOperation("获取全部商品专题")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public  Result<List<CmsSubjectPo>> listAll() {
        List<CmsSubjectPo> subjectList = subjectService.list();
        return  Result.success(subjectList);
    }

    @ApiOperation(value = "根据专题名称分页获取商品专题")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public  Result<CommonPage<CmsSubjectPo>> getList(@RequestParam(value = "keyword", required = false) String keyword,
                                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        List<CmsSubjectPo> subjectList = subjectService.list(keyword, pageNum, pageSize);
        return  Result.success(CommonPage.restPage(subjectList));
    }
}
