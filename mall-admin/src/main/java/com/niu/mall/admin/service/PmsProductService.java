package com.niu.mall.admin.service;

import com.github.pagehelper.Page;
import com.niu.mall.admin.dto.PmsProductDto;
import com.niu.mall.admin.dto.PmsProductQueryDto;
import com.niu.mall.common.api.CommonPage;
import com.niu.mall.mbg.po.PmsProductPo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
public interface PmsProductService extends IService<PmsProductPo> {
    /**
     * 创建商品
     *
     * @author lihaojie
     * @date 2022/11/21 19:06
     * @param pmsProductDto
     * @return int
     */
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    int creat(PmsProductDto pmsProductDto);
    /**
     * 更改商品信息时回显数据
     *
     * @author lihaojie
     * @date 2022/11/21 16:53
     * @param id
     * @return com.niu.mall.mbg.po.PmsProductPo
     */
    @Transactional
    PmsProductDto getUpdateInfo(Long id);
    /**
     * 更新商品信息
     *
     * @author lihaojie
     * @date 2022/11/23 21:08
     * @param id
     * @param productDto
     * @return int
     */
    @Transactional
    int updateById(Long id,PmsProductDto productDto);
    /**
     * 分页查询商品
     *
     * @author lihaojie
     * @date 2022/11/24 15:29
     * @param pageSize
     * @param pageNum
     * @param productQueryDto
     * @return com.github.pagehelper.Page<com.niu.mall.mbg.po.PmsProductPo>
     */
    CommonPage<PmsProductDto> list(Integer pageSize, Integer pageNum, PmsProductQueryDto productQueryDto);
    /**
     * 批量修改审核状态
     *
     * @author lihaojie
     * @date 2022/11/24 22:15
     * @param ids
     * @param verifyStatus
     * @param detail
     * @return int
     */
    @Transactional
    int updateVerifyStatusBatchIds(List<Long> ids, Integer verifyStatus, String detail);
}
