package com.niu.mall.user.service;


import com.niu.mall.user.param.OmsOrderReturnApplyParam;

/**
 * 前台订单退货管理Service
 * Created by lihaojie on 2023/10/17.
 */
public interface OmsPortalOrderReturnApplyService {
    /**
     * 提交申请
     */
    int create(OmsOrderReturnApplyParam returnApply);
}
