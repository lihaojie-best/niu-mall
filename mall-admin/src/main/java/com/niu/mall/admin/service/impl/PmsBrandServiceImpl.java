package com.niu.mall.admin.service.impl;

import com.niu.mall.mbg.po.PmsBrandPo;
import com.niu.mall.admin.dao.PmsBrandDao;
import com.niu.mall.admin.service.PmsBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandDao, PmsBrandPo> implements PmsBrandService {
    /**
     * 获取全部品牌列表
     *
     * @author lihaojie
     * @date 2022/11/26 12:51
     * @return java.util.List<com.niu.mall.mbg.po.PmsBrandPo>
     */
    @Override
    public List<PmsBrandPo> listAllBrand() {
        //1.需要
        return null;
    }
}
