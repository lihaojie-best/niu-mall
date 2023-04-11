package com.niu.mall.service;

import com.niu.mall.po.SmsCouponHistoryPo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 优惠券使用、领取历史表 服务类
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
public interface SmsCouponHistoryService extends IService<SmsCouponHistoryPo> {

    List<SmsCouponHistoryPo> list(Long couponId, Integer useStatus, String orderSn, Integer pageNum, Integer pageSize);

}
