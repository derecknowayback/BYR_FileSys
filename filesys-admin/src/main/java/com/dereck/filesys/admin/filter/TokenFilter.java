package com.dereck.filesys.admin.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.dereck.filesys.admin.security.LoginUser;
import com.dereck.filesys.common.constant.RedisConstant;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class TokenFilter extends OncePerRequestFilter {

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        // 没有用户，放行，让后面的Security自带的过滤器报错
        if(StrUtil.isEmpty(token)){
            filterChain.doFilter(request,response);
            return;
        }
        // 有了token,查下redis和数据库
        String userJson = stringRedisTemplate.opsForValue().get(token);
        // 如果已经登录了
        if(!StrUtil.isEmpty(userJson)){
            // 确实有这个token，那我们就刷新它，默认刷新时间30分钟
            stringRedisTemplate.expire(token, RedisConstant.USER_LOGIN_TTL, TimeUnit.MINUTES);
            // 封装Authentication对象
            LoginUser loginUser = JSONUtil.toBean(userJson, LoginUser.class);
            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(loginUser, null, null);
            // 存入SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request,response);
    }

}
