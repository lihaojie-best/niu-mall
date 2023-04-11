package com.niu.mall.service;

import com.niu.mall.param.SmsCouponParam;
import com.niu.mall.po.SmsCouponPo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 优惠券表 服务类
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
public interface SmsCouponService extends IService<SmsCouponPo> {


    /**
     * 添加优惠卷
     *
     * @param couponParam  添加优惠卷实体类
     * @return int
     * @author lihaojie
     * @date 2023/04/05 20:32
     */
    @Transactional
    int create(SmsCouponParam couponParam);

    /**
     * 更新优惠卷
     *
     * @param smsCouponParam 优惠卷实体类
     * @param id
     * @return 影响数据库的行数
     * @author lihaojie
     * @date 2023/04/06 09:45
     */
    @Transactional
    int update(SmsCouponParam smsCouponParam, Long id);

    /**
     * 删除Coupon
     *
     * @param id  couponId
     * @return int
     * @author lihaojie
     * @date 2023/04/06 10:20
     */
    int delete(Long id);
    /**
     * 获取单个优惠券的详细信息
     *
     * @param id  CouponId
     * @return SmsCouponParam
     * @author lihaojie
     * @date 2023/04/06 10:37
     */
    SmsCouponParam selectById(Long id);
    /**
     * 根据优惠券名称和类型分页获取优惠券列表
     *
     * @param name 优惠名字
     * @param type 类型
     * @param pageSize 分页大小
     * @param pageNum  当前页码
     * @return java.util.List<SmsCouponPo>
     * @author lihaojie
     * @date 2023/04/06 10:49
     */
    List<SmsCouponPo> list(String name, Integer type, Integer pageSize, Integer pageNum);
}
