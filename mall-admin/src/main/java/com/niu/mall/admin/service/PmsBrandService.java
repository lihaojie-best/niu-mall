package com.niu.mall.admin.service;

import com.niu.mall.mbg.po.PmsBrandPo;
import com.baomidou.mybatisplus.extension.service.IService;

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
     * 获取全部品牌列表
     *
     * @author lihaojie
     * @date 2022/11/26 12:51
     * @return java.util.List<com.niu.mall.mbg.po.PmsBrandPo>
     */
    List<PmsBrandPo> listAllBrand();
}
