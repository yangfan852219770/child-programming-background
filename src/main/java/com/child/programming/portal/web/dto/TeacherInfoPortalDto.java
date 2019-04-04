package com.child.programming.portal.web.dto;

import lombok.Data;

/**
 * @Description：老师展示信息
 * @Author：yangfan
 **/

@Data
public class TeacherInfoPortalDto {

    private String name; // 姓名

    private String phone; // 电话

    private String photoUrl; // 照片

    private String introduction; // 介绍

}
