package com.child.programming.education.manage.dto;

import com.child.programming.base.model.TbTeacherCourseScheduleDo;
import lombok.Data;

/**
 * @Description：
 * @Author：yangfan
 **/
@Data
public class TeacherScheduleDto {
    private TbTeacherCourseScheduleDo teacherCourseSchedule;

    private CourseDetailDto courseDetail;
}
