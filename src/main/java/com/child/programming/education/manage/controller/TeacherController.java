package com.child.programming.education.manage.controller;

import com.child.programming.base.dto.LoginedUserInfoDto;
import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.dto.ShiroDto;
import com.child.programming.base.dto.TeacherInfoDto;
import com.child.programming.base.model.TbTeacherDo;
import com.child.programming.base.service.IGradeService;
import com.child.programming.base.service.ITeacherService;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.base.util.HttpSessionUtil;
import com.child.programming.base.util.ResponseUtil;
import com.child.programming.education.manage.dto.ValidateDeleteDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description：老师管理
 * @Author：yangfan
 **/

@RestController
@RequestMapping("/teacher")
@Log4j2
public class TeacherController {

    @Autowired
    private ITeacherService iTeacherService;
    @Autowired
    private IGradeService iGradeService;

    /**
     * 列表
     * @param name 老师名称
     * @return
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    public List<TeacherInfoDto> getList(@RequestParam(value = "name", required = false) String name){
        return iTeacherService.getList(name);
    }


    /**
     * 新增和编辑保存
     * @param teacherDo
     * @param session
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ResultDto save(HttpSession session, @RequestBody TbTeacherDo teacherDo){
        LoginedUserInfoDto userInfoPojo = HttpSessionUtil.getLoginedUserInfo(session);
        if (null != userInfoPojo && null != teacherDo){

            // 登陆账号可用否校验
            boolean validateResult = iTeacherService.validateLoginId(teacherDo.getId(), teacherDo.getLoginId());
            if (!validateResult)
                return ResultDto.fail("该账号已被占用!");
            boolean result = iTeacherService.save(teacherDo,userInfoPojo.getId());
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
        if (null != userInfoPojo && !StringUtils.isEmpty(idsStr)) {
            String[] idArray = idsStr.split(",");
            // 删除前占用校验
            List<ValidateDeleteDto> validateDeleteDtoList = iGradeService.validateByTeacherIds(idArray);
            if (EmptyUtils.listIsNotEmpty(validateDeleteDtoList))
                return ResultDto.fail(ResponseUtil.FAIL_MSG, validateDeleteDtoList);
            boolean result = iTeacherService.delete(idArray, userInfoPojo.getId());
            if (result)
                return ResultDto.success();
        }
        return ResultDto.fail();
    }

    @RequestMapping(value = "resetPassword", method = RequestMethod.GET)
    public ResultDto resetPassword(@RequestParam(value = "teacherId", required = true)Integer teacherId
            ,HttpSession session) {
        LoginedUserInfoDto userInfoPojo = HttpSessionUtil.getLoginedUserInfo(session);
        if (null == userInfoPojo)
            return ResultDto.fail();

        boolean result = iTeacherService.resetPassword(teacherId, userInfoPojo.getId());
        if (result){
            log.warn(userInfoPojo.getName() + "将老师id为:" + teacherId + "重置默认密码");
            return ResultDto.success();
        }
        return ResultDto.fail();
    }

    /**
     * 通过loginId 的到教师信息
     * @param loginId 登录账号
     * @return
     */
    @RequestMapping(value = "getTeacherByLoginId", method = RequestMethod.GET)
    public ShiroDto getTeacherByLoginId(@RequestParam(value = "loginId", required = false) String loginId){
        return iTeacherService.getTeacherByLoginId(loginId);
    }
}
