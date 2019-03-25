package com.child.programming.education.manage.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Description：学校展示信息
 * @Author：yangfan
 **/

@Data
public class SchoolInfoDto {

    private Integer id; // 主键id

    private String name; // 学校名称

    private String address; // 学校地址

    private String introduction; // 介绍

    private String chargeUserName; // 负责人姓名

    private String chargeUserPhone; // 负责人电话

    private Date createTime; // 创建时间
}
