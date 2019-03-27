package com.child.programming.base.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author zdp
 * @description: 学生展示信息
 */
@Data
public class StudentInfoDto {

    private Integer id; //主键id

    private String guardianName; //监护人姓名

    private String guardianPhone; //监护人电话

    private String name; //学生姓名

    private Byte sex; //学生性别

    private Integer age; //学生年龄

    private String address; //学生地址

    private String email; //学生email

    private String photoUrl; //学生照片

    private Date createTime; //创建时间

}
