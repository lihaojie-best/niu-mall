package com.niu.mall.service;

import com.niu.mall.po.OmsOrderSettingPo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 订单设置表 服务类
 *
 * @author lihaojie
 * @date 2023/01/17 16:38
 **/
public interface OmsOrderSettingService extends IService<OmsOrderSettingPo> {
    /**
     * 修改指定订单设置
     *
     * @param id 订单设置表id
     * @param orderSetting 订单设置表
     * @return int
     * @author lihaojie
     * @date 2023/01/17 16:36
     */
    int update(Long id, OmsOrderSettingPo orderSetting);
}
