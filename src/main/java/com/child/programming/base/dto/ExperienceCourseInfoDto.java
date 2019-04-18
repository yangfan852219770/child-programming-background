package com.child.programming.base.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Description：
 * @Author：yangfan
 **/

@Data
public class ExperienceCourseInfoDto {

    private Integer id; // 主键

    private String title; // 名称

    private Double money; // 价格

    private String introduction; // 介绍

    private String photoUrl; // 图片地址

    private String address; // 上课地址

    private Date signUpEndDate; // 终止报名时间

    private String telephone; // 电话

    private Integer status; // 状态0删除，1报名，3结课

    private Date createTime; // 创建时间
}
