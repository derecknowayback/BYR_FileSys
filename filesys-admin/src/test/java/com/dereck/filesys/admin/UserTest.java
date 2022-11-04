package com.dereck.filesys.admin;

import com.dereck.filesys.admin.service.UserService;
import com.dereck.filesys.common.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Resource
    UserService userService;


    @Test
    public void testInsertOne(){
        User user = new User("测试人", "2564938992@qq.com", "123456");
        userService.saveOrUpdate(user);
    }


}
