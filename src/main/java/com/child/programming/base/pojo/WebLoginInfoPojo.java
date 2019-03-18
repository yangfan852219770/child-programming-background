package com.child.programming.base.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description：web端登陆用户账号和密码
 * @Author：yangfan
 **/

@ToString
public class WebLoginInfoPojo {
    @Getter
    @Setter
    private String loginId; //登陆账号

    @Getter
    @Setter
    private String password; //登陆密码
}
