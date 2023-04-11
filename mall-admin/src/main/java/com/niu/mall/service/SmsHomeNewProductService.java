package com.niu.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.niu.mall.po.SmsHomeNewProductPo;

import java.util.List;

/**
 * <p>
 * 新鲜好物表 服务类
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
public interface SmsHomeNewProductService extends IService<SmsHomeNewProductPo> {
    /**
     * 批量修改首页新品状态
     *
     * @param ids ids
     * @param recommendStatus 推荐状态
     * @return int
     * @author lihaojie
     * @date 2023/04/10 20:16
     */
    int updateRecommendStatus(List<Long> ids, Integer recommendStatus);
    /**
     * 分页查询首页新品
     *
     * @param productName 商品名
     * @param recommendStatus 推荐状态
     * @param pageSize 分页大小
     * @param pageNum  当前分页
     * @return java.util.List<com.niu.mall.po.SmsHomeNewProductPo>
     * @author lihaojie
     * @date 2023/04/10 20:19
     */
    List<SmsHomeNewProductPo> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum);
}
