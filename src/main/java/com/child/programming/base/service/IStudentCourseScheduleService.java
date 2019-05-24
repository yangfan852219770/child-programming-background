package com.child.programming.base.service;

import com.child.programming.base.model.TbStudentCourseScheduleDo;
import com.child.programming.education.manage.dto.CourseScheduleDto;
import com.child.programming.education.manage.dto.StudentScheduleDto;

import java.util.List;

/**
 * @Description：
 * @Author：yangfan
 **/
public interface IStudentCourseScheduleService {

    /**
     * 插入课程安排
     * @param courseScheduleDtoList
     * @param gradeId
     * @param userId
     * @return
     */
    Boolean generateSchedule(List<CourseScheduleDto> courseScheduleDtoList, Integer gradeId, Integer userId);

    /**
     * 获取学生课程表信息
     * @param studentId
     * @return
     */
    List<StudentScheduleDto> getStudentCourseScheduleList(Integer studentId);

}
