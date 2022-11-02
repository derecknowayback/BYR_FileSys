package com.dereck.filesys.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("users")
public class User {
    /**用户id*/
    @TableId(type = IdType.AUTO)
    private Long id;

    /**用户姓名*/
    @TableField(value = "user_name")
    private String name;

    /**用户邮箱*/
    @TableField(value = "user_email")
    private String email;

    /**用户密码*/
    @TableField(value = "user_password")
    private String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
