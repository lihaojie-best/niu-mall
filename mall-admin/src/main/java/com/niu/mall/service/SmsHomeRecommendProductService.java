package com.niu.mall.service;

import com.niu.mall.po.SmsHomeRecommendProductPo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 人气推荐商品表 服务类
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
public interface SmsHomeRecommendProductService extends IService<SmsHomeRecommendProductPo> {
    /**
     * 修改推荐排序
     *
     * @param id id
     * @param sort  排序
     * @return int
     * @author lihaojie
     * @date 2023/04/10 20:31
     */
    int updateSort(Long id, Integer sort);
    /**
     * 分页查询推荐
     *
     * @param productName 商品名
     * @param recommendStatus 推荐状态
     * @param pageSize 分页大小
     * @param pageNum 当前分页数量
     * @return java.util.List<com.niu.mall.po.SmsHomeRecommendProductPo>
     * @author lihaojie
     * @date 2023/04/10 20:48
     */
    List<SmsHomeRecommendProductPo> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum);

}
