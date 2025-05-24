package com.niu.mall.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.niu.mall.user.dao.UmsMemberReceiveAddressDao;
import com.niu.mall.user.po.UmsMemberPo;
import com.niu.mall.user.po.UmsMemberReceiveAddressPo;
import com.niu.mall.user.service.UmsMemberReceiveAddressService;
import com.niu.mall.user.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 用户地址管理Service实现类
 * Created by lihaojie on 2023/8/28.
 */
@Service
public class UmsMemberReceiveAddressServiceImpl implements UmsMemberReceiveAddressService {
    @Autowired
    private UmsMemberService memberService;
    @Autowired
    private UmsMemberReceiveAddressDao addressDao;
    @Override
    public int add(UmsMemberReceiveAddressPo address) {
        UmsMemberPo currentMember = memberService.getCurrentMember();
        address.setMemberId(currentMember.getId());
        return addressDao.insert(address);
    }

    @Override
    public int delete(Long id) {
        UmsMemberPo currentMember = memberService.getCurrentMember();
        QueryWrapper<UmsMemberReceiveAddressPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id", currentMember.getId())
                .eq("id", id);

        return addressDao.delete(queryWrapper);

    }

    @Override
    public int update(Long id, UmsMemberReceiveAddressPo address) {
        address.setId(null);
        UmsMemberPo currentMember = memberService.getCurrentMember();
        // 构造当前要更新的地址的查询条件
        LambdaQueryWrapper<UmsMemberReceiveAddressPo> updateWrapper = new LambdaQueryWrapper<>();
        updateWrapper.eq(UmsMemberReceiveAddressPo::getMemberId, currentMember.getId())
                .eq(UmsMemberReceiveAddressPo::getId, id);

// 如果是设置为默认地址，则将原有默认地址清除
        if (address.getDefaultStatus() == 1) {
            UmsMemberReceiveAddressPo resetDefault = new UmsMemberReceiveAddressPo();
            resetDefault.setDefaultStatus(0);

            LambdaUpdateWrapper<UmsMemberReceiveAddressPo> resetWrapper = new LambdaUpdateWrapper<>();
            resetWrapper.eq(UmsMemberReceiveAddressPo::getMemberId, currentMember.getId())
                    .eq(UmsMemberReceiveAddressPo::getDefaultStatus, 1);

            addressDao.update(resetDefault, resetWrapper);
        }

// 更新当前地址
        return addressDao.update(address, updateWrapper);

    }

    @Override
    public List<UmsMemberReceiveAddressPo> list() {
        UmsMemberPo currentMember = memberService.getCurrentMember();
        LambdaQueryWrapper<UmsMemberReceiveAddressPo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UmsMemberReceiveAddressPo::getMemberId, currentMember.getId());

        return addressDao.selectList(queryWrapper);

    }

    @Override
    public UmsMemberReceiveAddressPo getItem(Long id) {
        UmsMemberPo currentMember = memberService.getCurrentMember();
        LambdaQueryWrapper<UmsMemberReceiveAddressPo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UmsMemberReceiveAddressPo::getMemberId, currentMember.getId())
                .eq(UmsMemberReceiveAddressPo::getId, id);

        List<UmsMemberReceiveAddressPo> addressList = addressDao.selectList(queryWrapper);

        if(!CollectionUtils.isEmpty(addressList)){
            return addressList.get(0);
        }
        return null;
    }
}
