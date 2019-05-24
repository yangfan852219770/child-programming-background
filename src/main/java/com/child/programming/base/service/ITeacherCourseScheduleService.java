package com.child.programming.base.service;

import com.child.programming.base.model.TbTeacherCourseScheduleDo;
import com.child.programming.education.manage.dto.CourseScheduleDto;

import java.util.List;

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
     * 获取老师课程安排
     * @param teacherId
     * @return
     */
    List<TbTeacherCourseScheduleDo> getTeacherCourseScheduleList(Integer teacherId);
}
