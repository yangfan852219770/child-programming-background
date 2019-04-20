package com.child.programming.base.controller;

import com.child.programming.base.dto.LoginedUserInfoDto;
import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.dto.WebLoginInfoDto;
import com.child.programming.base.service.ITeacherService;
import com.child.programming.base.util.ConstDataUtil;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.base.util.MD5Util;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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

       /* LoginedUserInfoDto userInfoPojo = iTeacherService.getTeacherByLoginIdAndPassword(webLoginInfoDto.getLoginId(),webLoginInfoDto.getPassword());

        if(EmptyUtils.objectIsEmpty(userInfoPojo))
            return ResultDto.fail();

        session.setAttribute(ConstDataUtil.CURRENT_USER, userInfoPojo);*/

       if(EmptyUtils.objectIsEmpty(webLoginInfoDto))
           return ResultDto.fail("登陆异常，轻刷新页面后登陆！");
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(webLoginInfoDto.getLoginId(), MD5Util.MD5Encode(webLoginInfoDto.getPassword()));
        // 执行认证登陆
        try {
            subject.login(token);
            subject.isPermittedAll();

        } catch(IncorrectCredentialsException e){
            //这最好把 所有的 异常类型都背会
            return ResultDto.fail("密码错误");
        } catch (AuthenticationException e) {

            return ResultDto.fail("账号错误");
        }

        LoginedUserInfoDto loginedUserInfoDto=   (LoginedUserInfoDto)subject.getSession().getAttribute(ConstDataUtil.CURRENT_USER);
        System.out.println("当前用户角色为"+loginedUserInfoDto.getCurrentAuthority());
        return ResultDto.success(loginedUserInfoDto);
    }

    @RequestMapping("noRole")
    public ResultDto noRole(){

        return  ResultDto.fail("您尚未分配权限");

    }
    @RequestMapping("web/logout")
    public ResultDto logout(){

        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
         return  ResultDto.success();

    }
}
