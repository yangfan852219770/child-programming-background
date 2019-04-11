package com.child.programming.app.web.controller;

import com.child.programming.app.web.dto.CourseArrange;
import com.child.programming.app.web.dto.HomePageHeighSerachParam;
import com.child.programming.app.web.dto.SignUpCourseDto;
import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.model.TbCourseDo;
import com.child.programming.base.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("getStudentSignUpCourseList")
    public ResultDto getStudentSignUpCourseList(@RequestParam(value="page" ,required =false ) int page,
                                                @RequestParam(value="limit" ,required =false ) int limit,
                                                @RequestParam(value="studentId" ,required =false ) String studentId){
        List<SignUpCourseDto> signUpCourseDtos = iCourseService.getStudentSignUpCourseList(page,limit,studentId);
        return ResultDto.success(signUpCourseDtos);
    }

    @RequestMapping("getCourseById")
    public ResultDto getCourseById(int courseId){
        TbCourseDo courseDo = iCourseService.getCourseById(courseId);
        return ResultDto.success(courseDo);
    }


    @RequestMapping("getStudentSignUpCourseHistoryList")
    public ResultDto getStudentSignUpCourseHistoryList(@RequestParam(value="page" ,required =false ) int page,
                                                @RequestParam(value="limit" ,required =false ) int limit,
                                                @RequestParam(value="studentId" ,required =false ) String studentId){
        List<SignUpCourseDto> signUpCourseHistoryDtos = iCourseService.getStudentSignUpCourseHistoryList(page,limit,studentId);
        return ResultDto.success(signUpCourseHistoryDtos);
    }

    @RequestMapping("getStudentCourseClassList")
    public ResultDto getStudentCourseClassList(@RequestParam(value="page" ,required =false ) int page,
                                                       @RequestParam(value="limit" ,required =false ) int limit,
                                                       @RequestParam(value="studentId" ,required =false ) String studentId){
        List<SignUpCourseDto> studentCourseClassList = iCourseService.getStudentCourseClassList(page,limit,studentId);
        return ResultDto.success(studentCourseClassList);
    }

    @RequestMapping("getStudentCourseListByDate")
    public ResultDto getStudentCourseListByDate(@RequestParam(value="selectDate" ,required =false ) String selectDate,
                                               @RequestParam(value="week" ,required =false ) String week,
                                               @RequestParam(value="studentId" ,required =false ) String studentId){
        List<SignUpCourseDto> studentCourseByDateList = iCourseService.getStudentCourseListByDate(selectDate,week,studentId);
        return ResultDto.success(studentCourseByDateList);
    }


}
