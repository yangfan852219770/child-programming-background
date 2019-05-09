package com.child.programming.education.manage.controller;

import com.child.programming.base.model.TbStudentCourseScheduleDo;
import com.child.programming.base.service.IStudentCourseScheduleService;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.education.manage.dto.StudentCourseDto;
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
     * 获取学生信息
     * @param gradeId
     * @param studentName
     * @return
     */
    @RequestMapping(value = "/getStudentInfoList", method = RequestMethod.GET)
    public List<StudentCourseDto> getStudentInfoList(@RequestParam(value = "gradeId", required = false)Integer gradeId,
                                                @RequestParam(value = "studentName", required = false)String studentName){
        Map map = new HashMap();

        map.put("gradeId", gradeId);
        if (EmptyUtils.stringIsEmpty(studentName))
            map.put("studentName", null);
        else
            map.put("studentName", "%" + studentName + "%");

        return iStudentCourseScheduleService.getStudentCourseList(map);
    }

    /**
     * 获取学生课程表
     * @param studentId
     * @param courseId
     * @return
     */
    @RequestMapping(value = "getStudentCourseScheduleList", method = RequestMethod.GET)
    public List<TbStudentCourseScheduleDo> getStudentCourseScheduleList(@RequestParam(value = "studentId", required = true)Integer studentId,
                                                                        @RequestParam(value = "courseId", required = true)Integer courseId){
        if (EmptyUtils.intIsEmpty(studentId) || EmptyUtils.intIsEmpty(courseId))
            return null;
        return iStudentCourseScheduleService.getStudentCourseScheduleList(studentId, courseId);
    }
}
