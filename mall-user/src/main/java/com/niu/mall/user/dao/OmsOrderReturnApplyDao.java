package com.niu.mall.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niu.mall.user.dto.OmsOrderReturnApplyResultDto;
import com.niu.mall.user.po.OmsOrderReturnApplyPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 订单退货申请 Mapper 接口
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Mapper
public interface OmsOrderReturnApplyDao extends BaseMapper<OmsOrderReturnApplyPo> {
    /**
     * 查询退货详情
     * 
     * @param id 退货申请id
     * @return OmsOrderReturnApplyResultDto
     * @author lihaojie
     * @date 2023/01/17 17:28
     */
    OmsOrderReturnApplyResultDto getDetail(@Param("id") Long id);
}
