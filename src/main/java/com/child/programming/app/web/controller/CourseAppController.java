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
/**
 * @Description:    课程信息
 * @Author:         赵赞峰
 * @Version:        1.0
 */
@RestController
@RequestMapping("app/web/course")
public class CourseAppController {

    @Autowired
    private ICourseService iCourseService;

    /**
     * @Description:    小程序首页课程列表展示，包括搜索，高级搜索
     */
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
        if (courseDos!=null && courseDos.size()>0){
            if (courseDos.get(0)==null){
                return ResultDto.success(null);
            }
            return ResultDto.success(courseDos);
        }
        return ResultDto.success(null);
    }

    /**
     * @Description:    根据课程id查询小程序课程安排详细信息
     */
    @RequestMapping("getCourseDetailByCourseId")
    public ResultDto getCourseDetailByCourseId(int courseId){
        List<CourseArrange> courseArranges = iCourseService.getCourseDetailByCourseId(courseId);
        return ResultDto.success(courseArranges);
    }

    /**
     * @Description:    根据学生ID查询报名的课程
     */
    @RequestMapping("getStudentSignUpCourseList")
    public ResultDto getStudentSignUpCourseList(@RequestParam(value="page" ,required =false ) int page,
                                                @RequestParam(value="limit" ,required =false ) int limit,
                                                @RequestParam(value="studentId" ,required =false ) String studentId){
        List<SignUpCourseDto> signUpCourseDtos = iCourseService.getStudentSignUpCourseList(page,limit,studentId);
        return ResultDto.success(signUpCourseDtos);
    }

    /**
     * @Description:    根据课程ID查询课程信息
     */
    @RequestMapping("getCourseById")
    public ResultDto getCourseById(int courseId){
        TbCourseDo courseDo = iCourseService.getCourseById(courseId);
        return ResultDto.success(courseDo);
    }

    /**
     * @Description:    查询学生的购课历史
     */
    @RequestMapping("getStudentSignUpCourseHistoryList")
    public ResultDto getStudentSignUpCourseHistoryList(@RequestParam(value="page" ,required =false ) int page,
                                                @RequestParam(value="limit" ,required =false ) int limit,
                                                @RequestParam(value="studentId" ,required =false ) String studentId){
        List<SignUpCourseDto> signUpCourseHistoryDtos = iCourseService.getStudentSignUpCourseHistoryList(page,limit,studentId);
        return ResultDto.success(signUpCourseHistoryDtos);
    }

    /**
     * @Description:    查询学生的正在进行的课程信息
     */
    @RequestMapping("getStudentCourseClassList")
    public ResultDto getStudentCourseClassList(@RequestParam(value="page" ,required =false ) int page,
                                                       @RequestParam(value="limit" ,required =false ) int limit,
                                                       @RequestParam(value="studentId" ,required =false ) String studentId){
        List<SignUpCourseDto> studentCourseClassList = iCourseService.getStudentCourseClassList(page,limit,studentId);
        return ResultDto.success(studentCourseClassList);
    }

    /**
     * @Description:    根据日期查询学生的课程信息列表
     */
    @RequestMapping("getStudentCourseListByDate")
    public ResultDto getStudentCourseListByDate(@RequestParam(value="selectDate" ,required =false ) String selectDate,
                                               @RequestParam(value="week" ,required =false ) String week,
                                               @RequestParam(value="studentId" ,required =false ) String studentId){
        List<SignUpCourseDto> studentCourseByDateList = iCourseService.getStudentCourseListByDate(selectDate,week,studentId);
        return ResultDto.success(studentCourseByDateList);
    }

    /**
    *  @Description:    查询学生收藏课程
    */
    @RequestMapping("getStudentCollectCourseList")
    public ResultDto getStudentCollectCourseList(@RequestParam(value="page" ,required =false ) int page,
                                               @RequestParam(value="limit" ,required =false ) int limit,
                                               @RequestParam(value="studentId" ,required =false ) int studentId){
        List<TbCourseDo> courseDos = iCourseService.getStudentCollectCourseList(page,limit,studentId);
        if (courseDos!=null && courseDos.size()>0){
            if (courseDos.get(0)==null){
                return ResultDto.success(null);
            }
            return ResultDto.success(courseDos);
        }
        return ResultDto.success(null);
    }


}
