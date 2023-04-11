package com.niu.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.niu.mall.po.PmsBrandPo;
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
     * @return java.util.List<PmsBrandPo>
     * @author lihaojie
     * @date 2022/12/06 21:24
     */
    List<PmsBrandPo> listByKeyword(String keyword, int pageNum, int pageSize);

    /**
     * 创建商品
     *
     * @param brand 创建商品参数
     * @return int
     * @author lihaojie
     * @date 2023/01/18 13:00
     */
    @Transactional
    int creat(PmsBrandPo brand);

    /**
     * 批量更新厂家制造商状态
     *
     * @param factoryStatus 厂家制造状态
     * @param ids           brandIds
     * @return int
     * @author lihaojie
     * @date 2023/01/18 12:59
     */
    int updateFactoryStatus(Integer factoryStatus, List<Long> ids);

    /**
     * 批量更新显示状态
     *
     * @param showStatus 显示状态
     * @param ids        ids
     * @return int
     * @author lihaojie
     * @date 2023/01/18 13:03
     */
    int updateShowStatus(Integer showStatus, List<Long> ids);

    /**
     * 更新品牌
     *
     * @param id      品牌id
     * @param brandPo 商品品牌参数
     * @return int
     * @author lihaojie
     * @date 2023/01/18 13:07
     */
    int update(long id, PmsBrandPo brandPo);
}
