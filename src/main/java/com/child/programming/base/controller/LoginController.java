package com.child.programming.base.controller;

import com.child.programming.base.dto.LoginedUserInfoDto;
import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.dto.WebLoginInfoDto;
import com.child.programming.base.util.ConstDataUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @Description：登陆逻辑，包括web和小程序
 * @Author：yangfan
 **/
@Controller
@Log4j2
public class LoginController {


    /**
     * web端的登陆
     * @return
     */
    //TODO 目前并不完整
    @RequestMapping(value = "web/login/account", method = RequestMethod.POST)
    @ResponseBody
    public ResultDto webLogin(HttpSession session,
                              @RequestBody WebLoginInfoDto webLoginInfoDto){

        log.info(webLoginInfoDto + "登陆");

        LoginedUserInfoDto userInfoPojo = new LoginedUserInfoDto();

        userInfoPojo.setId(1);
        userInfoPojo.setLoginId("123465");
        userInfoPojo.setUserName("admin");
        userInfoPojo.setCurrentAuthority("admin");

        session.setAttribute(ConstDataUtil.CURRENT_USER, userInfoPojo);

        return ResultDto.success(userInfoPojo);
    }
}
