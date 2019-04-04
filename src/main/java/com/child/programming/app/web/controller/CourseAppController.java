package com.child.programming.app.web.controller;

import com.child.programming.app.web.dto.CourseArrange;
import com.child.programming.app.web.dto.HomePageHeighSerachParam;
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
    public ResultDto getClassNow(HomePageHeighSerachParam homePageHeighSerachParam){
        if (Boolean.valueOf(homePageHeighSerachParam.getHeighSearchFlg())){
            //高级搜索
            homePageHeighSerachParam.setCourseName(null);
        }else{
            //模糊搜索
            homePageHeighSerachParam.setSelectSchoolId(null);
            homePageHeighSerachParam.setTeacherId(null);
            homePageHeighSerachParam.setLowPrice(null);
            homePageHeighSerachParam.setHeighPrice(null);
            homePageHeighSerachParam.setLowDate(null);
            homePageHeighSerachParam.setHeighDate(null);
        }
        List<TbCourseDo> courseDos = iCourseService.getClassNow(homePageHeighSerachParam);
        return ResultDto.success(courseDos);
    }

    @RequestMapping("getCourseDetailByCourseId")
    public ResultDto getCourseDetailByCourseId(int courseId){
        List<CourseArrange> courseArranges = iCourseService.getCourseDetailByCourseId(courseId);
        return ResultDto.success(courseArranges);
    }



}
