package com.niu.mall.service;

import com.niu.mall.po.SmsHomeRecommendSubjectPo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页推荐专题表 服务类
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
public interface SmsHomeRecommendSubjectService extends IService<SmsHomeRecommendSubjectPo> {
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
    List<SmsHomeRecommendSubjectPo> list(String subjectName, Integer recommendStatus, Integer pageSize, Integer pageNum);
}
