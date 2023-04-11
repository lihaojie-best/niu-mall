package com.niu.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.niu.mall.dto.PmsProductDto;
import com.niu.mall.dto.PmsProductQueryDto;
import com.niu.mall.po.PmsProductPo;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品信息 服务类
 *
 * @author lihaojie
 * @date 2022/12/18 20:40
 **/
public interface PmsProductService extends IService<PmsProductPo> {
    /**
     * 创建商品
     *
     * @param pmsProductDto
     * @return int
     * @author lihaojie
     * @date 2022/11/21 19:06
     */
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    int creat(PmsProductDto pmsProductDto);

    /**
     * 更改商品信息时回显数据
     *
     * @param id
     * @return PmsProductPo
     * @author lihaojie
     * @date 2022/11/21 16:53
     */
    @Transactional
    PmsProductDto getUpdateInfo(Long id);

    /**
     * 更新商品信息
     *
     * @param id
     * @param productDto
     * @return int
     * @author lihaojie
     * @date 2022/11/23 21:08
     */
    @Transactional
    int updateById(Long id, PmsProductDto productDto);

    /**
     * 分页查询商品
     *
     * @param pageSize
     * @param pageNum
     * @param productQueryDto
     * @return com.github.pagehelper.Page<PmsProductPo>
     * @author lihaojie
     * @date 2022/11/24 15:29
     */
    List<PmsProductDto> list(Integer pageSize, Integer pageNum, PmsProductQueryDto productQueryDto);

    /**
     * 批量修改审核状态
     *
     * @param ids
     * @param verifyStatus
     * @param detail
     * @return int
     * @author lihaojie
     * @date 2022/11/24 22:15
     */
    @Transactional
    int updateVerifyStatusBatchIds(List<Long> ids, Integer verifyStatus, String detail);
}
