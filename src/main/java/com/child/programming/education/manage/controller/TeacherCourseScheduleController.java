package com.child.programming.education.manage.controller;

import com.child.programming.base.model.TbTeacherCourseScheduleDo;
import com.child.programming.base.service.ITeacherCourseScheduleService;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.education.manage.dto.TeacherScheduleDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description：老师课程安排
 * @Author：yangfan
 **/
@RestController
@RequestMapping("/teachersCourseSchedule")
@Log4j2
public class TeacherCourseScheduleController {

    @Autowired
    private ITeacherCourseScheduleService iTeacherCourseScheduleService;

    /**
     * 获取老师课程表
     * @param teacherId
     * @return
     */
    @RequestMapping(value = "getTeacherCourseScheduleList", method = RequestMethod.GET)
    public List<TeacherScheduleDto> getTeacherCourseScheduleList(@RequestParam(value = "teacherId", required = true)Integer teacherId){
        if (EmptyUtils.intIsEmpty(teacherId))
            return null;
        return iTeacherCourseScheduleService.getTeacherCourseScheduleList(teacherId);
    }
}
