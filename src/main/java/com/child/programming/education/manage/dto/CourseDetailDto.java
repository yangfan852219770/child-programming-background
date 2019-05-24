package com.child.programming.education.manage.dto;

import lombok.Data;

/**
 * @Description：
 * @Author：yangfan
 **/

@Data
public class CourseDetailDto {
    private Integer gradeId; // 班级id

    private String gradeName; // 班级名称

    private Integer courseId; // 课程id

    private String courseName; // 课程名称

    private Integer classroomId; // 教室id

    private Integer classroomCode; // 教室编码

    private Integer schoolId; // 校区id

    private String schoolName; // 校区名称
}
