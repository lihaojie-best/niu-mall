package com.niu.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.niu.mall.po.SmsFlashPromotionPo;
import com.niu.mall.dao.SmsFlashPromotionDao;
import com.niu.mall.service.SmsFlashPromotionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 限时购表 服务实现类
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Service
public class SmsFlashPromotionServiceImpl extends ServiceImpl<SmsFlashPromotionDao, SmsFlashPromotionPo> implements SmsFlashPromotionService {

    @Autowired
    private SmsFlashPromotionDao flashPromotionDao;
    /**
     * 插入限时购活动
     *
     * @param flashPromotionPo  限时购表
     * @return int
     * @author lihaojie
     * @date 2023/04/08 13:42
     */
    @Override
    public int insert(SmsFlashPromotionPo flashPromotionPo) {
        int count = 0;
        try {
            //插入
            count = flashPromotionDao.insert(flashPromotionPo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return count;

    }
    /**
     * 修改活动在线状态
     *
     * @param id 活动id
     * @param status  修改后的状态
     * @return int
     * @author lihaojie
     * @date 2023/04/08 13:54
     */
    @Override
    public int update(Long id, Integer status) {
        // 创建拆入实体类
        SmsFlashPromotionPo smsFlashPromotionPo = new SmsFlashPromotionPo();
        //补全信息
        smsFlashPromotionPo.setId(id).setStatus(status);
        int count = 0;
        try {
            // 操作数据库
            count = flashPromotionDao.updateById(smsFlashPromotionPo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return count;
    }
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
    @Override
    public List<SmsFlashPromotionPo> list(String keyword, Integer pageSize, Integer pageNum) {
        //创建查询模板
        QueryWrapper<SmsFlashPromotionPo> wrapper = new QueryWrapper<>();
        // 判断keyword是否为空
        if (!StringUtil.isNullOrEmpty(keyword)) {
            //添加条件
            wrapper.like("title", keyword);
        }
        //开启分页助手
        PageHelper.startPage(pageNum, pageSize);
        List<SmsFlashPromotionPo> list= null;
        try {
            //在数据库查询
            list = flashPromotionDao.selectList(wrapper);
        } catch (Exception e) {
            //抛出异常
            throw new RuntimeException(e);
        }
        return list;

    }
}
