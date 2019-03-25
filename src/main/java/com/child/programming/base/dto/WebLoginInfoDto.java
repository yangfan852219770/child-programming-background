package com.child.programming.base.dto;

import lombok.Data;

/**
 * @Description：web端登陆用户账号和密码
 * @Author：yangfan
 **/

@Data
public class WebLoginInfoDto {

    private String loginId; //登陆账号

    private String password; //登陆密码
}
