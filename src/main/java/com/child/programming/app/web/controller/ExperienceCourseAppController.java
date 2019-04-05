package com.child.programming.app.web.controller;

import com.child.programming.app.web.dto.CourseArrange;
import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.model.TbExperienceCourseDo;
import com.child.programming.base.service.IExperienceCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("app/web/experienceCourse")
public class ExperienceCourseAppController {

@Autowired
private IExperienceCourseService iExperienceCourseService;

    @RequestMapping("getAllExperienceCourse")
    public ResultDto getAllExperienceCourse(){
        List<TbExperienceCourseDo> experienceCourses = iExperienceCourseService.getAllExperienceCourse();
        return ResultDto.success(experienceCourses);
    }


}
