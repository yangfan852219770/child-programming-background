package com.child.programming.education.manage.dto;

import lombok.Data;

/**
 * @Description：学生课程列表信息
 * @Author：yangfan
 **/
@Data
public class StudentCourseDto {
    private Integer studentId; // 学生id

    private String studentName; // 学生姓名

    private Integer courseId; // 课程id

    private String courseName; // 课程名称

    private Integer gradeId; // 班级id

    private String gradeName; // 班级名称
}
