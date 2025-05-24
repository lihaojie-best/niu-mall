package com.niu.mall.user.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.niu.mall.user.dao.OmsCartItemDao;
import com.niu.mall.user.dao.PortalProductDao;
import com.niu.mall.user.domain.CartProduct;
import com.niu.mall.user.domain.CartPromotionItem;
import com.niu.mall.user.po.OmsCartItemPo;
import com.niu.mall.user.po.UmsMemberPo;
import com.niu.mall.user.service.OmsCartItemService;
import com.niu.mall.user.service.OmsPromotionService;
import com.niu.mall.user.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 购物车管理Service实现类
 * Created by lihaojie on 2023/8/2.
 */
@Service
public class OmsCartItemServiceImpl implements OmsCartItemService {
    @Autowired
    private OmsCartItemDao cartItemDao;
    @Autowired
    private PortalProductDao productDao;
    @Autowired
    private OmsPromotionService promotionService;
    @Autowired
    private UmsMemberService memberService;

    /**
     * 向购物车添加商品项
     * 如果购物车中已存在相同商品，则更新商品数量；否则，插入新的商品项
     *
     * @param cartItem 要添加到购物车的商品项，包含商品信息和数量
     * @return 返回影响的行数，表示添加或更新操作的结果
     */
    @Override
    public int add(OmsCartItemPo cartItem) {
        int count;
        // 获取当前登录的会员信息
        UmsMemberPo currentMember = memberService.getCurrentMember();
        // 设置商品项的会员ID
        cartItem.setMemberId(currentMember.getId());
        // 设置商品项的会员昵称
        cartItem.setMemberNickname(currentMember.getNickname());
        // 设置商品项的删除状态为未删除
        cartItem.setDeleteStatus(0);

        // 检查购物车中是否已存在相同商品项
        OmsCartItemPo existCartItem = getCartItem(cartItem);
        if (existCartItem == null) {
            // 如果不存在，设置创建日期并插入新的商品项
            cartItem.setCreateDate(new Date());
            count = cartItemDao.insert(cartItem);
        } else {
            // 如果存在，设置修改日期并更新商品数量
            cartItem.setModifyDate(new Date());
            existCartItem.setQuantity(existCartItem.getQuantity() + cartItem.getQuantity());
            count = cartItemDao.updateById(existCartItem);
        }
        // 返回操作结果
        return count;
    }


    /**
     * 根据会员id,商品id和规格获取购物车中商品
     */
    private OmsCartItemPo getCartItem(OmsCartItemPo cartItem) {
        QueryWrapper<OmsCartItemPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id", cartItem.getMemberId()).eq("product_id", cartItem.getProductId()).eq("delete_status", 0);

        if (!StringUtils.isEmpty(cartItem.getProductSkuId())) {
            queryWrapper.eq("product_sku_id", cartItem.getProductSkuId());
        }

        List<OmsCartItemPo> cartItemList = cartItemDao.selectList(queryWrapper);

        if (!CollectionUtils.isEmpty(cartItemList)) {
            return cartItemList.get(0);
        }
        return null;
    }

    @Override
    public List<OmsCartItemPo> list(Long memberId) {
        QueryWrapper<OmsCartItemPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("delete_status", 0).eq("member_id", memberId);
        return cartItemDao.selectList(queryWrapper);
    }

    @Override
    public List<CartPromotionItem> listPromotion(Long memberId, List<Long> cartIds) {
        List<OmsCartItemPo> cartItemList = list(memberId);
        if (CollUtil.isNotEmpty(cartIds)) {
            cartItemList = cartItemList.stream().filter(item -> cartIds.contains(item.getId())).collect(Collectors.toList());
        }
        List<CartPromotionItem> cartPromotionItemList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(cartItemList)) {
            cartPromotionItemList = promotionService.calcCartPromotion(cartItemList);
        }
        return cartPromotionItemList;
    }

    @Override
    public int updateQuantity(Long id, Long memberId, Integer quantity) {
        OmsCartItemPo cartItem = new OmsCartItemPo();
        cartItem.setQuantity(quantity);
        UpdateWrapper<OmsCartItemPo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("delete_status", 0)
                .eq("id", id)
                .eq("member_id", memberId);

        // 执行更新（只更新 cartItem 中非 null 的字段）
        return cartItemDao.update(cartItem, updateWrapper);

    }

    @Override
    public int delete(Long memberId, List<Long> ids) {
        OmsCartItemPo record = new OmsCartItemPo();
        record.setDeleteStatus(1);
        UpdateWrapper<OmsCartItemPo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", ids)
                .eq("member_id", memberId);

        return cartItemDao.update(record, updateWrapper);

    }

    @Override
    public CartProduct getCartProduct(Long productId) {
        return productDao.getCartProduct(productId);
    }

    @Override
    public int updateAttr(OmsCartItemPo cartItem) {
        //删除原购物车信息
        OmsCartItemPo updateCart = new OmsCartItemPo();
        updateCart.setId(cartItem.getId());
        updateCart.setModifyDate(new Date());
        updateCart.setDeleteStatus(1);
        cartItemDao.updateById(updateCart);
        cartItem.setId(null);
        add(cartItem);
        return 1;
    }

    @Override
    public int clear(Long memberId) {
        OmsCartItemPo record = new OmsCartItemPo();
        record.setDeleteStatus(1);
        UpdateWrapper<OmsCartItemPo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("member_id", memberId);

        return cartItemDao.update(record, updateWrapper);

    }
}
