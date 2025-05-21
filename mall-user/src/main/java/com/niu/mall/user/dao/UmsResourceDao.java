package com.niu.mall.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niu.mall.user.po.UmsResourcePo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 后台资源表 Mapper 接口
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Mapper
public interface UmsResourceDao extends BaseMapper<UmsResourcePo> {
    /**
     * 获取全部
     *
     * @return java.util.List<UmsResourcePo>
     * @author lihaojie
     * @date 2023/01/16 17:16
     */
    List<UmsResourcePo> getAll();

}
