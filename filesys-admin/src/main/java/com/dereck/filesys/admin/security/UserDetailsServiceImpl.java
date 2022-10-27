package com.dereck.filesys.admin.security;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.dereck.filesys.admin.service.UserAdminService;
import com.dereck.filesys.common.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    UserAdminService userAdminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryChainWrapper<User> wrapper = userAdminService.lambdaQuery().eq(User::getName, username);
        User loginUser = userAdminService.getOne(wrapper);
        // 没有找到该用户
        if(ObjectUtil.isNull(loginUser)){
            throw new UsernameNotFoundException("没有此用户");
        }
        // todo 添加用户权限到 LoginUser中


        return new LoginUser(loginUser);
    }
}
