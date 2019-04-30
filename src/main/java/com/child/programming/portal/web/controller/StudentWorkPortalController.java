package com.child.programming.portal.web.controller;


import com.child.programming.base.dto.LoginedUserInfoDto;
import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.dto.StudentWorkInfoDto;
import com.child.programming.base.model.TbStudentWorkDo;
import com.child.programming.base.service.IStudentWorkService;
import com.child.programming.base.util.ConstDataUtil;
import com.child.programming.base.util.HttpSessionUtil;
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
public class StudentWorkPortalController {

    @Autowired
    private IStudentWorkService studentWorkService;

    /**
     * 发布Scratch 作品
     * @param request
     * @param session
     * @return
     */
    @RequestMapping("uploadScratch")
    public ResultDto upload(HttpServletRequest request, HttpSession session) {

        return studentWorkService.uploadScratch(request,session);
    }

    /***
     * 将作品Id 封装到SessionKey 中
     * @param studentWorkId
     * @param session
     * @return
     */
    @RequestMapping("sessionKeyUpdate")
    public ResultDto sessionKeyUpdate(String studentWorkId, HttpSession session) {
        return studentWorkService.sessionKeyUpdate(studentWorkId,session)?ResultDto.success():ResultDto.fail();
    }

    /***
     * 学生作品列表
     * @return
     */
   @RequestMapping("studentWorkGetList")
    public List<StudentWorkInfoDto> getList(){
        return studentWorkService.getList("");
   }

}
