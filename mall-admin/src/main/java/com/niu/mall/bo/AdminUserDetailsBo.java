package com.niu.mall.bo;


import com.niu.mall.po.UmsAdminPo;
import com.niu.mall.po.UmsResourcePo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import java.util.List;
import java.util.stream.Collectors;

/**
 * SpringSecurity需要的用户详情
 *
 * @author lihaojie
 * @date 2023/01/02 14:46
 **/
public class AdminUserDetailsBo implements UserDetails {
    //后台用户
    private UmsAdminPo umsAdmin;
    //拥有资源列表
    private List<UmsResourcePo> resourceList;
    public AdminUserDetailsBo(UmsAdminPo umsAdmin, List<UmsResourcePo> resourceList) {
        this.umsAdmin = umsAdmin;
        this.resourceList = resourceList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的角色
        return resourceList.stream()
                .map(role ->new SimpleGrantedAuthority(role.getId()+":"+role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return umsAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return umsAdmin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return umsAdmin.getStatus().equals(1);
    }
}
