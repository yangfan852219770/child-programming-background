package com.child.programming.base.dto;

import lombok.Data;

/**
 * @Description：登陆之后，存放用户信息对象
 * @Author：yangfan
 **/

@Data
public class LoginedUserInfoDto {

    private Integer id; //主键

    private String name; //登陆者的真实姓名

    private String loginId; //登陆账号

    private String photoUrl;

    private String roleName;

    private String phone;

    private String introduction;

    private String currentAuthority; //登陆角色

    private String flexibleProperty; //灵活属性 用于储存学生作品 或其他
}
