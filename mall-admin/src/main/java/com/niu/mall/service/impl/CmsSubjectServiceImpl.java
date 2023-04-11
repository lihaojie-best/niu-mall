package com.niu.mall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.niu.mall.dao.CmsSubjectDao;
import com.niu.mall.po.CmsSubjectPo;
import com.niu.mall.service.CmsSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 专题表 服务实现类
 *
 * @author lihaojie
 * @date 2023/01/18 17:25
 **/
@Service
public class CmsSubjectServiceImpl extends ServiceImpl<CmsSubjectDao, CmsSubjectPo> implements CmsSubjectService {

    @Autowired
    private CmsSubjectDao subjectDao;

    /**
     * 根据专题名称分页获取商品专题
     *
     * @param keyword  专题名
     * @param pageNum  当前分页
     * @param pageSize 分页大小
     * @return java.util.List<CmsSubjectPo>
     * @author lihaojie
     * @date 2023/01/18 17:23
     */
    @Override
    public List<CmsSubjectPo> list(String keyword, Integer pageNum, Integer pageSize) {
        // 分页查询
        PageHelper.startPage(pageNum, pageSize);
        QueryWrapper<CmsSubjectPo> whereWrapper = new QueryWrapper<>();
        if(StrUtil.isNotEmpty(keyword)){
            whereWrapper.like("title", keyword);
        }
        List<CmsSubjectPo> cmsSubjectPos = null;
        try {
            // 查询
            cmsSubjectPos = subjectDao.selectList(whereWrapper);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return cmsSubjectPos;
    }
}
