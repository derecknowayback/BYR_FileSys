package com.dereck.filesys.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /**用户姓名*/
    private String name;

    /**用户邮箱*/
    private String email;

    /**用户密码*/
    private String password;

}
