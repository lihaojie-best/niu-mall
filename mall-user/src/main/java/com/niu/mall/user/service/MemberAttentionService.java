package com.niu.mall.user.service;

import com.niu.mall.user.domain.MemberBrandAttention;
import org.springframework.data.domain.Page;

/**
 * 会员品牌关注管理Service
 * Created by lihaojie on 2023/8/2.
 */
public interface MemberAttentionService {
    /**
     * 添加关注
     */
    int add(MemberBrandAttention memberBrandAttention);

    /**
     * 取消关注
     */
    int delete(Long brandId);

    /**
     * 获取用户关注列表
     */
    Page<MemberBrandAttention> list(Integer pageNum, Integer pageSize);

    /**
     * 获取用户关注详情
     */
    MemberBrandAttention detail(Long brandId);

    /**
     * 清空关注列表
     */
    void clear();
}
