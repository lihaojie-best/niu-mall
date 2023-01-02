package com.niu.mall.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niu.mall.admin.dao.UmsAdminRoleRelationDao;
import com.niu.mall.admin.service.UmsAdminCacheService;
import com.niu.mall.admin.service.UmsAdminService;
import com.niu.mall.common.service.RedisService;
import com.niu.mall.mbg.po.UmsAdminPo;
import com.niu.mall.mbg.po.UmsAdminRoleRelationPo;
import com.niu.mall.mbg.po.UmsResourcePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 后台用户缓存操作Service
 *
 * @author lihaojie
 * @date 2022/12/29 19:22
 **/
@Service
public class UmsAdminCacheServiceImpl implements UmsAdminCacheService {
    @Autowired
    private UmsAdminService adminService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.key.admin}")
    private String REDIS_KEY_ADMIN;
    @Value("${redis.key.resourceList}")
    private String REDIS_KEY_RESOURCE_LIST;
    /**
     * 删除后台用户缓存
     *
     * @param adminId adminId
     * @return void
     * @author lihaojie
     * @date 2022/12/29 19:24
     */
    @Override
    public void delAdmin(Long adminId) {
        //根据id获取UmsAdminPo
        UmsAdminPo umsAdminPo = adminService.getById(adminId);
        if(umsAdminPo!=null){
            //生成redis的key
            String key=REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + umsAdminPo.getUsername();
            //通过redisService删除
            redisService.del(key);
        }
    }
    /**
     * 删除后台用户资源列表缓存
     */
    @Override
    public void delResourceList(Long adminId) {
        String key =REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
        redisService.del(key);
    }
    /**
     * 当角色资源发生改变时删除缓存
     */
    @Override
    public void delResourceListByRole(Long roleId) {
        //1.根据角色id才校色关系表中获取所有相关校色的用户集合
        List<UmsAdminRoleRelationPo> adminRoleRelationPoList = adminRoleRelationDao.selectList(new QueryWrapper<UmsAdminRoleRelationPo>().eq("role_id", roleId));
        //2. redis中key的前缀
        String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
        //3.将再角色关系列表中获取的集合转为redis的key集合
        List<String> keys = adminRoleRelationPoList.stream().map(umsAdminRoleRelationPo -> keyPrefix + umsAdminRoleRelationPo.getAdminId() ).collect(Collectors.toList());
        //调用缓存服务删除缓存
        redisService.del(keys);
    }
    /**
     * 当角色相关资源信息改变时删除相关后台用户缓存
     */
    @Override
    public void delResourceListByRoleIds(List<Long> roleIds) {
        //1.获取umsAdminRoleRelationPoList
        List<UmsAdminRoleRelationPo> umsAdminRoleRelationPoList = adminRoleRelationDao.selectBatchIds(roleIds);
        if (CollUtil.isNotEmpty(umsAdminRoleRelationPoList)) {
            //2. keyPrefix
            String keyPrefix =  REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            //3.umsAdminRoleRelationPoList转换keylist
            List<String> keyList = umsAdminRoleRelationPoList.stream().map(umsAdminRoleRelationPo -> keyPrefix + umsAdminRoleRelationPo.getAdminId()).collect(Collectors.toList());
            //4.删除
            redisService.del(keyList);
        }
    }
    /**
     * 当资源信息改变时，删除资源项目后台用户缓存
     */
    @Override
    public void delResourceListByResource(Long resourceId) {
       //获取umsAdminRoleRelationPoList
        List<UmsAdminRoleRelationPo> umsAdminRoleRelationPoList = adminRoleRelationDao.selectList(new QueryWrapper<UmsAdminRoleRelationPo>().eq("resource_id", resourceId));
        //将umsAdminRoleRelationPoList转化为adminId
        List<Long> adminIdList = umsAdminRoleRelationPoList.stream().map(UmsAdminRoleRelationPo::getAdminId).collect(Collectors.toList());
        //如果不为空，就执行删除操作
        if (CollUtil.isNotEmpty(adminIdList)) {
            String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            List<String> keys = adminIdList.stream().map(adminId -> keyPrefix + adminId).collect(Collectors.toList());
            redisService.del(keys);
        }
    }
    /**
     * 获取缓存后台用户信息
     */
    @Override
    public UmsAdminPo getAdmin(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + username;
        return (UmsAdminPo) redisService.get(key);
    }
    /**
     * 设置缓存后台用户信息
     */
    @Override
    public void setAdmin(UmsAdminPo admin) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + admin.getUsername();
        redisService.set(key,admin,REDIS_EXPIRE);

    }
    /**
     * 获取缓存后台用户资源列表
     */
    @Override
    public List<UmsResourcePo> getResourceList(Long adminId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
        return (List<UmsResourcePo>) redisService.get(key);
    }
    /**
     * 设置缓存后台用户资源列表
     */
    @Override
    public void setResourceList(Long adminId, List<UmsResourcePo> resourceList) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
        redisService.set(key,resourceList,REDIS_EXPIRE);
    }
}
