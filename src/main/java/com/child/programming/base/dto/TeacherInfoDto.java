package com.child.programming.base.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Description：老师展示信息
 * @Author：yangfan
 **/

@Data
public class TeacherInfoDto {

    private Integer id; // 主键id

    private String loginId; // 登陆账号

    private String name; // 姓名

    private String phone; // 电话

    private String photoUrl; // 照片

    private String introduction; // 介绍

    private String certificate; // 证书

    private Date createTime; // 创建时间

}
