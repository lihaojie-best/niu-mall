package com.niu.mall.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.niu.mall.common.api.CommonPage;
import com.niu.mall.common.exception.Asserts;
import com.niu.mall.common.service.RedisService;
import com.niu.mall.user.component.CancelOrderSender;
import com.niu.mall.user.dao.*;
import com.niu.mall.user.domain.*;
import com.niu.mall.user.po.*;
import com.niu.mall.user.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 前台订单管理Service
 * Created by lihaojie on 2023/8/30.
 */
@Service
public class OmsPortalOrderServiceImpl implements OmsPortalOrderService {
    @Autowired
    private UmsMemberService memberService;
    @Autowired
    private OmsCartItemService cartItemService;
    @Autowired
    private UmsMemberReceiveAddressService memberReceiveAddressService;
    @Autowired
    private UmsMemberCouponService memberCouponService;
    @Autowired
    private UmsIntegrationConsumeSettingDao integrationConsumeSettingDao;
    @Autowired
    private PmsSkuStockDao skuStockDao;
    @Autowired
    private SmsCouponHistoryDao couponHistoryDao;
    @Autowired
    private OmsOrderDao orderDao;
    @Autowired
    private SmsCouponHistoryDao couponHistoryMapper;
    @Autowired
    private RedisService redisService;
    @Value("${redis.key.orderId}")
    private String REDIS_KEY_ORDER_ID;
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Autowired
    private PortalOrderDao portalOrderDao;
    @Autowired
    private OmsOrderSettingDao orderSettingDao;
    @Autowired
    private OmsOrderItemDao orderItemDao;
    @Autowired
    private CancelOrderSender cancelOrderSender;

    @Override
    public ConfirmOrderResult generateConfirmOrder(List<Long> cartIds) {
        ConfirmOrderResult result = new ConfirmOrderResult();
        //获取购物车信息
        UmsMemberPo currentMember = memberService.getCurrentMember();
        List<CartPromotionItem> cartPromotionItemList = cartItemService.listPromotion(currentMember.getId(),cartIds);
        result.setCartPromotionItemList(cartPromotionItemList);
        //获取用户收货地址列表
        List<UmsMemberReceiveAddressPo> memberReceiveAddressList = memberReceiveAddressService.list();
        result.setMemberReceiveAddressList(memberReceiveAddressList);
        //获取用户可用优惠券列表
        List<SmsCouponHistoryDetail> couponHistoryDetailList = memberCouponService.listCart(cartPromotionItemList, 1);
        result.setCouponHistoryDetailList(couponHistoryDetailList);
        //获取用户积分
        result.setMemberIntegration(currentMember.getIntegration());
        //获取积分使用规则
        UmsIntegrationConsumeSettingPo integrationConsumeSetting = integrationConsumeSettingDao.selectById(1L);
        result.setIntegrationConsumeSetting(integrationConsumeSetting);
        //计算总金额、活动优惠、应付金额
        ConfirmOrderResult.CalcAmount calcAmount = calcCartAmount(cartPromotionItemList);
        result.setCalcAmount(calcAmount);
        return result;
    }

    /**
     * 生成订单
     *
     * 该方法主要用于生成订单信息，包括订单详情和订单项信息它从购物车中获取商品和优惠信息，
     * 处理优惠券和积分的使用，计算订单总金额，并最终将订单信息插入数据库中
     *
     * @param orderParam 订单参数，包含购物车ID、优惠券ID、支付方式等信息
     * @return 返回一个包含订单信息和订单项信息的Map对象
     */
    @Override
    public Map<String, Object> generateOrder(OrderParam orderParam) {
        // 初始化订单项列表
        List<OmsOrderItemPo> orderItemList = new ArrayList<>();

        // 获取当前会员信息和购物车中的商品及优惠信息
        UmsMemberPo currentMember = memberService.getCurrentMember();
        List<CartPromotionItem> cartPromotionItemList = cartItemService.listPromotion(currentMember.getId(), orderParam.getCartIds());

        // 遍历购物车中的商品，生成订单项信息
        for (CartPromotionItem cartPromotionItem : cartPromotionItemList) {
            OmsOrderItemPo orderItem = new OmsOrderItemPo();
            // 设置订单项的基本信息，如产品ID、名称、图片等
            orderItem.setProductId(cartPromotionItem.getProductId());
            orderItem.setProductName(cartPromotionItem.getProductName());
            orderItem.setProductPic(cartPromotionItem.getProductPic());
            orderItem.setProductAttr(cartPromotionItem.getProductAttr());
            orderItem.setProductBrand(cartPromotionItem.getProductBrand());
            orderItem.setProductSn(cartPromotionItem.getProductSn());
            orderItem.setProductPrice(cartPromotionItem.getPrice());
            orderItem.setProductQuantity(cartPromotionItem.getQuantity());
            orderItem.setProductSkuId(cartPromotionItem.getProductSkuId());
            orderItem.setProductSkuCode(cartPromotionItem.getProductSkuCode());
            orderItem.setProductCategoryId(cartPromotionItem.getProductCategoryId());

            // 设置订单项的优惠信息
            orderItem.setPromotionAmount(cartPromotionItem.getReduceAmount());
            orderItem.setPromotionName(cartPromotionItem.getPromotionMessage());
            orderItem.setGiftIntegration(cartPromotionItem.getIntegration());
            orderItem.setGiftGrowth(cartPromotionItem.getGrowth());

            // 将订单项添加到列表中
            orderItemList.add(orderItem);
        }

        // 检查购物车中的商品是否有足够的库存
        if (!hasStock(cartPromotionItemList)) {
            Asserts.fail("库存不足，无法下单");
        }

        // 处理优惠券的使用
        if (orderParam.getCouponId() == null) {
            // 如果未使用优惠券，将每个订单项的优惠券金额设置为0
            for (OmsOrderItemPo orderItem : orderItemList) {
                orderItem.setCouponAmount(new BigDecimal(0));
            }
        } else {
            // 如果使用了优惠券，获取优惠券信息并处理优惠券金额
            SmsCouponHistoryDetail couponHistoryDetail = getUseCoupon(cartPromotionItemList, orderParam.getCouponId());
            if (couponHistoryDetail == null) {
                Asserts.fail("该优惠券不可用");
            }
            handleCouponAmount(orderItemList, couponHistoryDetail);
        }

        // 处理积分的使用
        if (orderParam.getUseIntegration() == null || orderParam.getUseIntegration().equals(0)) {
            // 如果未使用积分，将每个订单项的积分金额设置为0
            for (OmsOrderItemPo orderItem : orderItemList) {
                orderItem.setIntegrationAmount(new BigDecimal(0));
            }
        } else {
            // 如果使用了积分，计算积分金额并分摊到每个订单项中
            BigDecimal totalAmount = calcTotalAmount(orderItemList);
            BigDecimal integrationAmount = getUseIntegrationAmount(orderParam.getUseIntegration(), totalAmount, currentMember, orderParam.getCouponId() != null);
            if (integrationAmount.compareTo(new BigDecimal(0)) == 0) {
                Asserts.fail("积分不可用");
            } else {
                for (OmsOrderItemPo orderItem : orderItemList) {
                    BigDecimal perAmount = orderItem.getProductPrice().divide(totalAmount, 3, RoundingMode.HALF_EVEN).multiply(integrationAmount);
                    orderItem.setIntegrationAmount(perAmount);
                }
            }
        }

        // 计算每个订单项的实际支付金额
        handleRealAmount(orderItemList);

        // 锁定商品库存
        lockStock(cartPromotionItemList);

        // 创建订单对象并计算订单总金额
        OmsOrderPo order = new OmsOrderPo();
        order.setDiscountAmount(new BigDecimal(0));
        order.setTotalAmount(calcTotalAmount(orderItemList));
        order.setFreightAmount(new BigDecimal(0));
        order.setPromotionAmount(calcPromotionAmount(orderItemList));
        order.setPromotionInfo(getOrderPromotionInfo(orderItemList));

        // 处理订单的优惠券信息
        if (orderParam.getCouponId() == null) {
            order.setCouponAmount(new BigDecimal(0));
        } else {
            order.setCouponId(orderParam.getCouponId());
            order.setCouponAmount(calcCouponAmount(orderItemList));
        }

        // 处理订单的积分信息
        if (orderParam.getUseIntegration() == null) {
            order.setIntegration(0);
            order.setIntegrationAmount(new BigDecimal(0));
        } else {
            order.setIntegration(orderParam.getUseIntegration());
            order.setIntegrationAmount(calcIntegrationAmount(orderItemList));
        }

        // 计算订单的应付金额
        order.setPayAmount(calcPayAmount(order));

        // 设置订单的会员信息
        order.setMemberId(currentMember.getId());
        order.setCreateTime(new Date());
        order.setMemberUsername(currentMember.getUsername());

        // 设置订单的支付方式、来源、状态等信息
        order.setPayType(orderParam.getPayType());
        order.setSourceType(1);
        order.setStatus(0);
        order.setOrderType(0);

        // 设置订单的收货人信息
        UmsMemberReceiveAddressPo address = memberReceiveAddressService.getItem(orderParam.getMemberReceiveAddressId());
        order.setReceiverName(address.getName());
        order.setReceiverPhone(address.getPhoneNumber());
        order.setReceiverPostCode(address.getPostCode());
        order.setReceiverProvince(address.getProvince());
        order.setReceiverCity(address.getCity());
        order.setReceiverRegion(address.getRegion());
        order.setReceiverDetailAddress(address.getDetailAddress());

        // 设置订单的确认状态和删除状态
        order.setConfirmStatus(0);
        order.setDeleteStatus(0);

        // 计算订单的赠送积分和成长值
        order.setIntegration(calcGifIntegration(orderItemList));
        order.setGrowth(calcGiftGrowth(orderItemList));

        // 生成订单号
        order.setOrderSn(generateOrderSn(order));

        // 设置订单的自动收货天数
        List<OmsOrderSettingPo> orderSettings = orderSettingDao.selectList(null);
        if(CollUtil.isNotEmpty(orderSettings)){
            order.setAutoConfirmDay(orderSettings.get(0).getConfirmOvertime());
        }

        // TODO: 处理订单的发票和配送信息

        // 将订单信息和订单项信息插入数据库
        orderDao.insert(order);
        for (OmsOrderItemPo orderItem : orderItemList) {
            orderItem.setOrderId(order.getId());
            orderItem.setOrderSn(order.getOrderSn());
        }
        orderItemDao.insertList(orderItemList);

        // 更新优惠券的使用状态
        if (orderParam.getCouponId() != null) {
            updateCouponStatus(orderParam.getCouponId(), currentMember.getId(), 1);
        }

        // 扣除会员的积分
        if (orderParam.getUseIntegration() != null) {
            order.setUseIntegration(orderParam.getUseIntegration());
            memberService.updateIntegration(currentMember.getId(), currentMember.getIntegration() - orderParam.getUseIntegration());
        }

        // 删除购物车中的已下单商品
        deleteCartItemList(cartPromotionItemList, currentMember);

        // 发送延迟消息，用于取消未支付的订单
        sendDelayMessageCancelOrder(order.getId());

        // 将订单信息和订单项信息封装到结果Map中并返回
        Map<String, Object> result = new HashMap<>();
        result.put("order", order);
        result.put("orderItemList", orderItemList);
        return result;
    }


    @Override
    public Integer paySuccess(Long orderId, Integer payType) {
        //修改订单支付状态
        OmsOrderPo order = new OmsOrderPo();
        order.setId(orderId);
        order.setStatus(1);
        order.setPaymentTime(new Date());
        order.setPayType(payType);
        orderDao.updateById(order);
        //恢复所有下单商品的锁定库存，扣减真实库存
        OmsOrderDetail orderDetail = portalOrderDao.getDetail(orderId);
        int count = portalOrderDao.updateSkuStock(orderDetail.getOrderItemList());
        return count;
    }

    @Override
    public Integer cancelTimeOutOrder() {
        Integer count=0;
        OmsOrderSettingPo orderSetting = orderSettingDao.selectById(1L);
        //查询超时、未支付的订单及订单详情
        List<OmsOrderDetail> timeOutOrders = portalOrderDao.getTimeOutOrders(orderSetting.getNormalOrderOvertime());
        if (CollectionUtils.isEmpty(timeOutOrders)) {
            return count;
        }
        //修改订单状态为交易取消
        List<Long> ids = new ArrayList<>();
        for (OmsOrderDetail timeOutOrder : timeOutOrders) {
            ids.add(timeOutOrder.getId());
        }
        portalOrderDao.updateOrderStatus(ids, 4);
        for (OmsOrderDetail timeOutOrder : timeOutOrders) {
            //解除订单商品库存锁定
            portalOrderDao.releaseSkuStockLock(timeOutOrder.getOrderItemList());
            //修改优惠券使用状态
            updateCouponStatus(timeOutOrder.getCouponId(), timeOutOrder.getMemberId(), 0);
            //返还使用积分
            if (timeOutOrder.getUseIntegration() != null) {
                UmsMemberPo member = memberService.getById(timeOutOrder.getMemberId());
                memberService.updateIntegration(timeOutOrder.getMemberId(), member.getIntegration() + timeOutOrder.getUseIntegration());
            }
        }
        return timeOutOrders.size();
    }

    @Override
    public void cancelOrder(Long orderId) {
        //查询未付款的取消订单
        QueryWrapper<OmsOrderPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", orderId)
                .eq("status", 0)
                .eq("delete_status", 0);

        List<OmsOrderPo> cancelOrderList = orderDao.selectList(queryWrapper);

        if (CollectionUtils.isEmpty(cancelOrderList)) {
            return;
        }
        OmsOrderPo cancelOrder = cancelOrderList.get(0);
        if (cancelOrder != null) {
            //修改订单状态为取消
            cancelOrder.setStatus(4);
            QueryWrapper<OmsOrderItemPo> orderItemQueryWrapper = new QueryWrapper<>();
            orderItemQueryWrapper.eq("order_id", orderId);

            List<OmsOrderItemPo> orderItemList = orderItemDao.selectList(orderItemQueryWrapper);

            //解除订单商品库存锁定
            if (!CollectionUtils.isEmpty(orderItemList)) {
                portalOrderDao.releaseSkuStockLock(orderItemList);
            }
            //修改优惠券使用状态
            updateCouponStatus(cancelOrder.getCouponId(), cancelOrder.getMemberId(), 0);
            //返还使用积分
            if (cancelOrder.getUseIntegration() != null) {
                UmsMemberPo member = memberService.getById(cancelOrder.getMemberId());
                memberService.updateIntegration(cancelOrder.getMemberId(), member.getIntegration() + cancelOrder.getUseIntegration());
            }
        }
    }

    @Override
    public void sendDelayMessageCancelOrder(Long orderId) {
        //获取订单超时时间
        OmsOrderSettingPo orderSetting = orderSettingDao.selectById(1L);
        long delayTimes = orderSetting.getNormalOrderOvertime() * 60 * 1000;
        //发送延迟消息
        cancelOrderSender.sendMessage(orderId, delayTimes);
    }

    @Override
    public void confirmReceiveOrder(Long orderId) {
        UmsMemberPo member = memberService.getCurrentMember();
        OmsOrderPo order = orderDao.selectById(orderId);
        if(!member.getId().equals(order.getMemberId())){
            Asserts.fail("不能确认他人订单！");
        }
        if(order.getStatus()!=2){
            Asserts.fail("该订单还未发货！");
        }
        order.setStatus(3);
        order.setConfirmStatus(1);
        order.setReceiveTime(new Date());
        orderDao.updateById(order);
    }

    @Override
    public CommonPage<OmsOrderDetail> list(Integer status, Integer pageNum, Integer pageSize) {
        if(status==-1){
            status = null;
        }
        UmsMemberPo member = memberService.getCurrentMember();
        PageHelper.startPage(pageNum,pageSize);
        LambdaQueryWrapper<OmsOrderPo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OmsOrderPo::getDeleteStatus, 0)
                .eq(OmsOrderPo::getMemberId, member.getId())
                .orderByDesc(OmsOrderPo::getCreateTime);

        if (status != null) {
            queryWrapper.eq(OmsOrderPo::getStatus, status);
        }

        List<OmsOrderPo> orderList = orderDao.selectList(queryWrapper);

        CommonPage<OmsOrderPo> orderPage = CommonPage.restPage(orderList);
        //设置分页信息
        CommonPage<OmsOrderDetail> resultPage = new CommonPage<>();
        resultPage.setPageNum(orderPage.getPageNum());
        resultPage.setPageSize(orderPage.getPageSize());
        resultPage.setTotal(orderPage.getTotal());
        resultPage.setTotalPage(orderPage.getTotalPage());
        if(CollUtil.isEmpty(orderList)){
            return resultPage;
        }
        //设置数据信息
        List<Long> orderIds = orderList.stream().map(OmsOrderPo::getId).collect(Collectors.toList());
        LambdaQueryWrapper<OmsOrderItemPo> orderItemWrapper = new LambdaQueryWrapper<>();
        orderItemWrapper.in(OmsOrderItemPo::getOrderId, orderIds);

        List<OmsOrderItemPo> orderItemList = orderItemDao.selectList(orderItemWrapper);

        List<OmsOrderDetail> orderDetailList = new ArrayList<>();
        for (OmsOrderPo omsOrder : orderList) {
            OmsOrderDetail orderDetail = new OmsOrderDetail();
            BeanUtil.copyProperties(omsOrder,orderDetail);
            List<OmsOrderItemPo> relatedItemList = orderItemList.stream().filter(item -> item.getOrderId().equals(orderDetail.getId())).collect(Collectors.toList());
            orderDetail.setOrderItemList(relatedItemList);
            orderDetailList.add(orderDetail);
        }
        resultPage.setList(orderDetailList);
        return resultPage;
    }

    @Override
    public OmsOrderDetail detail(Long orderId) {
        OmsOrderPo omsOrder = orderDao.selectById(orderId);
        LambdaQueryWrapper<OmsOrderItemPo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OmsOrderItemPo::getOrderId, orderId);

        List<OmsOrderItemPo> orderItemList = orderItemDao.selectList(queryWrapper);

        OmsOrderDetail orderDetail = new OmsOrderDetail();
        BeanUtil.copyProperties(omsOrder,orderDetail);
        orderDetail.setOrderItemList(orderItemList);
        return orderDetail;
    }

    @Override
    public void deleteOrder(Long orderId) {
        UmsMemberPo member = memberService.getCurrentMember();
        OmsOrderPo order = orderDao.selectById(orderId);
        if(!member.getId().equals(order.getMemberId())){
            Asserts.fail("不能删除他人订单！");
        }
        if(order.getStatus()==3||order.getStatus()==4){
            order.setDeleteStatus(1);
            orderDao.updateById(order);
        }else{
            Asserts.fail("只能删除已完成或已关闭的订单！");
        }
    }

    /**
     * 生成18位订单编号:8位日期+2位平台号码+2位支付方式+6位以上自增id
     */
    private String generateOrderSn(OmsOrderPo order) {
        StringBuilder sb = new StringBuilder();
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String key = REDIS_DATABASE+":"+ REDIS_KEY_ORDER_ID + date;
        Long increment = redisService.incr(key, 1);
        sb.append(date);
        sb.append(String.format("%02d", order.getSourceType()));
        sb.append(String.format("%02d", order.getPayType()));
        String incrementStr = increment.toString();
        if (incrementStr.length() <= 6) {
            sb.append(String.format("%06d", increment));
        } else {
            sb.append(incrementStr);
        }
        return sb.toString();
    }

    /**
     * 删除下单商品的购物车信息
     */
    private void deleteCartItemList(List<CartPromotionItem> cartPromotionItemList, UmsMemberPo currentMember) {
        List<Long> ids = new ArrayList<>();
        for (CartPromotionItem cartPromotionItem : cartPromotionItemList) {
            ids.add(cartPromotionItem.getId());
        }
        cartItemService.delete(currentMember.getId(), ids);
    }

    /**
     * 计算该订单赠送的成长值
     */
    private Integer calcGiftGrowth(List<OmsOrderItemPo> orderItemList) {
        Integer sum = 0;
        for (OmsOrderItemPo orderItem : orderItemList) {
            sum = sum + orderItem.getGiftGrowth() * orderItem.getProductQuantity();
        }
        return sum;
    }

    /**
     * 计算该订单赠送的积分
     */
    private Integer calcGifIntegration(List<OmsOrderItemPo> orderItemList) {
        int sum = 0;
        for (OmsOrderItemPo orderItem : orderItemList) {
            sum += orderItem.getGiftIntegration() * orderItem.getProductQuantity();
        }
        return sum;
    }

    /**
     * 将优惠券信息更改为指定状态
     *
     * @param couponId  优惠券id
     * @param memberId  会员id
     * @param useStatus 0->未使用；1->已使用
     */
    private void updateCouponStatus(Long couponId, Long memberId, Integer useStatus) {
        if (couponId == null) return;
        //查询第一张优惠券
        LambdaQueryWrapper<SmsCouponHistoryPo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SmsCouponHistoryPo::getMemberId, memberId)
                .eq(SmsCouponHistoryPo::getCouponId, couponId)
                .eq(SmsCouponHistoryPo::getUseStatus, useStatus == 0 ? 1 : 0);

        List<SmsCouponHistoryPo> couponHistoryList = couponHistoryMapper.selectList(queryWrapper);

        if (!CollectionUtils.isEmpty(couponHistoryList)) {
            SmsCouponHistoryPo couponHistory = couponHistoryList.get(0);
            couponHistory.setUseTime(new Date());
            couponHistory.setUseStatus(useStatus);
            couponHistoryMapper.updateById(couponHistory);
        }
    }

    private void handleRealAmount(List<OmsOrderItemPo> orderItemList) {
        for (OmsOrderItemPo orderItem : orderItemList) {
            //原价-促销优惠-优惠券抵扣-积分抵扣
            BigDecimal realAmount = orderItem.getProductPrice()
                    .subtract(orderItem.getPromotionAmount())
                    .subtract(orderItem.getCouponAmount())
                    .subtract(orderItem.getIntegrationAmount());
            orderItem.setRealAmount(realAmount);
        }
    }

    /**
     * 获取订单促销信息
     */
    private String getOrderPromotionInfo(List<OmsOrderItemPo> orderItemList) {
        StringBuilder sb = new StringBuilder();
        for (OmsOrderItemPo orderItem : orderItemList) {
            sb.append(orderItem.getPromotionName());
            sb.append(";");
        }
        String result = sb.toString();
        if (result.endsWith(";")) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }

    /**
     * 计算订单应付金额
     */
    private BigDecimal calcPayAmount(OmsOrderPo order) {
        //总金额+运费-促销优惠-优惠券优惠-积分抵扣
        BigDecimal payAmount = order.getTotalAmount()
                .add(order.getFreightAmount())
                .subtract(order.getPromotionAmount())
                .subtract(order.getCouponAmount())
                .subtract(order.getIntegrationAmount());
        return payAmount;
    }

    /**
     * 计算订单优惠券金额
     */
    private BigDecimal calcIntegrationAmount(List<OmsOrderItemPo> orderItemList) {
        BigDecimal integrationAmount = new BigDecimal(0);
        for (OmsOrderItemPo orderItem : orderItemList) {
            if (orderItem.getIntegrationAmount() != null) {
                integrationAmount = integrationAmount.add(orderItem.getIntegrationAmount().multiply(new BigDecimal(orderItem.getProductQuantity())));
            }
        }
        return integrationAmount;
    }

    /**
     * 计算订单优惠券金额
     */
    private BigDecimal calcCouponAmount(List<OmsOrderItemPo> orderItemList) {
        BigDecimal couponAmount = new BigDecimal(0);
        for (OmsOrderItemPo orderItem : orderItemList) {
            if (orderItem.getCouponAmount() != null) {
                couponAmount = couponAmount.add(orderItem.getCouponAmount().multiply(new BigDecimal(orderItem.getProductQuantity())));
            }
        }
        return couponAmount;
    }

    /**
     * 计算订单活动优惠
     */
    private BigDecimal calcPromotionAmount(List<OmsOrderItemPo> orderItemList) {
        BigDecimal promotionAmount = new BigDecimal(0);
        for (OmsOrderItemPo orderItem : orderItemList) {
            if (orderItem.getPromotionAmount() != null) {
                promotionAmount = promotionAmount.add(orderItem.getPromotionAmount().multiply(new BigDecimal(orderItem.getProductQuantity())));
            }
        }
        return promotionAmount;
    }

    /**
     * 获取可用积分抵扣金额
     *
     * @param useIntegration 使用的积分数量
     * @param totalAmount    订单总金额
     * @param currentMember  使用的用户
     * @param hasCoupon      是否已经使用优惠券
     */
    private BigDecimal getUseIntegrationAmount(Integer useIntegration, BigDecimal totalAmount, UmsMemberPo currentMember, boolean hasCoupon) {
        BigDecimal zeroAmount = new BigDecimal(0);
        //判断用户是否有这么多积分
        if (useIntegration.compareTo(currentMember.getIntegration()) > 0) {
            return zeroAmount;
        }
        //根据积分使用规则判断是否可用
        //是否可与优惠券共用
        UmsIntegrationConsumeSettingPo integrationConsumeSetting = integrationConsumeSettingDao.selectById(1L);
        if (hasCoupon && integrationConsumeSetting.getCouponStatus().equals(0)) {
            //不可与优惠券共用
            return zeroAmount;
        }
        //是否达到最低使用积分门槛
        if (useIntegration.compareTo(integrationConsumeSetting.getUseUnit()) < 0) {
            return zeroAmount;
        }
        //是否超过订单抵用最高百分比
        BigDecimal integrationAmount = new BigDecimal(useIntegration).divide(new BigDecimal(integrationConsumeSetting.getUseUnit()), 2, RoundingMode.HALF_EVEN);
        BigDecimal maxPercent = new BigDecimal(integrationConsumeSetting.getMaxPercentPerOrder()).divide(new BigDecimal(100), 2, RoundingMode.HALF_EVEN);
        if (integrationAmount.compareTo(totalAmount.multiply(maxPercent)) > 0) {
            return zeroAmount;
        }
        return integrationAmount;
    }

    /**
     * 对优惠券优惠进行处理
     *
     * @param orderItemList       order_item列表
     * @param couponHistoryDetail 可用优惠券详情
     */
    private void handleCouponAmount(List<OmsOrderItemPo> orderItemList, SmsCouponHistoryDetail couponHistoryDetail) {
        // 获取优惠券信息
        SmsCouponPo coupon = couponHistoryDetail.getCoupon();
        // 根据优惠券的使用类型进行不同处理
        if (coupon.getUseType().equals(0)) {
            // 全场通用
            calcPerCouponAmount(orderItemList, coupon);
        } else if (coupon.getUseType().equals(1)) {
            // 指定分类
            List<OmsOrderItemPo> couponOrderItemList = getCouponOrderItemByRelation(couponHistoryDetail, orderItemList, 0);
            calcPerCouponAmount(couponOrderItemList, coupon);
        } else if (coupon.getUseType().equals(2)) {
            // 指定商品
            List<OmsOrderItemPo> couponOrderItemList = getCouponOrderItemByRelation(couponHistoryDetail, orderItemList, 1);
            calcPerCouponAmount(couponOrderItemList, coupon);
        }
    }


    /**
     * 对每个下单商品进行优惠券金额分摊的计算
     *
     * @param orderItemList 可用优惠券的下单商品商品
     * @param coupon        优惠券信息
     */
    private void calcPerCouponAmount(List<OmsOrderItemPo> orderItemList, SmsCouponPo coupon) {
        // 计算所有可用优惠券的下单商品的总价
        BigDecimal totalAmount = calcTotalAmount(orderItemList);
        for (OmsOrderItemPo orderItem : orderItemList) {
            // 根据每个商品的价格与总价的比例，计算该商品应分摊的优惠券金额
            //(商品价格/可用商品总价)*优惠券面额
            BigDecimal couponAmount = orderItem.getProductPrice().divide(totalAmount, 3, RoundingMode.HALF_EVEN).multiply(coupon.getAmount());
            // 将计算出的优惠券金额设置到商品对象中
            orderItem.setCouponAmount(couponAmount);
        }
    }


    /**
     * 获取与优惠券有关系的下单商品
     *
     * @param couponHistoryDetail 优惠券详情
     * @param orderItemList       下单商品
     * @param type                使用关系类型：0->相关分类；1->指定商品
     */
    private List<OmsOrderItemPo> getCouponOrderItemByRelation(SmsCouponHistoryDetail couponHistoryDetail, List<OmsOrderItemPo> orderItemList, int type) {
        List<OmsOrderItemPo> result = new ArrayList<>();
        if (type == 0) {
            List<Long> categoryIdList = new ArrayList<>();
            for (SmsCouponProductCategoryRelationPo productCategoryRelation : couponHistoryDetail.getCategoryRelationList()) {
                categoryIdList.add(productCategoryRelation.getProductCategoryId());
            }
            for (OmsOrderItemPo orderItem : orderItemList) {
                if (categoryIdList.contains(orderItem.getProductCategoryId())) {
                    result.add(orderItem);
                } else {
                    orderItem.setCouponAmount(new BigDecimal(0));
                }
            }
        } else if (type == 1) {
            List<Long> productIdList = new ArrayList<>();
            for (SmsCouponProductRelationPo productRelation : couponHistoryDetail.getProductRelationList()) {
                productIdList.add(productRelation.getProductId());
            }
            for (OmsOrderItemPo orderItem : orderItemList) {
                if (productIdList.contains(orderItem.getProductId())) {
                    result.add(orderItem);
                } else {
                    orderItem.setCouponAmount(new BigDecimal(0));
                }
            }
        }
        return result;
    }

    /**
     * 获取该用户可以使用的优惠券
     *
     * @param cartPromotionItemList 购物车优惠列表
     * @param couponId              使用优惠券id
     */
    private SmsCouponHistoryDetail getUseCoupon(List<CartPromotionItem> cartPromotionItemList, Long couponId) {
        List<SmsCouponHistoryDetail> couponHistoryDetailList = memberCouponService.listCart(cartPromotionItemList, 1);
        for (SmsCouponHistoryDetail couponHistoryDetail : couponHistoryDetailList) {
            if (couponHistoryDetail.getCoupon().getId().equals(couponId)) {
                return couponHistoryDetail;
            }
        }
        return null;
    }

    /**
     * 计算总金额
     *
     * 该方法用于计算给定订单项列表的总金额
     * 它通过遍历每个订单项，将商品价格与商品数量相乘，然后累加到总金额中
     *
     * @param orderItemList 订单项列表，包含多个订单项对象
     *                      每个订单项对象应包含商品价格和商品数量信息
     * @return BigDecimal 返回计算出的总金额
     */
    private BigDecimal calcTotalAmount(List<OmsOrderItemPo> orderItemList) {
        // 初始化总金额为0，用于累加每个订单项的金额
        BigDecimal totalAmount = new BigDecimal("0");
        // 遍历订单项列表
        for (OmsOrderItemPo item : orderItemList) {
            // 将当前订单项的商品价格与商品数量相乘，然后加到总金额上
            totalAmount = totalAmount.add(item.getProductPrice().multiply(new BigDecimal(item.getProductQuantity())));
        }
        // 返回计算出的总金额
        return totalAmount;
    }


    /**
     * 锁定下单商品的所有库存
     */
    private void lockStock(List<CartPromotionItem> cartPromotionItemList) {
        for (CartPromotionItem cartPromotionItem : cartPromotionItemList) {
            PmsSkuStockPo skuStock = skuStockDao.selectById(cartPromotionItem.getProductSkuId());
            skuStock.setLockStock(skuStock.getLockStock() + cartPromotionItem.getQuantity());
            skuStockDao.updateById(skuStock);
        }
    }

    /**
     * 判断下单商品是否都有库存
     */
    private boolean hasStock(List<CartPromotionItem> cartPromotionItemList) {
        for (CartPromotionItem cartPromotionItem : cartPromotionItemList) {
            if (cartPromotionItem.getRealStock()==null||cartPromotionItem.getRealStock() <= 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 计算购物车中商品的价格
     */
    private ConfirmOrderResult.CalcAmount calcCartAmount(List<CartPromotionItem> cartPromotionItemList) {
        ConfirmOrderResult.CalcAmount calcAmount = new ConfirmOrderResult.CalcAmount();
        calcAmount.setFreightAmount(new BigDecimal(0));
        BigDecimal totalAmount = new BigDecimal("0");
        BigDecimal promotionAmount = new BigDecimal("0");
        for (CartPromotionItem cartPromotionItem : cartPromotionItemList) {
            totalAmount = totalAmount.add(cartPromotionItem.getPrice().multiply(new BigDecimal(cartPromotionItem.getQuantity())));
            promotionAmount = promotionAmount.add(cartPromotionItem.getReduceAmount().multiply(new BigDecimal(cartPromotionItem.getQuantity())));
        }
        calcAmount.setTotalAmount(totalAmount);
        calcAmount.setPromotionAmount(promotionAmount);
        calcAmount.setPayAmount(totalAmount.subtract(promotionAmount));
        return calcAmount;
    }

}
