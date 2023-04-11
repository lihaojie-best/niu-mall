package com.niu.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.niu.mall.dao.SmsCouponHistoryDao;
import com.niu.mall.po.SmsCouponHistoryPo;
import com.niu.mall.service.SmsCouponHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 优惠券使用、领取历史表 服务实现类
 * </p>
 * 哪里有问题？？？？？？
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Service
public class SmsCouponHistoryServiceImpl extends ServiceImpl<SmsCouponHistoryDao, SmsCouponHistoryPo> implements SmsCouponHistoryService {

    @Autowired
    private SmsCouponHistoryDao historyDao;

    @Override
    public List<SmsCouponHistoryPo> list(Long couponId, Integer useStatus, String orderSn, Integer pageNum, Integer pageSize) {
        //创建一个查询模板类
        QueryWrapper<SmsCouponHistoryPo> wrapper = new QueryWrapper<>();
        // 优惠劵id
        if (couponId != null) {
            wrapper.and(smsCouponHistoryPoQueryWrapper -> smsCouponHistoryPoQueryWrapper.eq("coupon_id", couponId));
        }
        // 使用状态
        if (useStatus != null) {
            wrapper.and(smsCouponHistoryPoQueryWrapper -> smsCouponHistoryPoQueryWrapper.eq("use_status", useStatus));
        }
        // 订单号
        if (!orderSn.isEmpty()) {
            wrapper.and(smsCouponHistoryPoQueryWrapper -> smsCouponHistoryPoQueryWrapper.like("order_sn", orderSn));
        }
        // 开启分页助手
        PageHelper.startPage(pageNum, pageSize);
        List<SmsCouponHistoryPo> couponHistoryPoList = null;

        try {
            // 获取list
            couponHistoryPoList = historyDao.selectList(wrapper);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return couponHistoryPoList;
    }
}
