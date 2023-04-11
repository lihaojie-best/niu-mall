package com.niu.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.niu.mall.dao.SmsCouponDao;
import com.niu.mall.dao.SmsCouponProductCategoryRelationDao;
import com.niu.mall.dao.SmsCouponProductRelationDao;
import com.niu.mall.param.SmsCouponParam;
import com.niu.mall.service.SmsCouponService;
import com.niu.mall.po.SmsCouponPo;
import com.niu.mall.po.SmsCouponProductCategoryRelationPo;
import com.niu.mall.po.SmsCouponProductRelationPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

/**
 * <p>
 * 优惠券表 服务实现类
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Service
public class SmsCouponServiceImpl extends ServiceImpl<SmsCouponDao, SmsCouponPo> implements SmsCouponService {

    @Autowired
    private SmsCouponProductRelationDao productRelationDao;
    @Autowired
    private SmsCouponProductCategoryRelationDao productCategoryRelationDao;
    @Autowired
    private SmsCouponDao couponDao;

    /**
     * 添加优惠卷
     *
     * @param couponParam 添加优惠卷实体类
     * @return int
     * @author lihaojie
     * @date 2023/04/05 20:32
     */
    @Override
    public int create(SmsCouponParam couponParam) {
        // 优惠卷信息补全完整
        //初始化已使用数量
        couponParam.setUseCount(0);
        //初始化数量 优惠卷数量等于发行量
        couponParam.setCount(couponParam.getPublishCount());
        //初始化以领取数量 0
        couponParam.setReceiveCount(0);
        // 优惠卷入库
        int count = couponDao.insert(couponParam);
        //  插入优惠券和商品关系表
        if (couponParam.getUseType().equals(2)) {  //使用类型：0->全场通用；1->指定分类；2->指定商品
            for (SmsCouponProductRelationPo productRelation : couponParam.getProductRelationPoList()) {
                //设置优惠卷与商品关系表的优惠卷id
                productRelation.setCouponId(couponParam.getId());
            }
            productRelationDao.insertList(couponParam.getProductRelationPoList());
        }
        //  插入优惠劵有商品分类关系表
        if (couponParam.getUseType().equals(1)) {
            for (SmsCouponProductCategoryRelationPo categoryRelationPo : couponParam.getProductCategoryRelationPoList()) {
                //这是优惠卷与商品分类关系表中的优惠卷id
                categoryRelationPo.setCouponId(couponParam.getId());
            }
            productCategoryRelationDao.insertList(couponParam.getProductCategoryRelationPoList());
        }
        return count;
    }

    /**
     * 更新优惠卷
     *
     * @param smsCouponParam 优惠卷实体类
     * @param id
     * @return 影响数据库的行数
     * @author lihaojie
     * @date 2023/04/06 09:45
     */
    @Override
    public int update(SmsCouponParam smsCouponParam, Long id) {
        // 更新coupon表 以及 商品关系表 或者 产品关系表
        //补全数据
        smsCouponParam.setId(id);
        int count = couponDao.updateById(smsCouponParam);
        //维护商品关系表
        if (smsCouponParam.getUseType().equals(2)) {
            //维护商品关系表
            for (SmsCouponProductRelationPo productRelationPo : smsCouponParam.getProductRelationPoList()) {
                productRelationPo.setCouponId(smsCouponParam.getId());
            }
            //删除原来的数据
            productRelationDao.delete(new QueryWrapper<SmsCouponProductRelationPo>().eq("coupon_id", smsCouponParam.getId()));
            //插入新的数据
            productRelationDao.insertList(smsCouponParam.getProductRelationPoList());
        }
        //维护商品分类关系表
        if (smsCouponParam.getUseType().equals(1)) {
            //维护商品分类关系表
            for (SmsCouponProductCategoryRelationPo categoryRelationPo : smsCouponParam.getProductCategoryRelationPoList()) {
                categoryRelationPo.setCouponId(smsCouponParam.getId());
            }
            //删除以前的数据
            productCategoryRelationDao.delete(new QueryWrapper<SmsCouponProductCategoryRelationPo>().eq("coupon_id", smsCouponParam.getId()));
            //插入新数据
            productCategoryRelationDao.insertList(smsCouponParam.getProductCategoryRelationPoList());
        }
        return 0;
    }

    /**
     * 删除Coupon
     *
     * @param id couponId
     * @return int
     * @author lihaojie
     * @date 2023/04/06 10:20
     */
    @Override
    public int delete(Long id) {
        // 删除coupon以及商品关系表/商品分类关系表
        //根据id获取coupon详细信息
        SmsCouponPo couponPoDB = couponDao.selectById(id);
        //清除coupon
        int count = couponDao.deleteById(id);
        if (couponPoDB.getType().equals(2)) {
            //删除商品依赖表
            productRelationDao.delete(new QueryWrapper<SmsCouponProductRelationPo>().eq("coupon_id", id));
        }
        if (couponPoDB.getType().equals(1)) {
            //删除商品分类表
            productCategoryRelationDao.delete(new QueryWrapper<SmsCouponProductCategoryRelationPo>().eq("coupon_id", id));
        }
        return count;
    }
    /**
     * 获取单个优惠券的详细信息
     *
     * @param id  CouponId
     * @return SmsCouponParam
     * @author lihaojie
     * @date 2023/04/06 10:37
     */
    @Override
    public SmsCouponParam selectById(Long id) {
        /*SmsCouponParam couponParam = new SmsCouponParam();
        SmsCouponPo couponPo = couponDao.selectById(id);
        //复制属性
        BeanUtils.copyProperties(couponPo, couponParam);
        if (couponPo.getUseType().equals(2)) {
            //获取商品关系表
            List<SmsCouponProductRelationPo> couponProductRelationPos = productRelationDao.selectList(new QueryWrapper<SmsCouponProductRelationPo>().eq("coupon_id", id));
            //传入到param
            couponParam.setProductRelationPoList(couponProductRelationPos);
        }
        if (couponPo.getUseType().equals(1)) {
            //获取商品分类关系表
            List<SmsCouponProductCategoryRelationPo> categoryRelationPos = productCategoryRelationDao.selectList(new QueryWrapper<SmsCouponProductCategoryRelationPo>().eq("coupon_id", id));
            //插入parm
            couponParam.setProductCategoryRelationPoList(categoryRelationPos);
        }
        return couponParam;*/
        return couponDao.getItem(id);
    }
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
    @Override
    public List<SmsCouponPo> list(String name, Integer type, Integer pageSize, Integer pageNum) {
        // 现根据条件查询书分页列表 然后进行分页
        QueryWrapper<SmsCouponPo> wrapper = new QueryWrapper<>();
        if (name != null) {
            wrapper.and(smsCouponPoQueryWrapper -> smsCouponPoQueryWrapper.like("name",name));
        }
        if (type != null) {
            wrapper.and(smsCouponPoQueryWrapper -> smsCouponPoQueryWrapper.eq("type", type));
        }
        PageHelper.startPage(pageNum, pageSize);
        List<SmsCouponPo> smsCouponPos = null;
        try {
            smsCouponPos = couponDao.selectList(wrapper);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return smsCouponPos;
    }
}
