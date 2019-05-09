package com.child.programming.base.service;

import com.child.programming.base.model.TbTeacherCourseScheduleDo;
import com.child.programming.education.manage.dto.CourseScheduleDto;
import com.child.programming.education.manage.dto.TeacherCourseDto;

import java.util.List;
import java.util.Map;

/**
 * @Description：
 * @Author：yangfan
 **/
public interface ITeacherCourseScheduleService {

    /**
     * 生成老师课程表
     * @param courseScheduleDtoList
     * @param userId
     * @return
     */
    Boolean generateSchedule(List<CourseScheduleDto> courseScheduleDtoList, Integer userId);

    /**
     * 老师课程信息
     * @param map
     * @return
     */
    List<TeacherCourseDto> getTeacherCourseList(Map map);

    /**
     * 获取老师课程安排
     * @param teacherId
     * @param courseId
     * @param gradeId
     * @return
     */
    List<TbTeacherCourseScheduleDo> getTeacherCourseScheduleList(Integer teacherId, Integer courseId, Integer gradeId);
}
