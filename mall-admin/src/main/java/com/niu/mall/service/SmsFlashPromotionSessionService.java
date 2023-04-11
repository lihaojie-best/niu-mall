package com.niu.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.niu.mall.dto.SmsFlashPromotionSessionDto;
import com.niu.mall.po.SmsFlashPromotionSessionPo;

import java.util.List;

/**
 * <p>
 * 限时购场次表 服务类
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
public interface SmsFlashPromotionSessionService extends IService<SmsFlashPromotionSessionPo> {

    /**
     * 添加活动场次
     *
     * @param sessionPo 活动场次实体类
     * @return int
     * @author lihaojie
     * @date 2023/04/09 17:49
     */
    int create(SmsFlashPromotionSessionPo sessionPo);

    /**
     * 根据活动场次id更新
     *
     * @param id                 活动场次id
     * @param promotionSessionPo 活动场次实体类
     * @return int
     * @author lihaojie
     * @date 2023/04/09 17:50
     */
    int update(Long id, SmsFlashPromotionSessionPo promotionSessionPo);
    /**
     * 根据id查询场次和可选场次
     *
     * @param flashPromotionId  活动id
     * @return java.util.List<com.niu.mall.dto.SmsFlashPromotionSessionDto>
     * @author lihaojie
     * @date 2023/04/09 19:55
     */
    List<SmsFlashPromotionSessionDto> list(Long flashPromotionId);
}
