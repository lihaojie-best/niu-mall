package com.niu.mall.security.component;

import com.niu.mall.security.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT登录授权过滤器
 * Created by lihaojie on 2023/4/26.
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        //1. 获取token 如何获取? 现根据头获取
        String authHeader = request.getHeader(this.tokenHeader);
        //2.验证是否携带token 如果携带token再判断是否以配置的tokenHead开头
        if (authHeader != null && authHeader.startsWith(this.tokenHead)) {
            //3. 获取真正的token(去掉统一的token开头)
            String authToken = authHeader.substring(this.tokenHead.length());// The part after "Bearer "
            //4. 通过token解析出username
            String username = jwtTokenUtil.getUserNameFromToken(authToken);
            //5.控制台日志输出用户名
            LOGGER.info("checking username:{}", username);
            //6. 用户名不为空且安全上下文没有认证信息
            //安全上下文没有认证信息不为空则可能是伪造的
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                //7. 根据username获取用户信息
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                //8. 判断token是否正确
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    //9. 设置authentication
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    LOGGER.info("authenticated user:{}", username);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }
}
