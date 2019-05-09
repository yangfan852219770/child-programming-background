package com.child.programming.portal.web.controller;


import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.model.TbExperienceCourseDo;
import com.child.programming.base.service.IExperienceCourseService;
import com.child.programming.base.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author zdp
 * @description: TODO
 */
@RestController
@RequestMapping("portal")
public class ExperienceCoursePortalController {

    @Autowired
    private IExperienceCourseService experienceCourseService;

    /**
     * 体验课报名
     * @param studentName
     * @param studentPhone
     * @return
     */
    @RequestMapping("experienceCoursePortalSave")
    public ResultDto experienceCoursePortalSave(String studentName,String studentPhone) {

        return experienceCourseService.consult(studentName,studentPhone);
    }


}
