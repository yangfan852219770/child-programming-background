package com.child.programming.education.manage.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Description：付费记录信息
 * @Author：yangfan
 **/
@Data
public class PaymentRecordInfoDto {
    private Integer id; // 付费记录主键

    private Double payMoney; // 付费金额

    private Date payTime; // 缴费时间

    private Byte courseType; // 课程类型，1为正式课，2为体验课

    private Integer studentId; // 学生id

    private String studentName; // 学生姓名

    private Integer courseId; // 课程id

    private String courseName; // 课程名称

}
