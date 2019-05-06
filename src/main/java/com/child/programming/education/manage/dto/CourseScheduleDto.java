package com.child.programming.education.manage.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Description：
 * @Author：yangfan
 **/
@Data
public class CourseScheduleDto {
    private Integer studentId; // 学生id

    private Integer teacherId; // 老师id

    private Integer gradeId; // 班级id

    private Integer courseId; // 课程id

    private Integer period; // 课时

    private Date startTime; // 开始时间

    private Date endTime; // 结束时间
}
