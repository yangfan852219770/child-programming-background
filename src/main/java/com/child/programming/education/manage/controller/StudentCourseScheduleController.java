package com.child.programming.education.manage.controller;

import com.child.programming.base.model.TbStudentCourseScheduleDo;
import com.child.programming.base.service.IStudentCourseScheduleService;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.education.manage.dto.StudentScheduleDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description：学生课程表
 * @Author：yangfan
 **/
@RestController
@RequestMapping("/studentCourseSchedule")
@Log4j2
public class StudentCourseScheduleController {

    @Autowired
    private IStudentCourseScheduleService iStudentCourseScheduleService;

    /**
     * 获取学生课程表
     * @param studentId
     * @return
     */
    @RequestMapping(value = "getStudentCourseScheduleList", method = RequestMethod.GET)
    public List<StudentScheduleDto> getStudentCourseScheduleList(@RequestParam(value = "studentId", required = true)Integer studentId){
        if (EmptyUtils.intIsEmpty(studentId))
            return null;
        return iStudentCourseScheduleService.getStudentCourseScheduleList(studentId);
    }
}
