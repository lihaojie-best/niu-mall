package com.niu.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.niu.mall.dao.SmsHomeBrandDao;
import com.niu.mall.po.SmsHomeBrandPo;
import com.niu.mall.service.SmsHomeBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Timer;
import java.util.stream.Collectors;

/**
 * <p>
 * 首页推荐品牌表 服务实现类
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Service
public class SmsHomeBrandServiceImpl extends ServiceImpl<SmsHomeBrandDao, SmsHomeBrandPo> implements SmsHomeBrandService {
    @Autowired
    private SmsHomeBrandDao homeBrandDao;
    /**
     * 修改推荐品牌排序
     *
     * @param id 推荐品牌id
     * @param sort  排序
     * @return int
     * @author lihaojie
     * @date 2023/04/10 13:04
     */
    @Override
    public int updateSort(Long id, Integer sort) {

        SmsHomeBrandPo smsHomeBrandPo = new SmsHomeBrandPo();
        smsHomeBrandPo.setId(id).setSort(sort);
        return homeBrandDao.updateById(smsHomeBrandPo);
    }
    /**
     * 批量修改推荐品牌状态
     *
     * @param ids 推荐品牌表id
     * @param recommendStatus  推荐状态
     * @return int
     * @author lihaojie
     * @date 2023/04/10 13:03
     */
    @Override
    public int updateStatusBatchById(List<Long> ids, Integer recommendStatus) {
        return homeBrandDao.update(new SmsHomeBrandPo().setRecommendStatus(recommendStatus), new QueryWrapper<SmsHomeBrandPo>().in("id", ids));
    }
    /**
     * 分页查询推荐品牌
     *
     * @param brandName 品牌名
     * @param recommendStatus 推荐状态
     * @param pageSize 分页大小
     * @param pageNum  当前页
     * @return java.util.List<com.niu.mall.po.SmsHomeBrandPo>
     * @author lihaojie
     * @date 2023/04/10 16:29
     */
    @Override
    public List<SmsHomeBrandPo> list(String brandName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        QueryWrapper<SmsHomeBrandPo> wrapper = new QueryWrapper<>();
        if (!StringUtil.isNullOrEmpty(brandName)) {
            wrapper.and(smsHomeBrandPoQueryWrapper -> smsHomeBrandPoQueryWrapper.like("brand_name", brandName));
        }
        if (recommendStatus != null) {
            wrapper.and(smsHomeBrandPoQueryWrapper -> smsHomeBrandPoQueryWrapper.eq("recommend_status", recommendStatus));
        }
        PageHelper.startPage(pageNum, pageSize);
        List<SmsHomeBrandPo> list = homeBrandDao.selectList(wrapper);
        return list;
    }
}
