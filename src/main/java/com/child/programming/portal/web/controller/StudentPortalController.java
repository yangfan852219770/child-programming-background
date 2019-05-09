package com.child.programming.portal.web.controller;


import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.dto.StudentWorkInfoDto;
import com.child.programming.base.service.IStudentService;
import com.child.programming.base.service.IStudentWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author zdp
 * @description: TODO
 */
@RestController
@RequestMapping("portal")
public class StudentPortalController {

    @Autowired
    private IStudentService studentService;

    /**
     *
     * @param loginId
     * @param password
     * @return
     */
    @RequestMapping("studentLogin")
    public ResultDto studentLogin(String loginId,String password,HttpSession session) {

        return studentService.studentLogin(loginId,password,session);
    }

    /**
     * 退出
     * @param session
     * @return
     */
    @RequestMapping("studentLogout")
    public ResultDto studentLogin(HttpSession session) {

        return studentService.studentLogout(session);

    }

}
