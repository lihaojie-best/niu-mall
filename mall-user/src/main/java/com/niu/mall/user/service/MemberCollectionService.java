package com.niu.mall.user.service;

import com.niu.mall.user.domain.MemberProductCollection;
import org.springframework.data.domain.Page;

/**
 * 会员商品收藏管理Service
 * Created by lihaojie on 2023/8/2.
 */
public interface MemberCollectionService {
    /**
     * 添加收藏
     */
    int add(MemberProductCollection productCollection);

    /**
     * 删除收藏
     */
    int delete(Long productId);

    /**
     * 分页查询收藏
     */
    Page<MemberProductCollection> list(Integer pageNum, Integer pageSize);

    /**
     * 查看收藏详情
     */
    MemberProductCollection detail(Long productId);

    /**
     * 清空收藏
     */
    void clear();
}
