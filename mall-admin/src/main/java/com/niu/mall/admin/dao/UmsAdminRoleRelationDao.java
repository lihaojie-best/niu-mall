package com.niu.mall.admin.dao;

import com.niu.mall.mbg.po.UmsAdminRoleRelationPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niu.mall.mbg.po.UmsResourcePo;
import com.niu.mall.mbg.po.UmsRolePo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 后台用户和角色关系表 Mapper 接口
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Mapper
public interface UmsAdminRoleRelationDao extends BaseMapper<UmsAdminRoleRelationPo> {

    /**
     * 获取资源相关用户ID列表
     */
    List<UmsResourcePo> getResourceList(@Param("adminId")Long id);

    List<UmsRolePo> getRoleList(@Param("adminId") Long id);
    /**
     * 批量插入
     *
     * @param list
     * @return void
     * @author lihaojie
     * @date 2022/12/30 23:14
     */
    void insertList(@Param("list")List<UmsAdminRoleRelationPo> list);
}
