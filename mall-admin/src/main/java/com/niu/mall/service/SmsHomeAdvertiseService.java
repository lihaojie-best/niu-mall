package com.niu.mall.service;

import com.niu.mall.po.SmsHomeAdvertisePo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页轮播广告表 服务类
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
public interface SmsHomeAdvertiseService extends IService<SmsHomeAdvertisePo> {
    /**
     * 分页查询广告
     *
     * @param name     名字
     * @param type     类型
     * @param endTime  结束日期
     * @param pageNum  当前页
     * @param pageSize 页面大小
     * @return java.util.List<com.niu.mall.po.SmsHomeAdvertisePo>
     * @author lihaojie
     * @date 2023/04/09 21:27
     */
    List<SmsHomeAdvertisePo> list(String name, Integer type, String endTime, Integer pageNum, Integer pageSize);

}
