package com.niu.mall.user.service;


import com.niu.mall.user.po.UmsMemberPo;

/**
 * 会员信息缓存业务类
 * Created by lihaojie on 2023/3/14.
 */
public interface UmsMemberCacheService {
    /**
     * 删除会员用户缓存
     */
    void delMember(Long memberId);

    /**
     * 获取会员用户缓存
     */
    UmsMemberPo getMember(String username);

    /**
     * 设置会员用户缓存
     */
    void setMember(UmsMemberPo member);

    /**
     * 设置验证码
     */
    void setAuthCode(String telephone, String authCode);

    /**
     * 获取验证码
     */
    String getAuthCode(String telephone);
}
