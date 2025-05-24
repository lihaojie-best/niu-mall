package com.niu.mall.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niu.mall.user.domain.SmsCouponHistoryDetail;
import com.niu.mall.user.po.SmsCouponHistoryPo;
import com.niu.mall.user.po.SmsCouponPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 优惠券使用、领取历史表 Mapper 接口
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Mapper
public interface SmsCouponHistoryDao extends BaseMapper<SmsCouponHistoryPo> {
    /**
     * 获取优惠券历史详情
     */
    List<SmsCouponHistoryDetail> getDetailList(@Param("memberId") Long memberId);

    /**
     * 获取指定会员优惠券列表
     */
    List<SmsCouponPo> getCouponList(@Param("memberId") Long memberId, @Param("useStatus")Integer useStatus);
}
