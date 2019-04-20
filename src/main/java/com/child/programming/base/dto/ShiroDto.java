package com.child.programming.base.dto;

import lombok.Data;

/**
 * @author zdp
 * @description: TODO
 */
@Data
public class ShiroDto {

    private Integer id; //主键

    private String name; //登陆者的真实姓名

    private String loginId; //登陆账号

    //TODO 临时
    private String currentAuthority; //登陆角色

    private String flexibleProperty; //灵活属性 用于储存学生作品 或其他

    private String password;
}
