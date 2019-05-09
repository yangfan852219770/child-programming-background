package com.child.programming.education.manage.controller;

import com.child.programming.base.model.TbTeacherCourseScheduleDo;
import com.child.programming.base.service.ITeacherCourseScheduleService;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.education.manage.dto.TeacherCourseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 获取老师课程信息
     * @param teacherName
     * @return
     */
    @RequestMapping(value = "getTeacherInfoList", method = RequestMethod.GET)
    public List<TeacherCourseDto> getTeacherInfoList(@RequestParam(value = "teacherName", required = false)String teacherName){
        Map map = new HashMap();
        if (EmptyUtils.stringIsEmpty(teacherName))
            map.put("teacherName", null);
        else
            map.put("teacherName", "%" + teacherName + "%");
        return iTeacherCourseScheduleService.getTeacherCourseList(map);
    }

    /**
     * 获取老师课程表
     * @param teacherId
     * @param courseId
     * @param gradeId
     * @return
     */
    @RequestMapping(value = "getTeacherCourseScheduleList", method = RequestMethod.GET)
    public List<TbTeacherCourseScheduleDo> getTeacherCourseScheduleList(@RequestParam(value = "teacherId", required = true)Integer teacherId,
                                                                        @RequestParam(value = "courseId", required = true)Integer courseId,
                                                                        @RequestParam(value="gradeId", required = true)Integer gradeId){
        if (EmptyUtils.intIsEmpty(teacherId) || EmptyUtils.intIsEmpty(courseId) || EmptyUtils.intIsEmpty(gradeId))
            return null;
        return iTeacherCourseScheduleService.getTeacherCourseScheduleList(teacherId, courseId, gradeId);
    }
}
