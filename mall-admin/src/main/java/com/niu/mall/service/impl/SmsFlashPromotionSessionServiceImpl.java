package com.niu.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.niu.mall.dao.SmsFlashPromotionSessionDao;
import com.niu.mall.dto.SmsFlashPromotionSessionDto;
import com.niu.mall.po.SmsFlashPromotionSessionPo;
import com.niu.mall.service.SmsFlashPromotionProductRelationService;
import com.niu.mall.service.SmsFlashPromotionSessionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 限时购场次表 服务实现类
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Service
public class SmsFlashPromotionSessionServiceImpl extends ServiceImpl<SmsFlashPromotionSessionDao, SmsFlashPromotionSessionPo> implements SmsFlashPromotionSessionService {
    @Autowired
    private SmsFlashPromotionSessionDao sessionDao;
    @Autowired
    private SmsFlashPromotionProductRelationService relationService;

    /**
     * 添加活动场次
     *
     * @param sessionPo 活动场次实体类
     * @return int
     * @author lihaojie
     * @date 2023/04/09 17:49
     */
    @Override
    public int create(SmsFlashPromotionSessionPo sessionPo) {
        //设置创建时间
        sessionPo.setCreateTime(new Date());
        return sessionDao.insert(sessionPo);
    }

    /**
     * 根据活动场次id更新
     *
     * @param id                 活动场次id
     * @param promotionSessionPo 活动场次实体类
     * @return int
     * @author lihaojie
     * @date 2023/04/09 17:50
     */
    @Override
    public int update(Long id, SmsFlashPromotionSessionPo promotionSessionPo) {
        //补全实体类
        promotionSessionPo.setId(id);
        // 根据id更新
        return sessionDao.updateById(promotionSessionPo);
    }

    @Override
    public List<SmsFlashPromotionSessionDto> list(Long flashPromotionId) {
        ArrayList<SmsFlashPromotionSessionDto> result = new ArrayList<>();
        //获取list
        List<SmsFlashPromotionSessionPo> list = sessionDao.selectList(new QueryWrapper<SmsFlashPromotionSessionPo>().eq("status", 1));
        list.forEach(promotionSessionPo -> {
            SmsFlashPromotionSessionDto sessionDto = new SmsFlashPromotionSessionDto();
            BeanUtils.copyProperties(promotionSessionPo, sessionDto);
            long count = relationService.getCount(flashPromotionId,promotionSessionPo.getId());
            sessionDto.setProductCount(count);
            result.add(sessionDto);
        });
        return result;
    }
}
