package com.niu.mall.admin.service;

import com.niu.mall.mbg.po.OmsOrderReturnReasonPo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 退货原因表 服务类
 *
 * @author lihaojie
 * @date 2023/01/17 17:51
 **/
public interface OmsOrderReturnReasonService extends IService<OmsOrderReturnReasonPo> {
    /**
     * 添加退货原因
     *
     * @param returnReason 退货原因表
     * @return int
     * @author lihaojie
     * @date 2023/01/17 17:51
     */
    int create(OmsOrderReturnReasonPo returnReason);
    /**
     * 修改退货原因
     *
     * @param id 退货原因id
     * @param returnReason 退货原因参数
     * @return int
     * @author lihaojie
     * @date 2023/01/17 17:57
     */
    int update(Long id, OmsOrderReturnReasonPo returnReason);
    /**
     * 批量删除
     *
     * @param ids id集合
     * @return int
     * @author lihaojie
     * @date 2023/01/17 20:57
     */
    int delete(List<Long> ids);
    /**
     * 根据id查询
     *
     * @param id  id
     * @return com.niu.mall.mbg.po.OmsOrderReturnReasonPo
     * @author lihaojie
     * @date 2023/01/17 21:04
     */
    OmsOrderReturnReasonPo getItem(Long id);
    /**
     * 根据id修改退款申请状态
     *
     * @param ids ids
     * @param status 状态
     * @return int
     * @author lihaojie
     * @date 2023/01/18 12:19
     */
    int updateStatus(List<Long> ids, Integer status);
    /**
     * 分页查询退款原因
     *
     * @param pageSize 分页大小
     * @param pageNum  查询的分页
     * @return java.util.List<com.niu.mall.mbg.po.OmsOrderReturnReasonPo>
     * @author lihaojie
     * @date 2023/01/18 12:23
     */
    List<OmsOrderReturnReasonPo> list(Integer pageSize, Integer pageNum);
}
