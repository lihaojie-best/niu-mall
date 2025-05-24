package com.niu.mall.user.service.impl;

import com.niu.mall.user.dao.OmsOrderReturnApplyDao;
import com.niu.mall.user.param.OmsOrderReturnApplyParam;
import com.niu.mall.user.po.OmsOrderReturnApplyPo;
import com.niu.mall.user.service.OmsPortalOrderReturnApplyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 订单退货管理Service实现类
 * Created by lihaojie on 2023/10/17.
 */
@Service
public class OmsPortalOrderReturnApplyServiceImpl implements OmsPortalOrderReturnApplyService {
    @Autowired
    private OmsOrderReturnApplyDao returnApplyMapper;
    @Override
    public int create(OmsOrderReturnApplyParam returnApply) {
        OmsOrderReturnApplyPo realApply = new OmsOrderReturnApplyPo();
        BeanUtils.copyProperties(returnApply,realApply);
        realApply.setCreateTime(new Date());
        realApply.setStatus(0);
        return returnApplyMapper.insert(realApply);
    }
}
