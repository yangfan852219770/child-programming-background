package com.child.programming.base.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Description：
 * @Author：yangfan
 **/
@Data
public class SignUpFormalCourseInfoDto {
    private Integer id; // 报名正式课主键

    private Integer studentId; // 学生id

    private Integer gradeId; // 班级id

    private Integer courseId; // 课程id

    private String studentName; // 学生姓名

    private String studentPhone; // 学生电话

    private String gradeName; // 班级名称

    private String courseName; // 课程名称

    private Double courseMoney; // 课程价格

    private Integer courseStatus; // 课程状态

    private Date signUpTime; // 报名时间

    private Byte isPayment; // 是否付费

    private Byte isHalfway; // 是否中途报名
}
