package com.niu.mall.admin.service;

import com.niu.mall.admin.dto.OmsOrderReturnApplyResultDto;
import com.niu.mall.admin.param.OmsReturnApplyQueryParam;
import com.niu.mall.admin.param.OmsUpdateStatusParam;
import com.niu.mall.mbg.po.OmsOrderReturnApplyPo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 订单退货申请 服务类
 *
 * @author lihaojie
 * @date 2023/01/17 16:52
 **/
public interface OmsOrderReturnApplyService extends IService<OmsOrderReturnApplyPo> {
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
    List<OmsOrderReturnApplyPo> list(OmsReturnApplyQueryParam queryParam, Integer pageSize, Integer pageNum);

    /**
     * 批量删除退货申请
     *
     * @param ids 退货申请id
     * @return int
     * @author lihaojie
     * @date 2023/01/17 17:18
     */
    int delete(List<Long> ids);

    /**
     * 根据id查询退货申请
     *
     * @param id 退货申请id
     * @return com.niu.mall.admin.dto.OmsOrderReturnApplyResultDto
     * @author lihaojie
     * @date 2023/01/17 17:20
     */
    OmsOrderReturnApplyResultDto getItem(Long id);

    /**
     * 修改退货申请状态
     *
     * @param id 退货申请id
     * @param statusParam 申请状态
     * @return int
     * @author lihaojie
     * @date 2023/01/17 17:39
     */
    int updateStatus(Long id, OmsUpdateStatusParam statusParam);

}
