package com.niu.mall.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niu.mall.common.exception.ApiException;
import com.niu.mall.mbg.po.PmsSkuStockPo;
import com.niu.mall.admin.dao.PmsSkuStockDao;
import com.niu.mall.admin.service.PmsSkuStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * sku的库存 服务实现类
 *
 * @author lihaojie
 * @date 2022/12/21 19:29
 **/
@Service
public class PmsSkuStockServiceImpl extends ServiceImpl<PmsSkuStockDao, PmsSkuStockPo> implements PmsSkuStockService {
    @Autowired
    private PmsSkuStockDao skuStockDao;
    /**
     * 批量更新sku库存信息
     *
     * @param pid 商品id
     * @param skuStockList sku集合
     * @return int
     * @author lihaojie
     * @date 2022/12/21 19:28
     */
    @Override
    public int update(Long pid, List<PmsSkuStockPo> skuStockList) {
        int count = 0;
        try {
            skuStockDao.delete(new QueryWrapper<PmsSkuStockPo>().eq("product_id",pid));
            count = skuStockDao.insertList(skuStockList);
        } catch (Exception e) {
            throw new ApiException(e);
        }
        return count;
    }
}
