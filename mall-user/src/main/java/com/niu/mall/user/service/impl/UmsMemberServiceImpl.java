package com.niu.mall.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.niu.mall.common.exception.Asserts;
import com.niu.mall.user.dao.UmsMemberDao;
import com.niu.mall.user.dao.UmsMemberLevelDao;
import com.niu.mall.user.domain.MemberDetails;
import com.niu.mall.user.po.UmsMemberLevelPo;
import com.niu.mall.user.po.UmsMemberPo;
import com.niu.mall.security.util.JwtTokenUtil;
import com.niu.mall.user.service.UmsMemberCacheService;
import com.niu.mall.user.service.UmsMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 会员管理Service实现类
 * Created by lihaojie on 2023/8/3.
 */
@Service
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberDao,UmsMemberPo> implements UmsMemberService {
    private static final Logger LGGER = LoggerFactory.getLogger(UmsMemberServiceImpl.class);
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UmsMemberDao memberDao;
    @Autowired
    private UmsMemberLevelDao memberLevelMapper;
    @Autowired
    private UmsMemberCacheService memberCacheService;
    @Value("${redis.key.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    @Override
    public UmsMemberPo getByUsername(String username) {
        UmsMemberPo member = memberCacheService.getMember(username);
        if(member!=null) return member;
        List<UmsMemberPo> memberList = memberDao.selectList(new QueryWrapper<UmsMemberPo>().eq("username", username ));
        if (!CollectionUtils.isEmpty(memberList)) {
            member = memberList.get(0);
            memberCacheService.setMember(member);
            return member;
        }
        return null;
    }

    @Override
    public UmsMemberPo getById(Long id) {
       return memberDao.selectOne(new QueryWrapper<UmsMemberPo>().eq("id", id));
    }


    @Override
    public void register(String username, String password, String telephone, String authCode) {
        //验证验证码
        if(!verifyAuthCode(authCode,telephone)){
            Asserts.fail("验证码错误");
        }
        //查询是否已有该用户
        QueryWrapper<UmsMemberPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username).or().eq("phone", telephone);
        List<UmsMemberPo> umsMembers = memberDao.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(umsMembers)) {
            Asserts.fail("该用户已经存在");
        }
        //没有该用户进行添加操作
        UmsMemberPo umsMember = new UmsMemberPo();
        umsMember.setUsername(username);
        umsMember.setPhone(telephone);
        umsMember.setPassword(passwordEncoder.encode(password));
        umsMember.setCreateTime(new Date());
        umsMember.setStatus(1);
        //获取默认会员等级并设置
        QueryWrapper<UmsMemberLevelPo> levelWrapper = new QueryWrapper<>();
        levelWrapper.eq("default_status", 1);
        List<UmsMemberLevelPo> memberLevelList = memberLevelMapper.selectList(levelWrapper);

        if (!CollectionUtils.isEmpty(memberLevelList)) {
            umsMember.setMemberLevelId(memberLevelList.get(0).getId());
        }

        memberDao.insert(umsMember);
        umsMember.setPassword(null);
    }

    @Override
    public String generateAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i=0;i<6;i++){
            sb.append(random.nextInt(10));
        }
        memberCacheService.setAuthCode(telephone,sb.toString());
        return sb.toString();
    }

    @Override
    public void updatePassword(String telephone, String password, String authCode) {
        QueryWrapper<UmsMemberPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", telephone);
        List<UmsMemberPo> memberList = memberDao.selectList(queryWrapper);
        if(CollectionUtils.isEmpty(memberList)){
            Asserts.fail("该账号不存在");
        }
        //验证验证码
        if(!verifyAuthCode(authCode,telephone)){
            Asserts.fail("验证码错误");
        }
        UmsMemberPo umsMember = memberList.get(0);
        umsMember.setPassword(passwordEncoder.encode(password));
        memberDao.updateById(umsMember);
        memberCacheService.delMember(umsMember.getId());
    }

    /**
     * 获取当前登录的会员信息
     *
     * 该方法用于获取当前安全上下文中认证通过的会员详细信息它首先获取当前的安全上下文，
     * 然后提取出认证信息，并将认证信息中的主体（Principal）转换为MemberDetails类型，
     * 最后返回该类型的UmsMemberPo对象，即会员实体
     *
     * @return UmsMemberPo 当前登录的会员实体信息如果未认证或认证信息异常，则返回null
     */
    @Override
    public UmsMemberPo getCurrentMember() {
        // 获取当前的安全上下文
        SecurityContext ctx = SecurityContextHolder.getContext();
        // 从安全上下文中获取认证信息
        Authentication auth = ctx.getAuthentication();
        // 将认证信息中的主体转换为会员详细信息对象
        MemberDetails memberDetails = (MemberDetails) auth.getPrincipal();
        // 返回会员实体
        return memberDetails.getUmsMember();
    }


    @Override
    public void updateIntegration(Long id, Integer integration) {
        UmsMemberPo record=new UmsMemberPo();
        record.setId(id);
        record.setIntegration(integration);
        memberDao.updateById(record);
        memberCacheService.delMember(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UmsMemberPo member = getByUsername(username);
        if(member!=null){
            return new MemberDetails(member);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    /**
     * 用户登录方法
     *
     * @param username 用户名，需要与数据库中存储的用户名匹配
     * @param password 密码，需要与数据库中存储的密码匹配
     * @return 登录成功后返回的令牌，用于后续的授权验证
     *
     * 注意：密码需要客户端加密后传递，以增强安全性
     */
    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            //加载用户信息，根据用户名获取用户详情
            UserDetails userDetails = loadUserByUsername(username);
            //比对用户输入的密码与数据库中存储的密码是否匹配
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                //如果密码不正确，抛出异常
                throw new BadCredentialsException("密码不正确");
            }
            //创建认证对象，参数分别为用户详情、凭证和用户权限
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            //将认证信息存储到SecurityContextHolder中，以供后续使用
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //生成令牌并返回
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            //记录登录异常信息
            LGGER.warn("登录异常:{}", e.getMessage());
        }
        //返回生成的令牌
        return token;
    }


    @Override
    public String refreshToken(String token) {
        return jwtTokenUtil.refreshHeadToken(token);
    }

    //对输入的验证码进行校验
    private boolean verifyAuthCode(String authCode, String telephone){
        if(StringUtils.isEmpty(authCode)){
            return false;
        }
        String realAuthCode = memberCacheService.getAuthCode(telephone);
        return authCode.equals(realAuthCode);
    }

}
