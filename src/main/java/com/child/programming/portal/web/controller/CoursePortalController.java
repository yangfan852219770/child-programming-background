package com.child.programming.portal.web.controller;

import com.child.programming.base.dto.CourseInfoDto;
import com.child.programming.base.service.ICourseService;
import com.child.programming.education.manage.dto.CourseSaveDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zdp
 * @description: TODO
 */
@RestController
@RequestMapping("portal")
public class CoursePortalController {
    @Autowired
    private ICourseService iCourseService;

    /**
     * 课程展示列表
     * @return
     */
    @RequestMapping("courseGetList")
     public List<CourseSaveDto> getList(){
         return  iCourseService.getList("");
     }
}
