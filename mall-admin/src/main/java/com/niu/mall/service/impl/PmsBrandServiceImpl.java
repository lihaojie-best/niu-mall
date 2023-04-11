package com.niu.mall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.niu.mall.dao.PmsBrandDao;
import com.niu.mall.service.PmsBrandService;
import com.niu.mall.common.exception.ApiException;
import com.niu.mall.po.PmsBrandPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 品牌表 服务实现类
 *
 * @author lihaojie
 * @date 2022/12/07 23:00
 **/
@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandDao, PmsBrandPo> implements PmsBrandService {
    @Autowired
    private PmsBrandDao brandDao;

    /**
     * 创建品牌
     *
     * @param brand 品牌实体类
     * @return int
     * @author lihaojie
     * @date 2022/12/07 11:13
     */
    @Override
    public int creat(PmsBrandPo brand) {
        int insert = 0;
        try {
            //将更新参数的id设置为空
            brand.setId(null);
            insert = brandDao.insert(brand);
        } catch (Exception e) {
            throw new ApiException(e);
        }
        return insert;
    }

    /**
     * 批量更新厂家制造商状态
     *
     * @param factoryStatus 厂家制造状态
     * @param ids           brandIds
     * @return int
     * @author lihaojie
     * @date 2023/01/18 12:59
     */
    @Override
    public int updateFactoryStatus(Integer factoryStatus, List<Long> ids) {
        int count = 0;
        try {
            count = brandDao.update(new PmsBrandPo().setFactoryStatus(factoryStatus), new QueryWrapper<PmsBrandPo>().in("id", ids));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    /**
     * 批量更新显示状态
     *
     * @param showStatus 显示状态
     * @param ids        ids
     * @return int
     * @author lihaojie
     * @date 2023/01/18 13:03
     */
    @Override
    public int updateShowStatus(Integer showStatus, List<Long> ids) {
        int count = 0;
        try {
            // 更新
            count = brandDao.update(new PmsBrandPo().setShowStatus(showStatus), new QueryWrapper<PmsBrandPo>().in("id", ids));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    /**
     * 更新品牌
     *
     * @param id      品牌id
     * @param brandPo 商品品牌参数
     * @return int
     * @author lihaojie
     * @date 2023/01/18 13:07
     */
    @Override
    public int update(long id, PmsBrandPo brandPo) {
        int count = 0;
        try {
            // 补全id
            brandPo.setId(id);
            // 更新
            count = brandDao.updateById(brandPo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    /**
     * 模糊查询
     *
     * @param keyword  关键字
     * @param pageNum  当前页
     * @param pageSize 页面大小
     * @return java.util.List<PmsBrandPo>
     * @author lihaojie
     * @date 2022/12/06 21:24
     */
    @Override
    public List<PmsBrandPo> listByKeyword(String keyword, int pageNum, int pageSize) {
        try {
            Page<Object> objectPage = PageHelper.startPage(pageNum, pageSize);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        QueryWrapper whereWrapper = new QueryWrapper<>();
        if (!StrUtil.isEmpty(keyword)) {
            whereWrapper.like("name", keyword);
        }
        List<PmsBrandPo> pmsBrandPoList = brandDao.selectList(whereWrapper);
        return pmsBrandPoList;
    }
}
