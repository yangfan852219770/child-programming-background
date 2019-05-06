package com.child.programming.portal.web.controller;

import com.child.programming.base.dto.CourseInfoDto;
import com.child.programming.base.service.ICourseService;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.education.manage.dto.CourseSaveDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${IMAGE.BASE.MANAGE.URL}")
    private String baseUrl;
    /**
     * 课程展示列表
     * @return
     */
    @RequestMapping("courseGetList")
     public List<CourseSaveDto> getList(){
        List<CourseSaveDto> list= iCourseService.getList("");
        if(EmptyUtils.listIsEmpty(list))
            return null;
        for (CourseSaveDto courseSaveDto:list
             ) {
            courseSaveDto.setPhotoUrl(baseUrl+courseSaveDto.getPhotoUrl());
        }
         return  list;
     }
}
