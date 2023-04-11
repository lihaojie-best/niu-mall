package com.niu.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.niu.mall.po.CmsSubjectPo;

import java.util.List;

/**
 * 专题表 服务类
 *
 * @author lihaojie
 * @date 2023/01/18 17:25
 **/
public interface CmsSubjectService extends IService<CmsSubjectPo> {
    /**
     * 根据专题名称分页获取商品专题
     * 
     * @param keyword 专题名
     * @param pageNum 当前分页
     * @param pageSize  分页大小
     * @return java.util.List<CmsSubjectPo>
     * @author lihaojie
     * @date 2023/01/18 17:23
     */
    List<CmsSubjectPo> list(String keyword, Integer pageNum, Integer pageSize);
}
