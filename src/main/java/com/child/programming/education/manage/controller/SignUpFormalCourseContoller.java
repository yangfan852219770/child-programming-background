package com.child.programming.education.manage.controller;

import com.child.programming.base.dto.LoginedUserInfoDto;
import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.dto.SignUpExperienceCourseInfoDto;
import com.child.programming.base.dto.SignUpFormalCourseInfoDto;
import com.child.programming.base.service.ISignUpExperienceCourseService;
import com.child.programming.base.service.ISignUpFormalCourseService;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.base.util.HttpSessionUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description：正式课报名管理
 * @Author：yangfan
 **/

@RestController
@RequestMapping("/signUpFormalCourse")
@Log4j2
public class SignUpFormalCourseContoller {
    @Autowired
    private ISignUpFormalCourseService iSignUpFormalCourseService;

    /**
     * 学生报名正式课列表
     * @param studentName 学生姓名
     * @param isPayment 是否缴费
     * @return
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    public List<SignUpFormalCourseInfoDto> getList(@RequestParam(value = "studentName", required = false)String studentName,
                                                   @RequestParam(value = "isPayment", required = false)String isPayment){
        Map map = new HashMap();
        map.put("studentName", studentName);
        map.put("isPayment", isPayment);
        return iSignUpFormalCourseService.getList(map);
    }

    /**
     * 逻辑删除
     * @param idsStr
     * @param session
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public ResultDto delete(@RequestParam(value = "idsStr", required = true)String idsStr,
                            HttpSession session) {
        log.info(idsStr + "删除");

        LoginedUserInfoDto userInfoPojo = HttpSessionUtil.getLoginedUserInfo(session);
        if (null != userInfoPojo && EmptyUtils.stringIsNotEmpty(idsStr)) {
            String[] idArray = idsStr.split(",");
            if (!EmptyUtils.arrayIsEmpty(idArray)){
                boolean result = iSignUpFormalCourseService.delete(idArray, userInfoPojo.getId());
                if (result)
                    return ResultDto.success();
            }
        }
        return ResultDto.fail();
    }
}
