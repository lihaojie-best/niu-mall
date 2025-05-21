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
 * Created by macro on 2018/8/3.
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

    @Override
    public UmsMemberPo getCurrentMember() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        MemberDetails memberDetails = (MemberDetails) auth.getPrincipal();
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

    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            LGGER.warn("登录异常:{}", e.getMessage());
        }
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
