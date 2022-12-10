package com.niu.mall.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.niu.mall.mbg.po.PmsBrandPo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 品牌表 服务类
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
public interface PmsBrandService extends IService<PmsBrandPo> {

    /**
     * 模糊查询
     *
     * @param keyword  关键字
     * @param pageNum  当前页
     * @param pageSize 页面大小
     * @return java.util.List<com.niu.mall.mbg.po.PmsBrandPo>
     * @author lihaojie
     * @date 2022/12/06 21:24
     */
    List<PmsBrandPo> listByKeyword(String keyword, int pageNum, int pageSize);
    @Transactional
    int creat(PmsBrandPo brand);

}
