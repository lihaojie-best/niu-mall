package com.niu.mall.admin.service.impl;

import com.niu.mall.mbg.po.OmsOrderSettingPo;
import com.niu.mall.admin.dao.OmsOrderSettingDao;
import com.niu.mall.admin.service.OmsOrderSettingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单设置表 服务实现类
 *
 * @author lihaojie
 * @date 2023/01/17 16:38
 **/
@Service
public class OmsOrderSettingServiceImpl extends ServiceImpl<OmsOrderSettingDao, OmsOrderSettingPo> implements OmsOrderSettingService {
    @Autowired
    private OmsOrderSettingDao omsOrderSettingDao;
    /**
     * 修改指定订单设置
     *
     * @param id 订单设置表id
     * @param orderSetting 订单设置表
     * @return int
     * @author lihaojie
     * @date 2023/01/17 16:36
     */
    @Override
    public int update(Long id, OmsOrderSettingPo orderSetting) {

        int count = 0;
        try {
            count = omsOrderSettingDao.updateById(orderSetting.setId(id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return count;
    }
}
