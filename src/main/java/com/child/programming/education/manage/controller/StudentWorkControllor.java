package com.child.programming.education.manage.controller;

import com.child.programming.base.dto.*;
import com.child.programming.base.model.TbStudentWorkDo;
import com.child.programming.base.service.IStudentWorkService;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.base.util.HttpSessionUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author zdp
 * @description: TODO
 */
@Controller
@Log4j2
@RequestMapping("/studentWork")
public class StudentWorkControllor {

    @Autowired
    private IStudentWorkService studentWorkService;



    /**
     * 查询作品列表
     * @param name
     * @return
     */
    @RequestMapping("getList")
    @ResponseBody
    public List<StudentWorkInfoDto> getList(@RequestParam(value = "name",required = false)String name){

        return studentWorkService.getList(name);

    }

    /***
     * 推送商品
     * @param session
     * @param tbStudentWorkDo
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public ResultDto pushStudentWork(HttpSession session, @RequestBody TbStudentWorkDo tbStudentWorkDo) {
        LoginedUserInfoDto userInfoPojo = HttpSessionUtil.getLoginedUserInfo(session);
        if (!EmptyUtils.objectIsEmpty(userInfoPojo) && !EmptyUtils.objectIsEmpty(tbStudentWorkDo)){
            boolean result = studentWorkService.save(tbStudentWorkDo,userInfoPojo.getId());
            if (result)
                return ResultDto.success();
        }
        return ResultDto.fail();
    }

    /**
     * 删除
     * @param idsStr
     * @param session
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public ResultDto delete(@RequestParam(value = "idsStr", required = true)String idsStr,
                            HttpSession session) {
        log.info(idsStr + "删除");
        LoginedUserInfoDto userInfoPojo = HttpSessionUtil.getLoginedUserInfo(session);
        if (!EmptyUtils.objectIsEmpty(userInfoPojo) && !EmptyUtils.stringIsEmpty(idsStr)) {
            String[] idArray = idsStr.split(",");
            boolean result = studentWorkService.delete(idArray, userInfoPojo.getId());
            if (result)
                return ResultDto.success();
        }
        return ResultDto.fail();
    }

    @RequestMapping("/saveStudentWorkToSession")
    @ResponseBody
    private  void  saveStudentWorkToSession(@RequestBody StudentWorkInfoDto studentWorkInfoDto,HttpSession session){

        studentWorkService.saveStudentWorkToSession(studentWorkInfoDto,session);
    }
}
