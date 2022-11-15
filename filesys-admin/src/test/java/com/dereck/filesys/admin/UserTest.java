package com.dereck.filesys.admin;

import cn.hutool.json.JSONUtil;
import com.dereck.filesys.admin.dto.UserDTO;
import com.dereck.filesys.admin.security.LoginUser;
import com.dereck.filesys.admin.service.UserService;
import com.dereck.filesys.common.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserTest {

    @Resource
    UserService userService;

    @Resource
    UserDTO userDTO;


    @Test
    public void testInsertOne(){
        User user = new User("测试人", "2564938992@qq.com", "123456");
        userService.saveOrUpdate(user);
    }

    @Test
    public void getByUserName(){
        System.out.println(userService.lambdaQuery().eq(User::getName, "中午").one());
    }

    @Test
    public void testJson(){
        User a = new User("aaa", null, "12345");
        LoginUser loginUser = new LoginUser(a);
        System.out.println(JSONUtil.toJsonStr(a));
        System.out.println(JSONUtil.toJsonStr(loginUser));
    }



}
