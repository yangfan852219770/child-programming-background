package com.child.programming.app.web.controller;

import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.model.TbCourseDo;
import com.child.programming.base.service.ICourseService;
import com.child.programming.base.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("app/web/course")
public class CourseAppController {

    @Autowired
    private ICourseService iCourseService;


    @RequestMapping("getClassNow")
    public ResultDto getClassNow(HttpServletRequest request, HttpServletResponse response, int limit, int page,
                                 @RequestParam(value="courseName", required=false) String courseName ){
        List<TbCourseDo> courseDos = iCourseService.getClassNow(limit,page,courseName);
        System.out.println(courseName);
        if (courseDos!=null && courseDos.size()>0){
            return ResultDto.success(courseDos);
        }
        return new ResultDto(ResponseUtil.FAIL_0,"获取课程列表失败");
    }



}
