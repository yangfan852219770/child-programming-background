package com.child.programming.base.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description：登陆之后，存放用户信息对象
 * @Author：yangfan
 **/

@ToString
public class LoginedUserInfoPojo {

    @Getter
    @Setter
    private Integer id; //主键

    @Getter
    @Setter
    private String userName; //登陆者的真实姓名

    @Getter
    @Setter
    private String loginId; //登陆账号

    @Getter
    @Setter
    //TODO 临时
    private String currentAuthority; //登陆角色
}
