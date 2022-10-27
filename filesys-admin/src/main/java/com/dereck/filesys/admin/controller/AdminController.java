package com.dereck.filesys.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.dereck.filesys.admin.service.UserAdminService;
import com.dereck.filesys.common.entity.R;
import com.dereck.filesys.common.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class AdminController {
    @Resource
    private UserAdminService userAdminService;


    /**
     * 注册用户，加密
     * @param requestBody 请求体，包括了 用户名、密码、邮箱三个属性
     * @return 成功请求
     */
    @PostMapping("/signup")
    public R signUp(@RequestBody JSONObject requestBody){
        // todo 判断合法参数是否合法

        String name = requestBody.getString("name");
        String password = requestBody.getString("password");
        String email = requestBody.getString("email");
        userAdminService.save(new User(name,email,password));
        return R.ok();
    }


    // todo 等待整合 SpringSecurity
    @PutMapping ("/signin")
    public R signIn(@RequestBody JSONObject requestBody){
        String name = requestBody.getString("name");
        String password = requestBody.getString("password");
        // todo 先去redis中找
        if(false){

        }
        // 没找到就去mysql中找
        else{
            User queryRes = userAdminService.query().select("u_password").eq("u_name", name).getEntity();
            if(queryRes == null || queryRes.getPassword() == null) return R.fail("用户"+name+"不存在，请注册。",HttpStatus.UNAUTHORIZED);
            if(!queryRes.getPassword().equals(password)) return R.fail("密码错误，请重新输入",HttpStatus.UNAUTHORIZED);
            return R.ok();
        }
        return null;
    }




}
