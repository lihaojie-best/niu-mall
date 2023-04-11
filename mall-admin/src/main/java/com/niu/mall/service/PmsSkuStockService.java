package com.niu.mall.service;

import com.niu.mall.po.PmsSkuStockPo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * sku的库存 服务类
 *
 * @author lihaojie
 * @date 2022/12/21 19:29
 **/
public interface PmsSkuStockService extends IService<PmsSkuStockPo> {
    /**
     * 批量更新sku库存信息
     *
     * @param pid 商品id
     * @param skuStockList sku集合
     * @return int
     * @author lihaojie
     * @date 2022/12/21 19:28
     */
    int update(Long pid, List<PmsSkuStockPo> skuStockList);

}
