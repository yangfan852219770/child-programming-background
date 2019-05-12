package com.child.programming.education.manage.dto;

import lombok.Data;

/**
 * @Description：老师课程列表信息
 * @Author：yangfan
 **/
@Data
public class TeacherCourseDto {
    private Integer teacherId; // 老师id

    private String teacherName; // 老师姓名

    private Integer courseId; // 课程id

    private String courseName; // 课程名称

    private Integer gradeId; // 班级id

    private String gradeName; // 班级名称
}
