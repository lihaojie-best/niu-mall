package com.niu.mall.service;

import com.niu.mall.po.SmsFlashPromotionProductRelationPo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品限时购与商品关系表 服务类
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
public interface SmsFlashPromotionProductRelationService extends IService<SmsFlashPromotionProductRelationPo> {

    List<SmsFlashPromotionProductRelationPo> list(Long flashPromotionId, Long flashPromotionSessionId, Integer pageSize, Integer pageNum);
    /**
     * 根据活动和场次id获取商品关系数量
     *
     * @param flashPromotionId 限时购id
     * @param flashPromotionSessionId  限时购场次id
     * @return long
     * @author lihaojie
     * @date 2023/04/09 20:46
     */
    long getCount(Long flashPromotionId, Long flashPromotionSessionId);
}
