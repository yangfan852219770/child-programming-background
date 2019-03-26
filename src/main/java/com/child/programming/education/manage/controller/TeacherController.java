package com.child.programming.education.manage.controller;

import com.child.programming.base.dto.LoginedUserInfoDto;
import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.dto.TeacherInfoDto;
import com.child.programming.base.model.TbTeacherDo;
import com.child.programming.base.service.ITeacherService;
import com.child.programming.base.util.HttpSessionUtil;
import com.child.programming.base.util.ResponseUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description：
 * @Author：yangfan
 **/

@Controller
@RequestMapping("/teacher")
@Log4j2
public class TeacherController {

    @Autowired
    private ITeacherService iTeacherService;

    /**
     * 查询
     * @return
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    @ResponseBody
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
    @ResponseBody
    public ResultDto save(HttpSession session, @RequestBody TbTeacherDo teacherDo){
        LoginedUserInfoDto userInfoPojo = HttpSessionUtil.getLoginedUserInfo(session);
        if (null != userInfoPojo && null != teacherDo){
            boolean result = iTeacherService.save(teacherDo,userInfoPojo.getId());
            if (result)
                return ResultDto.success();
        }

        return ResultDto.error(ResponseUtil.ERROR_MSG);
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
        if (null != userInfoPojo && !StringUtils.isEmpty(idsStr)) {
            String[] idArray = idsStr.split(",");
            boolean result = iTeacherService.delete(idArray, userInfoPojo.getId());
            if (result)
                return ResultDto.success();
        }
        return ResultDto.error(ResponseUtil.ERROR_MSG);
    }
}
