package com.dereck.filesys.admin.controller;

import com.dereck.filesys.admin.service.LoginService;
import com.dereck.filesys.common.entity.R;
import com.dereck.filesys.common.entity.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class AdminController {

    @Resource
    private LoginService loginService;


    /**
     * 注册用户，加密
     * @param user 请求体，包括了 用户名、密码、邮箱三个属性
     * @return 成功请求
     */
    @PostMapping("/register")
    public R register(@RequestBody User user){
        return loginService.register(user);
    }


    // todo 等待整合 SpringSecurity
    @PutMapping ("/login")
    public R login(@RequestBody User user){
        return loginService.login(user);
    }




}
