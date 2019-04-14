package com.child.programming.portal.web.controller;


import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.service.IStudentWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author zdp
 * @description: TODO
 */
@RestController
@RequestMapping("worksPortal")
public class WorksController {

    @Autowired
    private IStudentWorkService studentWorkService;
    @RequestMapping("uploadScratch")
    public ResultDto upload(HttpServletRequest request, HttpSession session) {

        return studentWorkService.uploadScratch(request,session);

    }

}
