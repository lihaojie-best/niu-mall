package com.niu.mall.user.dao;


import com.niu.mall.user.domain.FlashPromotionProduct;
import com.niu.mall.user.po.CmsSubjectPo;
import com.niu.mall.user.po.PmsBrandPo;
import com.niu.mall.user.po.PmsProductPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 首页内容管理自定义Dao
 * Created by lihaojie on 2023/1/28.
 */
@Mapper
public interface HomeDao {

    /**
     * 获取推荐品牌
     */
    List<PmsBrandPo> getRecommendBrandList(@Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * 获取秒杀商品
     */
    List<FlashPromotionProduct> getFlashProductList(@Param("flashPromotionId") Long flashPromotionId, @Param("sessionId") Long sessionId);

    /**
     * 获取新品推荐
     */
    List<PmsProductPo> getNewProductList(@Param("offset") Integer offset, @Param("limit") Integer limit);
    /**
     * 获取人气推荐
     */
    List<PmsProductPo> getHotProductList(@Param("offset") Integer offset,@Param("limit") Integer limit);

    /**
     * 获取推荐专题
     */
    List<CmsSubjectPo> getRecommendSubjectList(@Param("offset") Integer offset, @Param("limit") Integer limit);
}
