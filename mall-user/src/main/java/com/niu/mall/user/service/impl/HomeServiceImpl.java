package com.niu.mall.user.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;

import com.niu.mall.user.dao.*;
import com.niu.mall.user.domain.FlashPromotionProduct;
import com.niu.mall.user.domain.HomeContentResult;
import com.niu.mall.user.domain.HomeFlashPromotion;
import com.niu.mall.user.po.*;
import com.niu.mall.user.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * 首页内容管理Service实现类
 * Created by lihaojie on 2023/1/28.
 */
@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    private SmsHomeAdvertiseDao advertiseMapper;
    @Autowired
    private HomeDao homeDao;
    @Autowired
    private SmsFlashPromotionDao flashPromotionDao;
    @Autowired
    private SmsFlashPromotionSessionDao promotionSessionMapper;
    @Autowired
    private PmsProductDao productMapper;
    @Autowired
    private PmsProductCategoryDao productCategoryMapper;
    @Autowired
    private CmsSubjectDao subjectMapper;

    @Override
    public HomeContentResult content() {
        HomeContentResult result = new HomeContentResult();
        //获取首页广告
        result.setAdvertiseList(getHomeAdvertiseList());
        //获取推荐品牌
        result.setBrandList(homeDao.getRecommendBrandList(0, 6));
        //获取秒杀信息
        result.setHomeFlashPromotion(getHomeFlashPromotion());
        //获取新品推荐
        result.setNewProductList(homeDao.getNewProductList(0, 4));
        //获取人气推荐
        result.setHotProductList(homeDao.getHotProductList(0, 4));
        //获取推荐专题
        result.setSubjectList(homeDao.getRecommendSubjectList(0, 4));
        return result;
    }

    @Override
    public List<PmsProductPo> recommendProductList(Integer pageSize, Integer pageNum) {
        // TODO:  暂时默认推荐所有商品
        PageHelper.startPage(pageNum, pageSize);
        QueryWrapper<PmsProductPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("delete_status", 0)
                .eq("publish_status", 1);

        return productMapper.selectList(queryWrapper);

    }

    @Override
    public List<PmsProductCategoryPo> getProductCateList(Long parentId) {
        QueryWrapper<PmsProductCategoryPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("show_status", 1)
                .eq("parent_id", parentId)
                .orderByDesc("sort");
        return productCategoryMapper.selectList(queryWrapper);

    }

    @Override
    public List<CmsSubjectPo> getSubjectList(Long cateId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        QueryWrapper<CmsSubjectPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("show_status", 1);
        if (cateId != null) {
            queryWrapper.eq("category_id", cateId);
        }

        return subjectMapper.selectList(queryWrapper);

    }

    @Override
    public List<PmsProductPo> hotProductList(Integer pageNum, Integer pageSize) {
        int offset = pageSize * (pageNum - 1);
        return homeDao.getHotProductList(offset, pageSize);
    }

    @Override
    public List<PmsProductPo> newProductList(Integer pageNum, Integer pageSize) {
        int offset = pageSize * (pageNum - 1);
        return homeDao.getNewProductList(offset, pageSize);
    }

    private HomeFlashPromotion getHomeFlashPromotion() {
        HomeFlashPromotion homeFlashPromotion = new HomeFlashPromotion();
        //获取当前秒杀活动
        Date now = new Date();
        SmsFlashPromotionPo flashPromotion = getFlashPromotion(now);
        if (flashPromotion != null) {
            //获取当前秒杀场次
            SmsFlashPromotionSessionPo flashPromotionSession = getFlashPromotionSession(now);
            if (flashPromotionSession != null) {
                homeFlashPromotion.setStartTime(flashPromotionSession.getStartTime());
                homeFlashPromotion.setEndTime(flashPromotionSession.getEndTime());
                //获取下一个秒杀场次
                SmsFlashPromotionSessionPo nextSession = getNextFlashPromotionSession(homeFlashPromotion.getStartTime());
                if (nextSession != null) {
                    homeFlashPromotion.setNextStartTime(nextSession.getStartTime());
                    homeFlashPromotion.setNextEndTime(nextSession.getEndTime());
                }
                //获取秒杀商品
                List<FlashPromotionProduct> flashProductList = homeDao.getFlashProductList(flashPromotion.getId(), flashPromotionSession.getId());
                homeFlashPromotion.setProductList(flashProductList);
            }
        }
        return homeFlashPromotion;
    }

    //获取下一个场次信息
    private SmsFlashPromotionSessionPo getNextFlashPromotionSession(Date date) {
        QueryWrapper<SmsFlashPromotionSessionPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("start_time", date)
                .orderByAsc("start_time");

        List<SmsFlashPromotionSessionPo> promotionSessionList = promotionSessionMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(promotionSessionList)) {
            return promotionSessionList.get(0);
        }
        return null;
    }

    // 获取轮播图
    private List<SmsHomeAdvertisePo> getHomeAdvertiseList() {
        QueryWrapper<SmsHomeAdvertisePo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", 1)
                .eq("status", 1)
                .orderByDesc("sort");
        return advertiseMapper.selectList(queryWrapper);
    }

    //根据时间获取秒杀活动
    private SmsFlashPromotionPo getFlashPromotion(Date date) {
        Date currDate = DateUtil.beginOfDay(date);
        QueryWrapper<SmsFlashPromotionPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1)
                .le("start_date", currDate)
                .ge("end_date", currDate);

        List<SmsFlashPromotionPo> flashPromotionList = flashPromotionDao.selectList(queryWrapper);

        if (!CollectionUtils.isEmpty(flashPromotionList)) {
            return flashPromotionList.get(0);
        }
        return null;
    }

    //根据时间获取秒杀场次
    private SmsFlashPromotionSessionPo getFlashPromotionSession(Date date) {
        //Date currTime = DateUtil.getTime(date);
        Date currTime = date;
        QueryWrapper<SmsFlashPromotionSessionPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.le("start_time", currTime)
                .ge("end_time", currTime);

        List<SmsFlashPromotionSessionPo> promotionSessionList = promotionSessionMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(promotionSessionList)) {
            return promotionSessionList.get(0);
        }
        return null;
    }
}
