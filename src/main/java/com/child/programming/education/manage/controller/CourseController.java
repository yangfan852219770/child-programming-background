package com.child.programming.education.manage.controller;

import com.child.programming.base.dto.CourseInfoDto;
import com.child.programming.base.dto.LoginedUserInfoDto;
import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.model.TbCourseDo;
import com.child.programming.base.model.TbGradeDo;
import com.child.programming.base.service.ICourseService;
import com.child.programming.base.service.IGradeService;
import com.child.programming.base.util.DateUtil;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.base.util.HttpSessionUtil;
import com.child.programming.education.manage.dto.CourseSaveDto;
import com.child.programming.education.manage.dto.CourseTimeScheduleDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
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
    @Autowired
    private IGradeService iGradeService;

    /**
     * 列表
     * @param name 课程名称
     * @return
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    public List<CourseSaveDto> getList(@RequestParam(value = "name", required = false) String name){
        return iCourseService.getList(name);
    }

    /**
     * 保存
     * @param session
     * @param courseSaveDto
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ResultDto save(HttpSession session, @RequestBody CourseSaveDto courseSaveDto){
        log.info(courseSaveDto);
        LoginedUserInfoDto userInfoPojo = HttpSessionUtil.getLoginedUserInfo(session);
        if (null != userInfoPojo && null != courseSaveDto){
            // 参数处理
            TbCourseDo courseDo = new TbCourseDo();
            BeanUtils.copyProperties(courseSaveDto, courseDo);

            // 处理时间安排
            List<CourseTimeScheduleDto> courseTimeScheduleDtoList = courseSaveDto.getTimeSchedule();
            if (EmptyUtils.listIsEmpty(courseTimeScheduleDtoList)){
                log.error("课程时间安排[timeSchedule]为空!");
                return ResultDto.fail("参数不完整，无法保存");
            }
            // 冲突检测
            String result = iGradeService.validateTimeScheduleConflict(courseTimeScheduleDtoList);
            log.info(result);
            // 有冲突
            if (!"0".equals(result))
                return ResultDto.fail(result);
            //无冲突
            boolean saveResult = iCourseService.save(userInfoPojo.getId(), courseDo, courseTimeScheduleDtoList);
            if (saveResult)
                return ResultDto.success();
            return ResultDto.fail();
        }

        return ResultDto.fail();
    }
}
