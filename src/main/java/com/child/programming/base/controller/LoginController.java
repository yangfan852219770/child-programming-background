package com.child.programming.base.controller;

import com.child.programming.base.pojo.LoginedUserInfoPojo;
import com.child.programming.base.pojo.ResultPojo;
import com.child.programming.base.pojo.WebLoginInfoPojo;
import com.child.programming.base.util.ConstDataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @Description：登陆逻辑，包括web和小程序
 * @Author：yangfan
 **/
@Controller
@Slf4j
public class LoginController {


    /**
     * web端的登陆
     * @return
     */
    //TODO 目前并不完整
    @RequestMapping("web/login")
    @ResponseBody
    public ResultPojo webLogin(HttpSession session,
                               @RequestBody WebLoginInfoPojo webLoginInfoPojo){

        log.info(webLoginInfoPojo + "登陆");

        LoginedUserInfoPojo userInfoPojo = new LoginedUserInfoPojo();

        userInfoPojo.setId(1);
        userInfoPojo.setLoginId("123465");
        userInfoPojo.setUserName("admin");
        userInfoPojo.setCurrentAuthority("admin");

        session.setAttribute(ConstDataUtil.CURRENT_USER, userInfoPojo);

        return ResultPojo.success(userInfoPojo);
    }
}
