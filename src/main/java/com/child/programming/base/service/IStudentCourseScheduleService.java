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
    Boolean generateBatchSchedule(List<CourseScheduleDto> courseScheduleDtoList, Integer gradeId, Integer userId);

    /**
     * 获取学生课程表信息
     * @param studentId
     * @return
     */
    List<StudentScheduleDto> getStudentCourseScheduleList(Integer studentId);

    /**
     * 生成单个学生课表
     * @param courseScheduleDtoList
     * @param studentId
     * @param userId
     * @return
     */
    Boolean generateOneSchedule(List<CourseScheduleDto> courseScheduleDtoList, Integer studentId, Integer userId);

}
