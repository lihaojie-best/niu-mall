package com.niu.mall.admin.service.impl;

import com.niu.mall.mbg.po.UmsMenuPo;
import com.niu.mall.mbg.po.UmsRolePo;
import com.niu.mall.admin.dao.UmsRoleDao;
import com.niu.mall.admin.service.UmsRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 服务实现类
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Service
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleDao, UmsRolePo> implements UmsRoleService {
    @Autowired
    private UmsRoleDao umsRoleDao;
    /**
     * 获取后台菜单列表
     *
     * @param id
     * @return java.lang.Object
     * @author lihaojie
     * @date 2022/12/30 20:51
     */
    @Override
    public List<UmsMenuPo> getMenuList(Long id) {
        return umsRoleDao.getMenuList(id);
    }
}
