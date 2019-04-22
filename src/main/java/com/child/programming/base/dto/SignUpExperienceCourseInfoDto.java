package com.child.programming.base.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Description：
 * @Author：yangfan
 **/
@Data
public class SignUpExperienceCourseInfoDto {
    private Integer id; // 报名主键

    private Integer studentId; // 学生id

    private Integer experienceCourseId; // 体验课id

    private String studentName; // 学生姓名

    private String studentPhone; // 学生电话

    private String experienceCourseName; // 体验课名称

    private Integer shareCounts; // 分享次数

    private Byte isPayment; // 是否付费

    private Date signUpTime; // 报名时间
}
