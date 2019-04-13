package com.child.programming.app.web.controller;

import com.child.programming.app.web.dto.CourseArrange;
import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.model.TbExperienceCourseDo;
import com.child.programming.base.service.IExperienceCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * @Description:    体验课信息
 * @Author:         赵赞峰
 * @Version:        1.0
 */
@RestController
@RequestMapping("app/web/experienceCourse")
public class ExperienceCourseAppController {

@Autowired
private IExperienceCourseService iExperienceCourseService;

    /**
     * @Description:    所有准备开课的体验课列表
     */
    @RequestMapping("getAllExperienceCourse")
    public ResultDto getAllExperienceCourse(){
        List<TbExperienceCourseDo> experienceCourses = iExperienceCourseService.getAllExperienceCourse();
        return ResultDto.success(experienceCourses);
    }


}
