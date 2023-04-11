package com.niu.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.niu.mall.dao.SmsHomeNewProductDao;
import com.niu.mall.po.SmsHomeNewProductPo;
import com.niu.mall.service.SmsHomeNewProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 新鲜好物表 服务实现类
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Service
public class SmsHomeNewProductServiceImpl extends ServiceImpl<SmsHomeNewProductDao, SmsHomeNewProductPo> implements SmsHomeNewProductService {
    @Autowired
    private SmsHomeNewProductDao homeNewProductDao;
    /**
     * 批量修改首页新品状态
     *
     * @param ids ids
     * @param recommendStatus 推荐状态
     * @return int
     * @author lihaojie
     * @date 2023/04/10 20:16
     */
    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        return homeNewProductDao.update(new SmsHomeNewProductPo().setRecommendStatus(recommendStatus), new QueryWrapper<SmsHomeNewProductPo>().in("id", ids));
    }
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
    @Override
    public List<SmsHomeNewProductPo> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        QueryWrapper<SmsHomeNewProductPo> wrapper = new QueryWrapper<>();
        if (!StringUtil.isNullOrEmpty(productName)) {
            wrapper.and(smsHomeNewProductPoQueryWrapper -> smsHomeNewProductPoQueryWrapper.like("product_name", productName));
        }
        if (recommendStatus != null) {
            wrapper.and(smsHomeNewProductPoQueryWrapper -> smsHomeNewProductPoQueryWrapper.eq("recommend_status", recommendStatus));
        }
        PageHelper.startPage(pageNum, pageSize);
        return homeNewProductDao.selectList(wrapper);
    }
}
