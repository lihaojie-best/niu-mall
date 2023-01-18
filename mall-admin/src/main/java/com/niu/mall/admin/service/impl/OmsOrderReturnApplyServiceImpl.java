package com.niu.mall.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.niu.mall.admin.dao.OmsOrderReturnApplyDao;
import com.niu.mall.admin.dto.OmsOrderReturnApplyResultDto;
import com.niu.mall.admin.param.OmsReturnApplyQueryParam;
import com.niu.mall.admin.param.OmsUpdateStatusParam;
import com.niu.mall.admin.service.OmsOrderReturnApplyService;
import com.niu.mall.mbg.po.OmsOrderReturnApplyPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 订单退货申请 服务实现类
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Service
public class OmsOrderReturnApplyServiceImpl extends ServiceImpl<OmsOrderReturnApplyDao, OmsOrderReturnApplyPo> implements OmsOrderReturnApplyService {
    @Autowired
    private OmsOrderReturnApplyDao returnApplyDao;

    /**
     * 分页查询退货申请
     *
     * @param queryParam 订单退货申请查询参数
     * @param pageSize   分页大小
     * @param pageNum    当前页
     * @return java.util.List<com.niu.mall.mbg.po.OmsOrderReturnApplyPo>
     * @author lihaojie
     * @date 2023/01/17 16:52
     */
    @Override
    public List<OmsOrderReturnApplyPo> list(OmsReturnApplyQueryParam queryParam, Integer pageSize, Integer pageNum) {
        //1. 开启分页
        PageHelper.startPage(pageNum, pageSize);
        //2. 查询wrapper
        QueryWrapper<OmsOrderReturnApplyPo> whereWrapper = new QueryWrapper<>();
        if (queryParam.getId() != null) {
            whereWrapper.eq("id", queryParam.getId());
        }
        if (StrUtil.isNotEmpty(queryParam.getReceiverKeyword()) && queryParam.getReceiverKeyword() != "") {
            whereWrapper.and(objectQueryWrapper -> objectQueryWrapper.like("return_name", queryParam.getReceiverKeyword()).or().like("return_phone", queryParam.getReceiverKeyword()));
        }
        if (queryParam.getStatus() != null) {
            whereWrapper.and(objectQueryWrapper -> objectQueryWrapper.eq("status", queryParam.getStatus()));
        }
        if (StrUtil.isNotEmpty(queryParam.getCreateTime())) {
            whereWrapper.and(objectQueryWrapper -> objectQueryWrapper.like("create_time", queryParam.getCreateTime()));
        }
        if (StrUtil.isNotEmpty(queryParam.getHandleMan())) {
            whereWrapper.and(objectQueryWrapper -> objectQueryWrapper.eq("handle_man", queryParam.getHandleMan()));
        }
        if (StrUtil.isNotEmpty(queryParam.getHandleTime())) {
            whereWrapper.and(objectQueryWrapper -> objectQueryWrapper.like("handle_time", queryParam.getHandleTime()));
        }
        List<OmsOrderReturnApplyPo> omsOrderReturnApplyPos = null;
        try {
            //3. 查询所有
            omsOrderReturnApplyPos = returnApplyDao.selectList(whereWrapper);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return omsOrderReturnApplyPos;
    }

    /**
     * 批量删除退货申请
     *
     * @param ids 退货申请id
     * @return int
     * @author lihaojie
     * @date 2023/01/17 17:18
     */
    @Override
    public int delete(List<Long> ids) {
        int count = 0;
        try {
            // 删除
            count = returnApplyDao.deleteBatchIds(ids);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return count;
    }
    /**
     * 根据id查询退货申请
     *
     * @param id 退货申请id
     * @return com.niu.mall.admin.dto.OmsOrderReturnApplyResultDto
     * @author lihaojie
     * @date 2023/01/17 17:20
     */
    @Override
    public OmsOrderReturnApplyResultDto getItem(Long id) {
        OmsOrderReturnApplyResultDto orderReturnApplyResultDto = null;
        try {
            orderReturnApplyResultDto = returnApplyDao.getDetail(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return orderReturnApplyResultDto;
    }
    /**
     * 修改退货申请状态
     *
     * @param id 退货申请id
     * @param statusParam 申请状态
     * @return int
     * @author lihaojie
     * @date 2023/01/17 17:39
     */
    @Override
    public int updateStatus(Long id, OmsUpdateStatusParam statusParam) {
        //1.获取
        Integer status = statusParam.getStatus();
        OmsOrderReturnApplyPo returnApply = new OmsOrderReturnApplyPo();
        //根据申请状态分类
        if(status.equals(1)){
            //确认退货
            returnApply.setId(id);
            returnApply.setStatus(1);
            returnApply.setReturnAmount(statusParam.getReturnAmount());
            returnApply.setCompanyAddressId(statusParam.getCompanyAddressId());
            returnApply.setHandleTime(new Date());
            returnApply.setHandleMan(statusParam.getHandleMan());
            returnApply.setHandleNote(statusParam.getHandleNote());
        }else if(status.equals(2)){
            //完成退货
            returnApply.setId(id);
            returnApply.setStatus(2);
            returnApply.setReceiveTime(new Date());
            returnApply.setReceiveMan(statusParam.getReceiveMan());
            returnApply.setReceiveNote(statusParam.getReceiveNote());
        }else if(status.equals(3)){
            //拒绝退货
            returnApply.setId(id);
            returnApply.setStatus(3);
            returnApply.setHandleTime(new Date());
            returnApply.setHandleMan(statusParam.getHandleMan());
            returnApply.setHandleNote(statusParam.getHandleNote());
        }else{
            return 0;
        }
        int count = 0;
        try {
            //更新
            count = returnApplyDao.updateById(returnApply);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return count;
    }
}
