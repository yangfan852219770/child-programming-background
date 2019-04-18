package com.child.programming.base.controller;

import com.child.programming.base.dto.LoginedUserInfoDto;
import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.dto.WebLoginInfoDto;
import com.child.programming.base.service.ITeacherService;
import com.child.programming.base.util.ConstDataUtil;
import com.child.programming.base.util.EmptyUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Description：登陆逻辑，包括web和小程序
 * @Author：yangfan
 **/
@RestController
@Log4j2
public class LoginController {


    @Autowired
    private ITeacherService iTeacherService;
    /**
     * web端的登陆
     * @return
     */
    @RequestMapping(value = "web/login/account", method = RequestMethod.POST)
    public ResultDto webLogin(HttpSession session,
                              @RequestBody WebLoginInfoDto webLoginInfoDto){

        log.info(webLoginInfoDto + "登陆");

        LoginedUserInfoDto userInfoPojo = iTeacherService.getTeacherByLoginIdAndPassword(webLoginInfoDto.getLoginId(),webLoginInfoDto.getPassword());

        if(EmptyUtils.objectIsEmpty(userInfoPojo))
            return ResultDto.fail();

        session.setAttribute(ConstDataUtil.CURRENT_USER, userInfoPojo);
        return ResultDto.success(userInfoPojo);
    }
    @RequestMapping("web/logout")
    public ResultDto logout(HttpSession session){
         session.invalidate();
         return  ResultDto.success();

    }
}
