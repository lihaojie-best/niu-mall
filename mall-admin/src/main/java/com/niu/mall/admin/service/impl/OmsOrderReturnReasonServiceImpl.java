package com.niu.mall.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.niu.mall.mbg.po.OmsOrderReturnReasonPo;
import com.niu.mall.admin.dao.OmsOrderReturnReasonDao;
import com.niu.mall.admin.service.OmsOrderReturnReasonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 退货原因表 服务实现类
 *
 * @author lihaojie
 * @date 2023/01/17 17:56
 **/
@Service
public class OmsOrderReturnReasonServiceImpl extends ServiceImpl<OmsOrderReturnReasonDao, OmsOrderReturnReasonPo> implements OmsOrderReturnReasonService {
    @Autowired
    private OmsOrderReturnReasonDao returnReasonDao;
    /**
     * 添加退货原因
     *
     * @param returnReason 退货原因表
     * @return int
     * @author lihaojie
     * @date 2023/01/17 17:51
     */
    @Override
    public int create(OmsOrderReturnReasonPo returnReason) {
        //1.补全创建时间
        returnReason.setCreateTime(new Date());
        int count = 0;
        try {
            //2. 插入
            count = returnReasonDao.insert(returnReason);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return count;
    }
    /**
     * 修改退货原因
     *
     * @param id 退货原因id
     * @param returnReason 退货原因参数
     * @return int
     * @author lihaojie
     * @date 2023/01/17 17:57
     */
    @Override
    public int update(Long id, OmsOrderReturnReasonPo returnReason) {
        //补全id
        returnReason.setId(id);
        int count = 0;
        try {
            // 更新
            count = returnReasonDao.updateById(returnReason);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return count;
    }
    /**
     * 批量删除
     *
     * @param ids id集合
     * @return int
     * @author lihaojie
     * @date 2023/01/17 20:57
     */
    @Override
    public int delete(List<Long> ids) {
        int count = 0;
        try {
            count = returnReasonDao.deleteBatchIds(ids);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return count;
    }
    /**
     * 根据id查询
     *
     * @param id  id
     * @return com.niu.mall.mbg.po.OmsOrderReturnReasonPo
     * @author lihaojie
     * @date 2023/01/17 21:04
     */
    @Override
    public OmsOrderReturnReasonPo getItem(Long id) {
        OmsOrderReturnReasonPo omsOrderReturnReasonPo = null;
        try {
            omsOrderReturnReasonPo = returnReasonDao.selectById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return omsOrderReturnReasonPo;
    }
    /**
     * 根据id修改退款申请状态
     *
     * @param ids ids
     * @param status 状态
     * @return int
     * @author lihaojie
     * @date 2023/01/18 12:19
     */
    @Override
    public int updateStatus(List<Long> ids, Integer status) {
        //1.创建更新的entity
        OmsOrderReturnReasonPo omsOrderReturnReasonPo = new OmsOrderReturnReasonPo();
        omsOrderReturnReasonPo.setStatus(status);
        int count = 0;
        try {
            //2.更新
            count = returnReasonDao.update(omsOrderReturnReasonPo, new QueryWrapper<OmsOrderReturnReasonPo>().in("id", ids));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return count;
    }
    /**
     * 分页查询退款原因
     *
     * @param pageSize 分页大小
     * @param pageNum  查询的分页
     * @return java.util.List<com.niu.mall.mbg.po.OmsOrderReturnReasonPo>
     * @author lihaojie
     * @date 2023/01/18 12:23
     */
    @Override
    public List<OmsOrderReturnReasonPo> list(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<OmsOrderReturnReasonPo> omsOrderReturnReasonPos = null;
        try {
            omsOrderReturnReasonPos = returnReasonDao.selectList(new QueryWrapper<>());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return omsOrderReturnReasonPos;
    }
}
