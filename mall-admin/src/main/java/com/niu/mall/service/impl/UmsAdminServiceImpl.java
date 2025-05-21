package com.niu.mall.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.niu.mall.bo.AdminUserDetailsBo;
import com.niu.mall.common.exception.Asserts;
import com.niu.mall.common.util.RequestUtil;
import com.niu.mall.dao.UmsAdminDao;
import com.niu.mall.dao.UmsAdminLoginLogDao;
import com.niu.mall.dao.UmsAdminRoleRelationDao;
import com.niu.mall.param.UmsAdminLoginParam;
import com.niu.mall.param.UmsAdminParam;
import com.niu.mall.param.UmsUpdateAdminPasswordParam;
import com.niu.mall.po.*;
import com.niu.mall.security.util.JwtTokenUtil;
import com.niu.mall.security.util.SpringUtil;
import com.niu.mall.service.UmsAdminCacheService;
import com.niu.mall.service.UmsAdminService;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 后台用户表 服务实现类
 *
 * @author lihaojie
 * @date 2022/12/29 13:56
 **/
@Service
public class UmsAdminServiceImpl extends ServiceImpl<UmsAdminDao, UmsAdminPo> implements UmsAdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);
    @Autowired
    private UmsAdminDao adminDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;
    @Autowired
    private UmsAdminLoginLogDao loginLogDao;

    /**
     * 用户注册
     *
     * @param umsAdminParam 用户注册参数
     * @return UmsAdminPo
     * @author lihaojie
     * @date 2022/12/21 21:28
     */
    @Override
    public UmsAdminPo register(UmsAdminParam umsAdminParam) {
        //1.param --转换--》po
        UmsAdminPo umsAdminPo = new UmsAdminPo();
        BeanUtils.copyProperties(umsAdminParam, umsAdminPo);
        //2.补全创建时间 和 用户状态
        umsAdminPo.setCreateTime(new Date()).setStatus(1);
        //3.查询是否有相同用户名的用户
        QueryWrapper<UmsAdminPo> whereWrapper = new QueryWrapper<UmsAdminPo>().eq("user_name", umsAdminPo.getUsername());
        List<UmsAdminPo> list = adminDao.selectList(whereWrapper);
        if (list.size() > 0) {
            return null;
        }
        //4.密码加密并插入
        String encodePassword = passwordEncoder.encode(umsAdminParam.getPassword());
        umsAdminPo.setPassword(encodePassword);
        int count = adminDao.insert(umsAdminPo);
        //5.判断是否插入
        if (count > 0) {
            return umsAdminPo;
        } else {
            return null;
        }

    }

    /**
     * 用户登录并返回token
     *
     * @param umsAdminLoginParam 用户登录参数
     * @return java.lang.String
     * @author lihaojie
     * @date 2022/12/29 15:17
     */
    @Override
    public String login(UmsAdminLoginParam umsAdminLoginParam) {
        String token = null;
        try {
            //根据用户名获取UserDetails(用户登录信息）
            UserDetails userDetails = loadUserByUsername(umsAdminLoginParam.getUsername());
            //判断密码是否匹配 umsAdminLoginParam明文   明文在前，加密的在后
            if (!passwordEncoder.matches(umsAdminLoginParam.getPassword(),userDetails.getPassword())) {
                Asserts.fail("密码错误");
            }
            if (!userDetails.isEnabled()) {
                Asserts.fail("用户已被禁用");
            }
            //设置token
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            //设置认证token
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            token = jwtTokenUtil.generateToken(userDetails);
            //新增登入记录
            insertLoginLog(umsAdminLoginParam.getUsername());
        } catch (Exception e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }
    //内部方法

    /**
     * 新增登录日志
     *
     * @param username 用户名
     * @author lihaojie
     * @date 2022/12/29 21:11
     */
    private void insertLoginLog(String username) {
        UmsAdminPo admin = getAdminByUsername(username);
        if (admin == null) return;
        UmsAdminLoginLogPo loginLog = new UmsAdminLoginLogPo();
        loginLog.setAdminId(admin.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(RequestUtil.getRequestIp(request));
        loginLogDao.insert(loginLog);
    }

    @Override
    public UmsAdminPo getAdminByUsername(String username) {
        //1.先从缓存中查找
        UmsAdminPo admin = getCacheService().getAdmin(username);
        //2.如果找到了就返回
        if (admin != null) return admin;
        //3.没有找到就从数据库获取
        List<UmsAdminPo> adminList = adminDao.selectList(new QueryWrapper<UmsAdminPo>().eq("username", username));
        if (adminList != null && adminList.size() > 0) {
            admin = adminList.get(0);
            //将数据库中的数据存入缓存中
            getCacheService().setAdmin(admin);
            return admin;
        }
        return null;
    }

    @Override
    public List<UmsRolePo> getRoleList(Long id) {
        return adminRoleRelationDao.getRoleList(id);
    }

    /**
     * 根据用户名或姓名模糊分页查询
     *
     * @param keyword  关键字
     * @param pageSize 分页大小
     * @param pageNum  当前页
     * @return java.util.List<UmsAdminPo>
     * @author lihaojie
     * @date 2022/12/30 21:33
     */
    @Override
    public List<UmsAdminPo> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        QueryWrapper<UmsAdminPo> whereWrapper = new QueryWrapper<>();
        if (!StringUtil.isNullOrEmpty(keyword)) {
            whereWrapper.like("username", keyword).or().like("nick_name", keyword);
        }
        return adminDao.selectList(whereWrapper);
    }
    /**
     * 更新用户信息
     *
     * @param id adminId
     * @param adminPo 更新参数实体类
     * @return int
     * @author lihaojie
     * @date 2022/12/30 23:03
     */
    @Override
    public int update(Long id, UmsAdminPo adminPo) {
        adminPo.setId(id);
        //1.根据id获取原先数据
        UmsAdminPo umsAdminPoDb = adminDao.selectOne(new QueryWrapper<UmsAdminPo>().eq("id", id));
        //2.对比密码时候相同,不同就需要将密码编码后插入 相同就不需要修改密码,将password字段设置为null
        if(!adminPo.getPassword().isEmpty()){
            //密码不为空则需要修改密码
            if(umsAdminPoDb.getPassword().equals(adminPo.getPassword())){
                //如果相同则不需要修改
                adminPo.setPassword(null);
            }else {
                //需要修改.则先需要编码
                adminPo.setPassword(passwordEncoder.encode(adminPo.getPassword()));
            }
        }
        int count = 0;
        //try catch 一下插入操作
        try {
            count = adminDao.updateById(adminPo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return count;
    }
    /**
     * 修改用户密码
     *
     * @param updateAdminPasswordParam
     * @return int
     * @author lihaojie
     * @date 2022/12/30 22:48
     */
    @Override
    public int updatePassword(UmsUpdateAdminPasswordParam updateAdminPasswordParam) {
        //1.判断param是否都在
        if(StrUtil.isEmpty(updateAdminPasswordParam.getUsername())
                ||StrUtil.isEmpty(updateAdminPasswordParam.getOldPassword())
                ||StrUtil.isEmpty(updateAdminPasswordParam.getNewPassword())){
            return -1;
        }
        int count = 0;
        try {

            //2.根据用户名获取oldDb 如果没查询到则返回-2
            UmsAdminPo umsAdminPoDb = adminDao.selectOne(new QueryWrapper<UmsAdminPo>().eq("username", updateAdminPasswordParam.getUsername()));
            if (umsAdminPoDb==null){
                return -2;
            }
            //3.对比旧密码是否正确 不正确返回
            if(passwordEncoder.matches(umsAdminPoDb.getPassword(), updateAdminPasswordParam.getOldPassword())){
                return -3;
            }
            //4. 更新数据
            UmsAdminPo umsAdminPo = new UmsAdminPo().setPassword(passwordEncoder.encode(updateAdminPasswordParam.getNewPassword())).setId(umsAdminPoDb.getId());
            count = adminDao.updateById(umsAdminPo);
            //5.删除缓存
            getCacheService().delAdmin(umsAdminPoDb.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return count;
    }
    /**
     * 根据id删除用户
     *
     * @param id adminId
     * @return int
     * @author lihaojie
     * @date 2022/12/30 22:56
     */
    @Override
    public int delete(Long id) {
        int count = 0;
        try {
            //删除用户
            count = adminDao.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //成功删除则删除缓存
        getCacheService().delAdmin(id);
        getCacheService().delResourceList(id);
        return count;

    }

    @Override
    public int updateRole(Long adminId, List<Long> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();
        //先删除原来的关系
        adminRoleRelationDao.delete(new QueryWrapper<UmsAdminRoleRelationPo>().eq("adminId",adminId));
        //建立新关系
        if (!CollectionUtils.isEmpty(roleIds)) {
            List<UmsAdminRoleRelationPo> list = new ArrayList<>();
            for (Long roleId : roleIds) {
                UmsAdminRoleRelationPo roleRelation = new UmsAdminRoleRelationPo();
                roleRelation.setAdminId(adminId);
                roleRelation.setRoleId(roleId);
                list.add(roleRelation);
            }
            adminRoleRelationDao.insertList(list);
        }
        getCacheService().delResourceList(adminId);
        return count;
    }


    /**
     * 根据用户名获取UserDetails
     *
     * @param username 用户名
     * @return org.springframework.security.core.userdetails.UserDetails
     * @author lihaojie
     * @date 2022/12/29 15:53
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        //根据用户名获取用户信息
        UmsAdminPo umsAdminPo = adminDao.selectOne(new QueryWrapper<UmsAdminPo>().eq("username", username));
        if (umsAdminPo != null) {
            //入股数据库有此用户,则根据用户id查询资源列表
            List<UmsResourcePo> resourceList = getResourceList(umsAdminPo.getId());
            return new AdminUserDetailsBo(umsAdminPo, resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    private List<UmsResourcePo> getResourceList(Long id) {
        // 1.先从缓存中获取用户资源列表
        List<UmsResourcePo> resourceList = getCacheService().getResourceList(id);
        if (CollUtil.isNotEmpty(resourceList)) {
            return resourceList;
        }
        // 2.没有则在数据库中获取
        resourceList = adminRoleRelationDao.getResourceList(id);
        if (CollUtil.isNotEmpty(resourceList)) {
            getCacheService().setResourceList(id, resourceList);
        }
        return resourceList;
    }

    @Override
    public UmsAdminCacheService getCacheService() {
        return SpringUtil.getBean(UmsAdminCacheService.class);
    }

    /**
     * 刷新token
     *
     * @param oldToken
     * @return java.lang.String
     * @author lihaojie
     * @date 2022/12/30 19:11
     */
    @Override
    public String refreshToken(String oldToken) {
        return jwtTokenUtil.refreshHeadToken(oldToken);
    }
}
