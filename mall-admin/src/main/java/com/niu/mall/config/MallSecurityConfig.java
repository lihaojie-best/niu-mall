package com.niu.mall.config;


import com.niu.mall.po.UmsResourcePo;
import com.niu.mall.service.UmsAdminService;
import com.niu.mall.service.UmsResourceService;
import com.niu.mall.security.component.DynamicSecurityService;
import com.niu.mall.security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * mall-security模块相关配置
 *
 * @author lihaojie
 * @date 2023/01/16 17:23
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MallSecurityConfig extends SecurityConfig {

    @Autowired
    private UmsAdminService adminService;
    @Resource
    private UmsResourceService resourceService;

    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息: 用户信息+资源列表
        return username -> adminService.loadUserByUsername(username);
    }


    @DependsOn("dataSource")
    @Bean
    public DynamicSecurityService dynamicSecurityService() {
        return () -> {
            Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
            List<UmsResourcePo> list = resourceService.listAll();
            list.forEach(resourcePo -> map.put(resourcePo.getUrl(), new org.springframework.security.access.SecurityConfig(resourcePo.getId() + ":" + resourcePo.getName())));
            return map;
        };
    }

}
