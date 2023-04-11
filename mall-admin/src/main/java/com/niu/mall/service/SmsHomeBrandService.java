package com.niu.mall.service;

import com.niu.mall.po.SmsHomeBrandPo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页推荐品牌表 服务类
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
public interface SmsHomeBrandService extends IService<SmsHomeBrandPo> {

    /**
     * 修改推荐品牌排序
     *
     * @param id 推荐品牌id
     * @param sort  排序
     * @return int
     * @author lihaojie
     * @date 2023/04/10 13:04
     */
    int updateSort(Long id, Integer sort);
    /**
     * 批量修改推荐品牌状态
     * 
     * @param ids 推荐品牌表id
     * @param recommendStatus  推荐状态
     * @return int 
     * @author lihaojie
     * @date 2023/04/10 13:03
     */
    int updateStatusBatchById(List<Long> ids, Integer recommendStatus);
    /**
     * 分页查询推荐品牌
     *
     * @param brandName 品牌名
     * @param recommendStatus 推荐状态
     * @param pageSize 分页大小
     * @param pageNum  当前页
     * @return java.util.List<com.niu.mall.po.SmsHomeBrandPo>
     * @author lihaojie
     * @date 2023/04/10 16:29
     */
    List<SmsHomeBrandPo> list(String brandName, Integer recommendStatus, Integer pageSize, Integer pageNum);
}
