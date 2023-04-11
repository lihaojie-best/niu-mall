package com.niu.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.niu.mall.dao.SmsHomeRecommendProductDao;
import com.niu.mall.po.SmsHomeRecommendProductPo;
import com.niu.mall.service.SmsHomeRecommendProductService;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 人气推荐商品表 服务实现类
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Service
public class SmsHomeRecommendProductServiceImpl extends ServiceImpl<SmsHomeRecommendProductDao, SmsHomeRecommendProductPo> implements SmsHomeRecommendProductService {
    @Autowired
    private SmsHomeRecommendProductDao homeRecommendProductDao;

    /**
     * 修改推荐排序
     *
     * @param id   id
     * @param sort 排序
     * @return int
     * @author lihaojie
     * @date 2023/04/10 20:31
     */
    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeRecommendProductPo smsHomeRecommendProductPo = new SmsHomeRecommendProductPo().setId(id).setSort(sort);
        return homeRecommendProductDao.insert(smsHomeRecommendProductPo);
    }

    /**
     * 分页查询推荐
     *
     * @param productName     商品名
     * @param recommendStatus 推荐状态
     * @param pageSize        分页大小
     * @param pageNum         当前分页数量
     * @return java.util.List<com.niu.mall.po.SmsHomeRecommendProductPo>
     * @author lihaojie
     * @date 2023/04/10 20:48
     */
    @Override
    public List<SmsHomeRecommendProductPo> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        QueryWrapper<SmsHomeRecommendProductPo> wrapper = new QueryWrapper<>();
        if (!StringUtil.isNullOrEmpty(productName)) {
            wrapper.and(smsHomeRecommendProductPoQueryWrapper -> smsHomeRecommendProductPoQueryWrapper.like("product_name", productName));
        }
        if (recommendStatus != null) {
            wrapper.and(smsHomeRecommendProductPoQueryWrapper -> smsHomeRecommendProductPoQueryWrapper.eq("recommend_status", recommendStatus));
        }
        PageHelper.startPage(pageNum, pageSize);
        return homeRecommendProductDao.selectList(wrapper);
    }
}
