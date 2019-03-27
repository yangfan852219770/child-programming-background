package com.child.programming.education.manage.controller;

import com.child.programming.base.dto.LoginedUserInfoDto;
import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.dto.SchoolInfoDto;
import com.child.programming.base.model.TbSchoolDo;
import com.child.programming.base.service.IClassroomService;
import com.child.programming.base.service.ISchoolService;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.base.util.HttpSessionUtil;
import com.child.programming.base.util.ResponseUtil;
import com.child.programming.education.manage.dto.SchoolInfoSelectDto;
import com.child.programming.education.manage.dto.ValidateClassroomInfoDto;
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

@RestController
@RequestMapping("/school")
@Log4j2
public class SchoolController {
    @Autowired
    private ISchoolService iSchoolService;
    @Autowired
    private IClassroomService iClassroomService;

    /**
     * 查询校区
     * @return
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    public List<SchoolInfoDto> getList(@RequestParam(value = "name", required = false) String name){
        return iSchoolService.getList(name);
    }


    /**
     * 新增和编辑保存
     * @param schoolDo
     * @param session
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ResultDto save(HttpSession session, @RequestBody TbSchoolDo schoolDo){
        LoginedUserInfoDto userInfoPojo = HttpSessionUtil.getLoginedUserInfo(session);
        if (null != userInfoPojo && null != schoolDo){
            boolean result = iSchoolService.save(schoolDo,userInfoPojo.getId());
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
            List<ValidateClassroomInfoDto> validateClassroomInfoDtoList = iClassroomService.validateSchoolId(idArray);
            //没有教室占用，可以删除
            if (EmptyUtils.listIsEmpty(validateClassroomInfoDtoList)){
                boolean result = iSchoolService.delete(idArray, userInfoPojo.getId());
                if (result)
                    return ResultDto.success();
            }
            //有教室占用，不能删除
            return ResultDto.fail(ResponseUtil.FAIL_MSG, validateClassroomInfoDtoList);
        }
        return ResultDto.fail();
    }

    /**
     * 获取学校信息select框
     * @return
     */
    @RequestMapping("getSchoolInfoSelect")
    public List<SchoolInfoSelectDto> getSchoolInfoSelect(){
        return iSchoolService.getSchoolInfoSelectList();
    }

}
