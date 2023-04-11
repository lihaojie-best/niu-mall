package com.niu.mall.service;

import com.niu.mall.po.SmsFlashPromotionPo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 限时购表 服务类
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
public interface SmsFlashPromotionService extends IService<SmsFlashPromotionPo> {
    /**
     * 插入限时购活动
     *
     * @param flashPromotionPo  限时购表
     * @return int
     * @author lihaojie
     * @date 2023/04/08 13:42
     */
    int insert(SmsFlashPromotionPo flashPromotionPo);
    /**
     * 修改活动在线状态
     *
     * @param id 活动id
     * @param status  修改后的状态
     * @return int
     * @author lihaojie
     * @date 2023/04/08 13:54
     */
    int update(Long id, Integer status);
    /**
     * 根据关键词分页查询活动
     *
     * @param keyword 关键词
     * @param pageSize 分页大小
     * @param pageNum  当前页码
     * @return java.util.List<com.niu.mall.po.SmsFlashPromotionPo>
     * @author lihaojie
     * @date 2023/04/08 14:20
     */
    List<SmsFlashPromotionPo> list(String keyword, Integer pageSize, Integer pageNum);

}
