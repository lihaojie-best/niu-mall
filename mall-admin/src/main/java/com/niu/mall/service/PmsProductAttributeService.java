package com.niu.mall.service;

import com.niu.mall.dto.PmsProductAttrInfoDto;
import com.niu.mall.param.PmsProductAttributeParam;
import com.niu.mall.po.PmsProductAttributePo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品属性参数表 服务类
 *
 * @author lihaojie
 * @date 2022/12/14 12:17
 **/
public interface PmsProductAttributeService extends IService<PmsProductAttributePo> {
    /**
     * 新增商品属性
     *
     * @param pmsProductAttributeParam 商品属性参数实体类
     * @return int
     * @author lihaojie
     * @date 2022/12/12 21:31
     */
    @Transactional
    int creat(PmsProductAttributeParam pmsProductAttributeParam);

    /**
     * 批量删除商品属性
     *
     * @param ids 商品属性id集合
     * @return int
     * @author lihaojie
     * @date 2022/12/12 22:34
     */
    int delete(List<Long> ids);
    /**
     * 根据商品分类的id获取商品属性及属性分类
     *
     * @param productCategoryId 商品分类id
     * @return java.util.List<PmsProductAttrInfoDto>
     * @author lihaojie
     * @date 2022/12/18 12:46
     */
    List<PmsProductAttrInfoDto> getProductAttrInfo(Long productCategoryId);
    /**
     * 根据分类查询属性列表或参数列表
     *
     * @param cid 商品分类id
     * @param type 0表示属性，1表示参数
     * @param pageSize 分页大小
     * @param pageNum 分页当前页数
     * @return java.util.List<PmsProductAttributePo>
     * @author lihaojie
     * @date 2022/12/18 13:24
     */
    List<PmsProductAttributePo> getList(Long cid, Integer type, Integer pageSize, Integer pageNum);
}
