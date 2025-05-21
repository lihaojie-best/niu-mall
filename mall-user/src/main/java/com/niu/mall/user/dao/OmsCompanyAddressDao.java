package com.niu.mall.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niu.mall.user.po.OmsCompanyAddressPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 公司收发货地址表 Mapper 接口
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Mapper
public interface OmsCompanyAddressDao extends BaseMapper<OmsCompanyAddressPo> {

}
