package com.child.programming.portal.web.controller;


import com.child.programming.base.dto.LoginedUserInfoDto;
import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.dto.StudentInfoDto;
import com.child.programming.base.dto.StudentWorkInfoDto;
import com.child.programming.base.model.TbStudentWorkDo;
import com.child.programming.base.service.IStudentWorkService;
import com.child.programming.base.service.IUploadService;
import com.child.programming.base.util.ConstDataUtil;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.base.util.HttpSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private IUploadService iUploadService;
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
     * 将作品保存到数据库
     * @param tbStudentWorkDo
     * @param request
     * @param session
     * @return
     */
    @RequestMapping("studentWorkSave")
    public ResultDto studentWorkSave(TbStudentWorkDo tbStudentWorkDo,HttpServletRequest request, HttpSession session) {
        return studentWorkService.portalSave(tbStudentWorkDo,request,session);
    }

    /***
     * 上传作品封面
     * @param file
     * @param request
     * @param session
     * @return
     */
    @RequestMapping("uploadStudentWorkCover")
    public ResultDto uploadStudentWorkCover(MultipartFile file, HttpServletRequest request,HttpSession session) {
        StudentInfoDto studentInfoDto =HttpSessionUtil.getStudentInfoDto(session);
        if(EmptyUtils.objectIsEmpty(studentInfoDto))
            return ResultDto.fail("请登录之后上传");

        return iUploadService.uploadFile("studentWorkCover",file,request);
    }

    /***
     * 学生作品列表
     * @return
     */
   @RequestMapping("studentWorkGetList")
    public List<StudentWorkInfoDto> getList(){

       return studentWorkService.getPortalList();
   }
    @RequestMapping("currentStudentWorkGetList")
    public List<StudentWorkInfoDto> getCurrentStudentWorkGetList(String workName,HttpSession session){

        return studentWorkService.getCurrentStudentWorkGetList(workName,session);
    }
    /***
     * 通过id
     * @return
     */
    @RequestMapping("studentWorkGetOneById")
    public StudentWorkInfoDto studentWorkGetOneById(Integer studentWorkId){

        return studentWorkService.getOneById(studentWorkId);
    }

    @RequestMapping("currentStudentWorkDel")
    public ResultDto currentStudentWorkDel(String ids,HttpSession session){
        StudentInfoDto studentInfoDto = HttpSessionUtil.getStudentInfoDto(session);
        if(EmptyUtils.objectIsEmpty(studentInfoDto))
            return ResultDto.fail("请登录后删除！");
        if(EmptyUtils.stringIsEmpty(ids))
            return  ResultDto.fail("请传入数据！");
        String[] idArr=ids.split(",");
        return studentWorkService.delete(idArr,studentInfoDto.getId())?ResultDto.success("删除成功！"):ResultDto.fail("删除失败！");
    }

    @RequestMapping("getStudentWorkInSession")
    public StudentWorkInfoDto getStudentWorkInSession(HttpSession session){
        return  studentWorkService.getStudentWorkInSession(session);
    }
}
