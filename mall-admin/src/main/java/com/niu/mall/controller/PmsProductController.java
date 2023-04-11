package com.niu.mall.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niu.mall.dto.PmsProductDto;
import com.niu.mall.dto.PmsProductQueryDto;
import com.niu.mall.service.PmsProductService;
import com.niu.mall.common.api.CommonPage;
import com.niu.mall.common.api.Result;
import com.niu.mall.po.PmsProductPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品信息 前端控制器
 *
 * @author lihaojie
 * @date 2022/12/18 18:34
 **/
@Api(tags = "PmsProductController", description = "商品管理")
@RestController
@RequestMapping("/product")
public class PmsProductController {

    @Autowired
    private PmsProductService productService;

    /**
     * 创建商品
     *
     * @param pmsProductDto
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2022/11/21 19:06
     */
    @ApiOperation("创建商品")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Result creat(@RequestBody PmsProductDto pmsProductDto) {
        int count = productService.creat(pmsProductDto);
        //判断是否创建成功
        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.failed();
        }
    }

    /**
     * 更改商品信息时回显数据
     *
     * @param id
     * @return com.niu.mall.common.api.Result<PmsProductPo>
     * @author lihaojie
     * @date 2022/11/21 16:51
     */
    @ApiOperation("根据商品id获取商品编辑信息")
    @RequestMapping(value = "/updateInfo/{id}", method = RequestMethod.GET)
    public Result<PmsProductDto> getUpdateInfo(@PathVariable Long id) {
        PmsProductDto productDto = productService.getUpdateInfo(id);
        return Result.success(productDto);
    }

    /**
     * 分页查询商品
     *
     * @param productQueryDto
     * @param pageSize
     * @param pageNum
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2022/11/24 15:28
     */
    @ApiOperation("分页查询商品")
    @GetMapping("/list")
    public Result<CommonPage<PmsProductDto>> list(PmsProductQueryDto productQueryDto,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        //调用service
        List<PmsProductDto> list = productService.list(pageSize, pageNum, productQueryDto);
        return Result.success(CommonPage.restPage(list));
    }

    /**
     * 更新商品
     *
     * @param id         id
     * @param productDto 更新实体类
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2022/11/21 17:01
     */
    @ApiOperation("更新商品")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public Result update(@PathVariable Long id, @RequestBody PmsProductDto productDto) {
        //创建条件Wrapper
        int count = productService.updateById(id, productDto);
        //判断更新是否正确
        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.failed();
        }
    }

    /**
     * 根据商品名或货号模糊查询
     *
     * @param keyword 关键字
     * @return com.niu.mall.common.api.Result<java.util.List < PmsProductPo>>
     * @author lihaojie
     * @date 2022/11/24 21:48
     */
    @ApiOperation("根据商品名或货号模糊查询")
    @GetMapping("/simpleList")
    public Result<List<PmsProductPo>> simpleList(String keyword) {
        //调用service
        QueryWrapper whereWrapper = new QueryWrapper<>();

        if (!StrUtil.isEmpty(keyword)) {
            whereWrapper.like("name", keyword);
            whereWrapper.or();
            whereWrapper.like("product_sn", keyword);
        }
        List list = productService.list(whereWrapper);
        return Result.success(list);
    }

    /**
     * 批量修改审核状态
     *
     * @param ids          商品id
     * @param verifyStatus 0->未审核；1->审核通过
     * @param detail       审批记录的详细说明
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2022/11/24 22:13
     */
    @ApiOperation("批量修改审核状态")
    @PostMapping("/update/verifyStatus")
    public Result updateVerifyStatusBatchIds(@RequestParam("ids") List<Long> ids,
                                             @RequestParam("verifyStatus") Integer verifyStatus,
                                             @RequestParam("detail") String detail) {
        int count = productService.updateVerifyStatusBatchIds(ids, verifyStatus, detail);
        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.failed();
        }
    }

    /**
     * 批量上下架商品
     *
     * @param ids           商品id集合
     * @param publishStatus 0->下架；1->上架
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2022/11/26 08:34
     */
    @ApiOperation("批量上下架商品")
    @PostMapping("/update/publishStatus")
    public Result updatePublishStatus(@RequestParam("ids") List<Long> ids,
                                      @RequestParam("publishStatus") Integer publishStatus) {
        //创建更新实体类
        PmsProductPo productPo = new PmsProductPo().setPublishStatus(publishStatus);
        //创建更新查询条件
        QueryWrapper<PmsProductPo> whereWrapper = new QueryWrapper<>();
        whereWrapper.in("id", ids);
        boolean flag = productService.update(productPo, whereWrapper);
        if (flag) {
            return Result.success(flag);
        } else {
            return Result.failed();
        }
    }

    /**
     * 批量修改商品的推荐状态
     *
     * @param ids             商品id
     * @param recommendStatus 0 -> 不推荐 1-> 推荐
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2022/11/26 08:44
     */
    @ApiOperation("批量推荐商品")
    @PostMapping("/update/recommendStatus")
    public Result updateRecommendStatus(@RequestParam("ids") List<Long> ids,
                                        @RequestParam("recommendStatus") Integer recommendStatus) {
        //创建更新实体类
        PmsProductPo productPo = new PmsProductPo().setRecommandStatus(recommendStatus);
        //创建更新查询条件
        QueryWrapper<PmsProductPo> whereWrapper = new QueryWrapper<>();
        whereWrapper.in("id", ids);
        boolean flag = productService.update(productPo, whereWrapper);
        if (flag) {
            return Result.success(flag);
        } else {
            return Result.failed();
        }
    }

    /**
     * 批量更新新品状态
     *
     * @param ids       商品id
     * @param newStatus 0->不是新品；1->新品
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2022/11/26 09:06
     */
    @ApiOperation("批量更新新品状态")
    @PostMapping("/update/newStatus")
    public Result updateNewStatus(@RequestParam("ids") List<Long> ids,
                                  @RequestParam("newStatus") Integer newStatus) {
        //创建更新实体类
        PmsProductPo productPo = new PmsProductPo().setNewStatus(newStatus);
        //创建更新查询条件
        QueryWrapper<PmsProductPo> whereWrapper = new QueryWrapper<>();
        whereWrapper.in("id", ids);
        boolean flag = productService.update(productPo, whereWrapper);
        if (flag) {
            return Result.success(flag);
        } else {
            return Result.failed();
        }
    }

    /**
     * 批量修改删除状态
     *
     * @param ids          商品id
     * @param deleteStatus 0->未删除；1->已删除
     * @return com.niu.mall.common.api.Result
     * @author lihaojie
     * @date 2022/11/26 09:12
     */
    @ApiOperation("批量修改删除状态")
    @PostMapping("/update/deleteStatus")
    public Result updateDeleteStatus(@RequestParam("ids") List<Long> ids,
                                     @RequestParam("deleteStatus") Integer deleteStatus) {
        //创建更新实体类
        PmsProductPo productPo = new PmsProductPo().setDeleteStatus(deleteStatus);
        //创建更新查询条件
        QueryWrapper<PmsProductPo> whereWrapper = new QueryWrapper<>();
        whereWrapper.in("id", ids);
        boolean flag = productService.update(productPo, whereWrapper);
        if (flag) {
            return Result.success(flag);
        } else {
            return Result.failed();
        }
    }

}
