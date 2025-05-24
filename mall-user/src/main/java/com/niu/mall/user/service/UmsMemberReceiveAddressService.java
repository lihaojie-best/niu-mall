package com.niu.mall.user.service;


import com.niu.mall.user.po.UmsMemberReceiveAddressPo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户地址管理Service
 * Created by lihaojie on 2023/8/28.
 */
public interface UmsMemberReceiveAddressService {
    /**
     * 添加收货地址
     */
    int add(UmsMemberReceiveAddressPo address);

    /**
     * 删除收货地址
     * @param id 地址表的id
     */
    int delete(Long id);

    /**
     * 修改收货地址
     * @param id 地址表的id
     * @param address 修改的收货地址信息
     */
    @Transactional
    int update(Long id, UmsMemberReceiveAddressPo address);

    /**
     * 返回当前用户的收货地址
     */
    List<UmsMemberReceiveAddressPo> list();

    /**
     * 获取地址详情
     * @param id 地址id
     */
    UmsMemberReceiveAddressPo getItem(Long id);
}
