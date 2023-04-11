package com.niu.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niu.mall.dao.SmsHomeAdvertiseDao;
import com.niu.mall.po.SmsHomeAdvertisePo;
import com.niu.mall.service.SmsHomeAdvertiseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 首页轮播广告表 服务实现类
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Service
public class SmsHomeAdvertiseServiceImpl extends ServiceImpl<SmsHomeAdvertiseDao, SmsHomeAdvertisePo> implements SmsHomeAdvertiseService {
    @Autowired
    private SmsHomeAdvertiseDao homeAdvertiseDao;

    /**
     * 分页查询广告
     *
     * @param name     名字
     * @param type     类型
     * @param endTime  结束日期
     * @param pageNum  当前页
     * @param pageSize 页面大小
     * @return java.util.List<com.niu.mall.po.SmsHomeAdvertisePo>
     * @author lihaojie
     * @date 2023/04/09 21:27
     */
    @Override
    public List<SmsHomeAdvertisePo> list(String name, Integer type, String endTime, Integer pageNum, Integer pageSize) {
        QueryWrapper<SmsHomeAdvertisePo> wrapper = new QueryWrapper<>();
        if (!StringUtil.isNullOrEmpty(name)) {
            wrapper.like("name", name);
        }
        if (type != null) {
            wrapper.eq("type", type);
        }
        if (!StringUtil.isNullOrEmpty(endTime) ){
            String startStr = endTime +" 00:00:00";
            String endStr = endTime + " 23:59:59";
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start = null;
            try {
                start = dateFormat.parse(startStr);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            Date end = null;
            try {
                end = dateFormat.parse(endStr);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            wrapper.between("end_time", start, end);

        }
        wrapper.orderByAsc("sort");
        return homeAdvertiseDao.selectList(wrapper);
    }
}
