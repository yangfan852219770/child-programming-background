package com.child.programming.base.dto;

import lombok.Data;

/**
 * @Description：登陆之后，存放用户信息对象
 * @Author：yangfan
 **/

@Data
public class LoginedUserInfoDto {

    private Integer id; //主键

    private String userName; //登陆者的真实姓名

    private String loginId; //登陆账号

    //TODO 临时
    private String currentAuthority; //登陆角色
}
