package com.dereck.filesys.admin.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.json.JSONUtil;
import com.dereck.filesys.admin.security.LoginUser;
import com.dereck.filesys.admin.service.LoginService;
import com.dereck.filesys.admin.service.UserService;
import com.dereck.filesys.common.entity.R;
import com.dereck.filesys.common.entity.User;
import com.dereck.filesys.common.constant.RedisConstant;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserService userService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private AuthenticationManager authenticationManager;



    public R login(User user){
        // 我们拿到授权了
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword(), null);
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        // 验证失败，没有这样的用户（实际上是在  LoginUser类中实现的）
        if(ObjectUtil.isNull(authenticate)){
            return R.fail("验证失败，没有此用户",HttpStatus.BAD_REQUEST);
        }
        // 验证成功，确实有这样的用户，上Redis
        // todo 会不会有重复数据？？？
        LoginUser loginUser = (LoginUser)authenticate.getPrincipal();
        String jsonStr = JSONUtil.toJsonStr(user);
        stringRedisTemplate.opsForValue().set(RedisConstant.LOGIN +":" +user.getName(),jsonStr );

        return R.ok();

    }

    public R register(User user){
        String name = user.getName();
        String password = user.getPassword();
        String email = user.getEmail();
        if(name == null || name.length() > 20){
            return R.fail("用户名长度应该在1~20个字之间", HttpStatus.BAD_REQUEST);
        }
        else if(!ReUtil.isMatch("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$",password)){
            return R.fail("密码必须包含大小写字母和数字的组合，可以使用特殊字符，长度在8-10之间)",HttpStatus.BAD_REQUEST);
        }
        else if(!ReUtil.isMatch("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$",email)) {
            return R.fail("邮箱格式不正确", HttpStatus.BAD_REQUEST);
        }
        userService.save(new User(name,email,password));
        return R.ok();
    }


}
