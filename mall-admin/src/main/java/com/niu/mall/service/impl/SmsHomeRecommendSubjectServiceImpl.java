package com.niu.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.niu.mall.dao.SmsHomeRecommendSubjectDao;
import com.niu.mall.po.SmsHomeRecommendSubjectPo;
import com.niu.mall.service.SmsHomeRecommendSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页推荐专题表 服务实现类
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Service
public class SmsHomeRecommendSubjectServiceImpl extends ServiceImpl<SmsHomeRecommendSubjectDao, SmsHomeRecommendSubjectPo> implements SmsHomeRecommendSubjectService {
    @Autowired
    private SmsHomeRecommendSubjectDao homeRecommendSubjectDao;
    /**
     * 分页查询推荐
     *
     * @param subjectName 专题名
     * @param recommendStatus 推荐状态
     * @param pageSize 分页大小
     * @param pageNum  分页
     * @return java.util.List<com.niu.mall.po.SmsHomeRecommendSubjectPo>
     * @author lihaojie
     * @date 2023/04/10 22:20
     */
    @Override
    public List<SmsHomeRecommendSubjectPo> list(String subjectName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        QueryWrapper<SmsHomeRecommendSubjectPo> wrapper = new QueryWrapper<>();
        if (!StringUtil.isNullOrEmpty(subjectName)) {
            wrapper.and(smsHomeRecommendSubjectPoQueryWrapper -> smsHomeRecommendSubjectPoQueryWrapper.like("subject_name", subjectName));
        }
        if (recommendStatus != null) {
            wrapper.and(smsHomeRecommendSubjectPoQueryWrapper -> smsHomeRecommendSubjectPoQueryWrapper.eq("recommend_status", recommendStatus));
        }
        PageHelper.startPage(pageNum, pageSize);
        return homeRecommendSubjectDao.selectList(wrapper);
    }
}
