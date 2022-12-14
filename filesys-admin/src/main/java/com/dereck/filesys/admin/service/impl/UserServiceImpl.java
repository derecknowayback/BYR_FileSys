package com.dereck.filesys.admin.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dereck.filesys.admin.dto.UserDTO;
import com.dereck.filesys.admin.service.UserService;
import com.dereck.filesys.common.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserDTO, User>implements UserService {

}
