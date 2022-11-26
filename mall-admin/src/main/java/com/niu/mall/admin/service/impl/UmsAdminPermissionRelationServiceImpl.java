package com.niu.mall.admin.service.impl;

import com.niu.mall.mbg.po.UmsAdminPermissionRelationPo;
import com.niu.mall.admin.dao.UmsAdminPermissionRelationDao;
import com.niu.mall.admin.service.UmsAdminPermissionRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户和权限关系表(除角色中定义的权限以外的加减权限) 服务实现类
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Service
public class UmsAdminPermissionRelationServiceImpl extends ServiceImpl<UmsAdminPermissionRelationDao, UmsAdminPermissionRelationPo> implements UmsAdminPermissionRelationService {

}
