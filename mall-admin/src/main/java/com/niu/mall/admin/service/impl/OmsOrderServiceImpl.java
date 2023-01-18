package com.niu.mall.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.niu.mall.admin.dao.OmsOrderDao;
import com.niu.mall.admin.dao.OmsOrderOperateHistoryDao;
import com.niu.mall.admin.dto.OmsOrderDetailDto;
import com.niu.mall.admin.param.OmsMoneyInfoParam;
import com.niu.mall.admin.param.OmsOrderDeliveryParam;
import com.niu.mall.admin.param.OmsOrderQueryParam;
import com.niu.mall.admin.param.OmsReceiverInfoParam;
import com.niu.mall.admin.service.OmsOrderService;
import com.niu.mall.mbg.po.OmsOrderOperateHistoryPo;
import com.niu.mall.mbg.po.OmsOrderPo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单表 服务实现类
 *
 * @author lihaojie
 * @date 2023/01/16 23:48
 **/
@Service
public class OmsOrderServiceImpl extends ServiceImpl<OmsOrderDao, OmsOrderPo> implements OmsOrderService {
    @Autowired
    public OmsOrderDao omsOrderDao;
    @Autowired
    private OmsOrderOperateHistoryDao omsOrderOperateHistoryDao;

    /**
     * 查询订单
     *
     * @param queryParam 查询参数
     * @param pageSize   分页大小
     * @param pageNum    查询页数
     * @return java.util.List<com.niu.mall.mbg.po.OmsOrderPo>
     * @author lihaojie
     * @date 2023/01/16 23:46
     */
    @Override
    public List<OmsOrderPo> list(OmsOrderQueryParam queryParam, Integer pageSize, Integer pageNum) {
        List<OmsOrderPo> omsOrderPos = null;
        try {
            //1.开启分页
            PageHelper.startPage(pageNum, pageSize);
            //2.查询
            QueryWrapper<OmsOrderPo> whereWrapper = new QueryWrapper<>();
            if (StrUtil.isNotEmpty(queryParam.getOrderSn())) {
                whereWrapper.eq("order_sn", queryParam.getOrderSn());
            }
            if (StrUtil.isNotEmpty(queryParam.getReceiverKeyword()) && StrUtil.isNotBlank(queryParam.getReceiverKeyword())) {
                whereWrapper.and(omsOrderPoQueryWrapper -> omsOrderPoQueryWrapper.eq("receiver_keyword", queryParam.getReceiverKeyword()));
            }
            if (queryParam.getStatus() != null) {
                whereWrapper.and(omsOrderPoQueryWrapper -> omsOrderPoQueryWrapper.eq("status", queryParam.getStatus()));
            }
            if (queryParam.getOrderType() != null) {
                whereWrapper.and(omsOrderPoQueryWrapper -> omsOrderPoQueryWrapper.eq("order_type", queryParam.getOrderType()));
            }
            if (queryParam.getSourceType() != null) {
                whereWrapper.and(omsOrderPoQueryWrapper -> omsOrderPoQueryWrapper.eq("source_type", queryParam.getSourceType()));
            }
            if (StrUtil.isNotEmpty(queryParam.getCreateTime()) && StrUtil.isNotBlank(queryParam.getCreateTime())) {
                whereWrapper.and(omsOrderPoQueryWrapper -> omsOrderPoQueryWrapper.eq("create_time", queryParam.getCreateTime()));
            }
            omsOrderPos = omsOrderDao.selectList(whereWrapper);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return omsOrderPos;
    }

    /**
     * 批量发货
     *
     * @param deliveryParamList 每个订单的发货信息
     * @return int
     * @author lihaojie
     * @date 2023/01/17 00:16
     */
    @Override
    public int delivery(List<OmsOrderDeliveryParam> deliveryParamList) {
        //1.将发货参数 装换位po
        List<OmsOrderPo> list = new ArrayList<>();
        deliveryParamList.forEach(
                deliveryParam ->
                        list.add(new OmsOrderPo()
                                .setId(deliveryParam.getOrderId())
                                .setDeliveryCompany(deliveryParam.getDeliveryCompany())
                                .setDeliverySn(deliveryParam.getDeliverySn())));
        //2. 对每一个po进行更新
        list.forEach(omsOrderPo -> omsOrderDao.updateById(omsOrderPo));
        return 0;
    }

    /**
     * 批量关闭订单
     *
     * @param ids  id列表
     * @param note 订单操作历史记录备注
     * @return int
     * @author lihaojie
     * @date 2023/01/17 00:32
     */
    @Override
    public int close(List<Long> ids, String note) {
        int count = 0;
        try {
            //1.创建一个跟新entity
            OmsOrderPo record = new OmsOrderPo().setStatus(4);
            //2. 更新订单状态
            QueryWrapper<OmsOrderPo> whereWrapper = new QueryWrapper<>();
            whereWrapper.eq("delete_status", 0).and(omsOrderPoQueryWrapper -> omsOrderPoQueryWrapper.in("id", ids));
            count = omsOrderDao.update(record, whereWrapper);
            //3. 将ids封装为historyList
            List<OmsOrderOperateHistoryPo> historyList = ids.stream().map(orderId -> {
                OmsOrderOperateHistoryPo history = new OmsOrderOperateHistoryPo();
                history.setOrderId(orderId);
                history.setCreateTime(new Date());
                history.setOperateMan("后台管理员");
                history.setOrderStatus(4);
                history.setNote("订单关闭:" + note);
                return history;
            }).collect(Collectors.toList());
            //4.插入订单关闭历史
            historyList.forEach(history -> omsOrderOperateHistoryDao.insert(history));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    /**
     * 根据id获取订单信息
     *
     * @param id 订单id
     * @return com.niu.mall.admin.dto.OmsOrderDetail
     * @author lihaojie
     * @date 2023/01/17 00:49
     */
    @Override
    public OmsOrderDetailDto detail(Long id) {
        return omsOrderDao.getDetail(id);
    }

    /**
     * 修改收货人信息
     *
     * @param receiverInfoParam 订单修改收货人信息参数
     * @return int
     * @author lihaojie
     * @date 2023/01/17 11:39
     */
    @Override
    public int updateReceiverInfo(OmsReceiverInfoParam receiverInfoParam) {
        int count = 0;
        try {
            //1.将修改参数转为po层
            OmsOrderPo omsOrderPo = new OmsOrderPo();
            //1.1 拷贝参数
            BeanUtils.copyProperties(receiverInfoParam, omsOrderPo);
            omsOrderPo.setId(receiverInfoParam.getOrderId());
            //2. 根据id更新
            count = omsOrderDao.updateById(omsOrderPo);
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    /**
     * 修改订单费用信息
     *
     * @param moneyInfoParam 修改订单费用信息参数
     * @return int
     * @author lihaojie
     * @date 2023/01/17 12:23
     */
    @Override
    public int updateMoneyInfo(OmsMoneyInfoParam moneyInfoParam) {
        int count = 0;
        try {
            //1. OmsMoneyInfoParam -> OmsOrderPo
            OmsOrderPo omsOrderPo = new OmsOrderPo();
         /*   omsOrderPo.setId(moneyInfoParam.getOrderId());
            omsOrderPo.setFreightAmount(moneyInfoParam.getFreightAmount());
            omsOrderPo.setDiscountAmount(moneyInfoParam.getDiscountAmount());
            omsOrderPo.setStatus(moneyInfoParam.getStatus());*/
            BeanUtils.copyProperties(moneyInfoParam, omsOrderPo);
            //2. 更新
            count = omsOrderDao.update(omsOrderPo, new QueryWrapper<OmsOrderPo>().eq("id", moneyInfoParam.getOrderId()));
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    /**
     * 备注订单
     *
     * @param id     订单id
     * @param note   订单备注
     * @param status 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
     * @return int
     * @author lihaojie
     * @date 2023/01/17 12:24
     */
    @Override
    public int updateNote(Long id, String note, Integer status) {
        int count = 0;
        try {
            //转换为po
            OmsOrderPo omsOrderPo = new OmsOrderPo().setId(id).setNote(note).setStatus(status);
            count = omsOrderDao.updateById(omsOrderPo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return count;
    }
}
