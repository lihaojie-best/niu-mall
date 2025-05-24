package com.niu.mall.user.service.impl;

import cn.hutool.core.collection.CollUtil;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niu.mall.common.exception.Asserts;
import com.niu.mall.user.dao.*;
import com.niu.mall.user.domain.CartPromotionItem;
import com.niu.mall.user.domain.SmsCouponHistoryDetail;
import com.niu.mall.user.po.*;
import com.niu.mall.user.service.UmsMemberCouponService;
import com.niu.mall.user.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * 会员优惠券管理Service实现类
 * Created by lihaojie on 2023/8/29.
 */
@Service
public class UmsMemberCouponServiceImpl implements UmsMemberCouponService {
    @Autowired
    private UmsMemberService memberService;
    @Autowired
    private SmsCouponDao couponMapper;
    @Autowired
    private SmsCouponHistoryDao couponHistoryMapper;
    @Autowired
    private SmsCouponHistoryDao couponHistoryDao;
    @Autowired
    private SmsCouponProductRelationDao couponProductRelationMapper;
    @Autowired
    private SmsCouponProductCategoryRelationDao couponProductCategoryRelationMapper;
    @Autowired
    private PmsProductDao productMapper;
    @Override
    public void add(Long couponId) {
        UmsMemberPo currentMember = memberService.getCurrentMember();
        //获取优惠券信息，判断数量
        SmsCouponPo coupon = couponMapper.selectById(couponId);
        if(coupon==null){
            Asserts.fail("优惠券不存在");
        }
        if(coupon.getCount()<=0){
            Asserts.fail("优惠券已经领完了");
        }
        Date now = new Date();
        if(now.before(coupon.getEnableTime())){
            Asserts.fail("优惠券还没到领取时间");
        }
        //判断用户领取的优惠券数量是否超过限制
        QueryWrapper<SmsCouponHistoryPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("coupon_id", couponId)
                .eq("member_id", currentMember.getId());

        long count = couponHistoryMapper.selectCount(queryWrapper);

        if(count>=coupon.getPerLimit()){
            Asserts.fail("您已经领取过该优惠券");
        }
        //生成领取优惠券历史
        SmsCouponHistoryPo couponHistory = new SmsCouponHistoryPo();
        couponHistory.setCouponId(couponId);
        couponHistory.setCouponCode(generateCouponCode(currentMember.getId()));
        couponHistory.setCreateTime(now);
        couponHistory.setMemberId(currentMember.getId());
        couponHistory.setMemberNickname(currentMember.getNickname());
        //主动领取
        couponHistory.setGetType(1);
        //未使用
        couponHistory.setUseStatus(0);
        couponHistoryMapper.insert(couponHistory);
        //修改优惠券表的数量、领取数量
        coupon.setCount(coupon.getCount()-1);
        coupon.setReceiveCount(coupon.getReceiveCount()==null?1:coupon.getReceiveCount()+1);
        couponMapper.updateById(coupon);
    }

    /**
     * 16位优惠码生成：时间戳后8位+4位随机数+用户id后4位
     */
    private String generateCouponCode(Long memberId) {
        StringBuilder sb = new StringBuilder();
        Long currentTimeMillis = System.currentTimeMillis();
        String timeMillisStr = currentTimeMillis.toString();
        sb.append(timeMillisStr.substring(timeMillisStr.length() - 8));
        for (int i = 0; i < 4; i++) {
            sb.append(new Random().nextInt(10));
        }
        String memberIdStr = memberId.toString();
        if (memberIdStr.length() <= 4) {
            sb.append(String.format("%04d", memberId));
        } else {
            sb.append(memberIdStr.substring(memberIdStr.length()-4));
        }
        return sb.toString();
    }

    @Override
    public List<SmsCouponHistoryPo> listHistory(Integer useStatus) {
        UmsMemberPo currentMember = memberService.getCurrentMember();
        QueryWrapper<SmsCouponHistoryPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id", currentMember.getId());

        if (useStatus != null) {
            queryWrapper.eq("use_status", useStatus);
        }

        return couponHistoryMapper.selectList(queryWrapper);

    }

    @Override
    public List<SmsCouponHistoryDetail> listCart(List<CartPromotionItem> cartItemList, Integer type) {
        UmsMemberPo currentMember = memberService.getCurrentMember();
        Date now = new Date();
        //获取该用户所有优惠券
        List<SmsCouponHistoryDetail> allList = couponHistoryDao.getDetailList(currentMember.getId());
        //根据优惠券使用类型来判断优惠券是否可用
        List<SmsCouponHistoryDetail> enableList = new ArrayList<>();
        List<SmsCouponHistoryDetail> disableList = new ArrayList<>();
        for (SmsCouponHistoryDetail couponHistoryDetail : allList) {
            Integer useType = couponHistoryDetail.getCoupon().getUseType();
            BigDecimal minPoint = couponHistoryDetail.getCoupon().getMinPoint();
            Date endTime = couponHistoryDetail.getCoupon().getEndTime();
            if(useType.equals(0)){
                //0->全场通用
                //判断是否满足优惠起点
                //计算购物车商品的总价
                BigDecimal totalAmount = calcTotalAmount(cartItemList);
                if(now.before(endTime)&&totalAmount.subtract(minPoint).intValue()>=0){
                    enableList.add(couponHistoryDetail);
                }else{
                    disableList.add(couponHistoryDetail);
                }
            }else if(useType.equals(1)){
                //1->指定分类
                //计算指定分类商品的总价
                List<Long> productCategoryIds = new ArrayList<>();
                for (SmsCouponProductCategoryRelationPo categoryRelation : couponHistoryDetail.getCategoryRelationList()) {
                    productCategoryIds.add(categoryRelation.getProductCategoryId());
                }
                BigDecimal totalAmount = calcTotalAmountByproductCategoryId(cartItemList,productCategoryIds);
                if(now.before(endTime)&&totalAmount.intValue()>0&&totalAmount.subtract(minPoint).intValue()>=0){
                    enableList.add(couponHistoryDetail);
                }else{
                    disableList.add(couponHistoryDetail);
                }
            }else if(useType.equals(2)){
                //2->指定商品
                //计算指定商品的总价
                List<Long> productIds = new ArrayList<>();
                for (SmsCouponProductRelationPo productRelation : couponHistoryDetail.getProductRelationList()) {
                    productIds.add(productRelation.getProductId());
                }
                BigDecimal totalAmount = calcTotalAmountByProductId(cartItemList,productIds);
                if(now.before(endTime)&&totalAmount.intValue()>0&&totalAmount.subtract(minPoint).intValue()>=0){
                    enableList.add(couponHistoryDetail);
                }else{
                    disableList.add(couponHistoryDetail);
                }
            }
        }
        if(type.equals(1)){
            return enableList;
        }else{
            return disableList;
        }
    }

    @Override
    public List<SmsCouponPo> listByProduct(Long productId) {
        List<Long> allCouponIds = new ArrayList<>();
        // 获取指定商品优惠券
        List<SmsCouponProductRelationPo> cprList = couponProductRelationMapper.selectList(
                new QueryWrapper<SmsCouponProductRelationPo>().eq("product_id", productId)
        );
        if (CollUtil.isNotEmpty(cprList)) {
            List<Long> couponIds = cprList.stream()
                    .map(SmsCouponProductRelationPo::getCouponId)
                    .collect(Collectors.toList());
            allCouponIds.addAll(couponIds);
        }

// 获取指定分类优惠券
        PmsProductPo product = productMapper.selectById(productId);
        List<SmsCouponProductCategoryRelationPo> cpcrList = couponProductCategoryRelationMapper.selectList(
                new QueryWrapper<SmsCouponProductCategoryRelationPo>()
                        .eq("product_category_id", product.getProductCategoryId())
        );
        if (CollUtil.isNotEmpty(cpcrList)) {
            List<Long> couponIds = cpcrList.stream()
                    .map(SmsCouponProductCategoryRelationPo::getCouponId)
                    .collect(Collectors.toList());
            allCouponIds.addAll(couponIds);
        }

        if (CollUtil.isEmpty(allCouponIds)) {
            return new ArrayList<>();
        }

// 所有优惠券（通用 + 专属）
        Date now = new Date();
        QueryWrapper<SmsCouponPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .gt(SmsCouponPo::getEndTime, now)
                .lt(SmsCouponPo::getStartTime, now)
                .and(wrapper -> wrapper.eq(SmsCouponPo::getUseType, 0)
                        .or()
                        .ne(SmsCouponPo::getUseType, 0)
                        .in(SmsCouponPo::getId, allCouponIds)
                );

        return couponMapper.selectList(queryWrapper);

    }

    @Override
    public List<SmsCouponPo> list(Integer useStatus) {
        UmsMemberPo member = memberService.getCurrentMember();
        return couponHistoryDao.getCouponList(member.getId(),useStatus);
    }

    private BigDecimal calcTotalAmount(List<CartPromotionItem> cartItemList) {
        BigDecimal total = new BigDecimal("0");
        for (CartPromotionItem item : cartItemList) {
            BigDecimal realPrice = item.getPrice().subtract(item.getReduceAmount());
            total=total.add(realPrice.multiply(new BigDecimal(item.getQuantity())));
        }
        return total;
    }

    private BigDecimal calcTotalAmountByproductCategoryId(List<CartPromotionItem> cartItemList,List<Long> productCategoryIds) {
        BigDecimal total = new BigDecimal("0");
        for (CartPromotionItem item : cartItemList) {
            if(productCategoryIds.contains(item.getProductCategoryId())){
                BigDecimal realPrice = item.getPrice().subtract(item.getReduceAmount());
                total=total.add(realPrice.multiply(new BigDecimal(item.getQuantity())));
            }
        }
        return total;
    }

    private BigDecimal calcTotalAmountByProductId(List<CartPromotionItem> cartItemList,List<Long> productIds) {
        BigDecimal total = new BigDecimal("0");
        for (CartPromotionItem item : cartItemList) {
            if(productIds.contains(item.getProductId())){
                BigDecimal realPrice = item.getPrice().subtract(item.getReduceAmount());
                total=total.add(realPrice.multiply(new BigDecimal(item.getQuantity())));
            }
        }
        return total;
    }

}
