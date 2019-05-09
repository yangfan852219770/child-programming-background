package com.child.programming.base.service;

import com.child.programming.base.model.TbStudentCourseScheduleDo;
import com.child.programming.education.manage.dto.CourseScheduleDto;
import com.child.programming.education.manage.dto.StudentCourseDto;

import java.util.List;
import java.util.Map;

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
     * 获取学生课程信息
     * @param map
     * @return
     */
    List<StudentCourseDto> getStudentCourseList(Map map);

    /**
     * 获取学生课程表信息
     * @param studentId
     * @param courseId
     * @return
     */
    List<TbStudentCourseScheduleDo> getStudentCourseScheduleList(Integer studentId, Integer courseId);

}
