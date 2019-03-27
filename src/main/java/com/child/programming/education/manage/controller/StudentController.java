package com.child.programming.education.manage.controller;

import com.child.programming.base.dto.LoginedUserInfoDto;
import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.dto.StudentInfoDto;
import com.child.programming.base.model.TbStudentDo;
import com.child.programming.base.service.IStudentService;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.base.util.HttpSessionUtil;
import com.child.programming.base.util.ResponseUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author zdp
 * @description: TODO
 */
@RestController
@RequestMapping("/student")
@Log4j2
public class StudentController {

    @Autowired
    private IStudentService studentService;

    /**
     * 查询学生信息
     * @param name
     * @return
     */
    @RequestMapping("getList")
    public List<StudentInfoDto>  getList(@RequestParam(value = "name",required = false)String name){

        return studentService.getList(name);

    }
    /**
     * 新增和编辑保存
     * @param studentDo
     * @param session
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ResultDto save(HttpSession session, @RequestBody TbStudentDo studentDo){
        LoginedUserInfoDto userInfoPojo = HttpSessionUtil.getLoginedUserInfo(session);
        if (!EmptyUtils.objectIsEmpty(userInfoPojo) && !EmptyUtils.objectIsEmpty(studentDo)){
            boolean result = studentService.save(studentDo,userInfoPojo.getId());
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
    public ResultDto delete(@RequestParam(value = "idsStr", required = true)String idsStr,
                            HttpSession session) {
        log.info(idsStr + "删除");
        LoginedUserInfoDto userInfoPojo = HttpSessionUtil.getLoginedUserInfo(session);
        if (!EmptyUtils.objectIsEmpty(userInfoPojo) && !EmptyUtils.stringIsEmpty(idsStr)) {
            String[] idArray = idsStr.split(",");
            boolean result = studentService.delete(idArray, userInfoPojo.getId());
            if (result)
                return ResultDto.success();
        }
        return ResultDto.fail();
    }
}
