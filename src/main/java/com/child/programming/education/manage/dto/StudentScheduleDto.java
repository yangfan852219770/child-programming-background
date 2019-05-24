package com.child.programming.education.manage.dto;

import com.child.programming.base.model.TbStudentCourseScheduleDo;
import lombok.Data;

/**
 * @Description：
 * @Author：yangfan
 **/
@Data
public class StudentScheduleDto {
    private TbStudentCourseScheduleDo studentCourseSchedule;

    private CourseDetailDto courseDetail;
}
