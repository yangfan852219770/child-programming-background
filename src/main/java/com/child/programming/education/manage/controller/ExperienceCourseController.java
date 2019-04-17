package com.child.programming.education.manage.controller;

import com.child.programming.base.dto.ExperienceCourseInfoDto;
import com.child.programming.base.dto.LoginedUserInfoDto;
import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.model.TbExperienceCourseDo;
import com.child.programming.base.service.IExperienceCourseService;
import com.child.programming.base.util.HttpSessionUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description：体验课管理
 * @Author：yangfan
 **/

@RestController
@RequestMapping("/experienceCourse")
@Log4j2
public class ExperienceCourseController {
    @Autowired
    private IExperienceCourseService iExperienceCourseService;

    /**
     * 获取集合
     * @param title
     * @return
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    public List<ExperienceCourseInfoDto> getList(@RequestParam(value = "title", required = false) String title){
        return iExperienceCourseService.getList(title);
    }

    /**
     * 保存
     * @param session
     * @param experienceCourseDo
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ResultDto save(HttpSession session, @RequestBody TbExperienceCourseDo experienceCourseDo){
        LoginedUserInfoDto userInfoPojo = HttpSessionUtil.getLoginedUserInfo(session);
        if (null != userInfoPojo && null != experienceCourseDo){
            boolean result = iExperienceCourseService.save(userInfoPojo.getId(), experienceCourseDo);
            if (result)
                return ResultDto.success();
        }
        return ResultDto.fail();

    }
}
