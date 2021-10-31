package com.k.bootweb.pojo.dao;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @ApiModelProperty(value = "用户号",position = 1)
    private Integer uid;
    @ApiModelProperty(value = "用户名",position = 2)
    private String username;
    @ApiModelProperty(value = "用户密码",position = 3)
    private String password;
    @ApiModelProperty(value = "邮箱",position = 4)
    private String email;
    private String salt;
    private int type;

    public User(String username,String password,String email){
        this.username=username;
        this.password=password;
        this.email=email;
    }

}
