package com.niu.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.niu.mall.dao.SmsFlashPromotionProductRelationDao;
import com.niu.mall.po.SmsFlashPromotionProductRelationPo;
import com.niu.mall.service.SmsFlashPromotionProductRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品限时购与商品关系表 服务实现类
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Service
public class SmsFlashPromotionProductRelationServiceImpl extends ServiceImpl<SmsFlashPromotionProductRelationDao, SmsFlashPromotionProductRelationPo> implements SmsFlashPromotionProductRelationService {
    @Autowired
    private SmsFlashPromotionProductRelationDao dao;
    @Override
    public List<SmsFlashPromotionProductRelationPo> list(Long flashPromotionId, Long flashPromotionSessionId, Integer pageSize, Integer pageNum) {
        QueryWrapper<SmsFlashPromotionProductRelationPo> wrapper = new QueryWrapper<>();
        //创建插叙条件
        wrapper.eq("flash_promotion_session_id",flashPromotionSessionId).eq("flash_promotion_id",flashPromotionId);
        //开启分页
        PageHelper.startPage(pageNum, pageSize);
        List<SmsFlashPromotionProductRelationPo> list = null;
        try {
            //查询
            list = dao.selectList(wrapper);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    /**
     * 根据活动和场次id获取商品关系数量
     *
     * @param flashPromotionId 限时购id
     * @param flashPromotionSessionId  限时购场次id
     * @return long
     * @author lihaojie
     * @date 2023/04/09 20:46
     */
    @Override
    public long getCount(Long flashPromotionId, Long flashPromotionSessionId) {
        return dao.selectCount(new QueryWrapper<SmsFlashPromotionProductRelationPo>().eq("flash_promotion_session_id", flashPromotionSessionId).eq("flash_promotion_id", flashPromotionId));
    }
}
