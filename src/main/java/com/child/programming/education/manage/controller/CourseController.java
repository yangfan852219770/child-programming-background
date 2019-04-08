package com.child.programming.education.manage.controller;

import com.child.programming.base.dto.CourseInfoDto;
import com.child.programming.base.service.ICourseService;
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
 * @Description：课程管理
 * @Author：yangfan
 **/


@RestController
@RequestMapping("/course")
@Log4j2
public class CourseController {
    @Autowired
    private ICourseService iCourseService;

    /**
     * 列表
     * @param name 课程名称
     * @return
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    public List<CourseInfoDto> getList(@RequestParam(value = "name", required = false) String name){
        return iCourseService.getList(name);
    }
}
