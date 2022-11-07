package com.dereck.filesys.file.filter;


import cn.hutool.json.JSONUtil;
import com.dereck.filesys.common.constant.RedisConstant;
import com.dereck.filesys.common.entity.StatusVar;
import com.dereck.filesys.common.entity.User;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LoginFilter extends OncePerRequestFilter {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        // 先检查token有没有，再检查token是否过期
        if(token == null || stringRedisTemplate.hasKey(token)){
            response.setStatus(500);
            return;
        }
        // 存到线程中
        User user = JSONUtil.toBean(token, User.class);
        if(!StatusVar.hasUser())
            StatusVar.saveUser(user);
        // 更新token的数据
        stringRedisTemplate.expire(token, RedisConstant.USER_LOGIN_TTL, TimeUnit.MINUTES);
        // 放行
        filterChain.doFilter(request,response);
    }
}
