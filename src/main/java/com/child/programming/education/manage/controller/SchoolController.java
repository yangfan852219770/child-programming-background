package com.child.programming.education.manage.controller;

import com.child.programming.base.model.TbSchoolDto;
import com.child.programming.base.pojo.LoginedUserInfoPojo;
import com.child.programming.base.pojo.ResultPojo;
import com.child.programming.base.service.ISchoolService;
import com.child.programming.base.util.HttpSessionUtil;
import com.child.programming.base.util.ResponseUtil;
import com.child.programming.education.manage.pojo.AntSchoolInfoSelectPojo;
import com.child.programming.education.manage.service.IAntSchoolService;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/school")
@Slf4j
public class SchoolController {
    @Autowired
    private ISchoolService iSchoolService;
    @Autowired
    private IAntSchoolService iAntSchoolService;

    /**
     * 查询校区
     * @return
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    @ResponseBody
    public List<TbSchoolDto> getList(@RequestParam(value = "name", required = false) String name){
        List<TbSchoolDto> schoolDtoList = iSchoolService.getList(name);

        return schoolDtoList;
    }


    /**
     * 新增和编辑保存
     * @param schoolDto
     * @param session
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public ResultPojo save(HttpSession session, @RequestBody TbSchoolDto schoolDto){
        LoginedUserInfoPojo userInfoPojo = HttpSessionUtil.getLoginedUserInfo(session);
        if (null != userInfoPojo && null != schoolDto){
            boolean result = iSchoolService.save(schoolDto,userInfoPojo.getId());
            if (result)
                return ResultPojo.success(schoolDto);
        }

        return ResultPojo.error(ResponseUtil.ERROR_MSG);
    }

    /**
     * 删除
     * @param idsStr
     * @param session
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public ResultPojo delete(@RequestParam(value = "idsStr", required = true)String idsStr,
                             HttpSession session) {
        log.info(idsStr + "删除");

        LoginedUserInfoPojo userInfoPojo = HttpSessionUtil.getLoginedUserInfo(session);
        if (null != userInfoPojo && !StringUtils.isEmpty(idsStr)) {
            String[] idArray = idsStr.split(",");
            boolean result = iSchoolService.delete(idArray, userInfoPojo.getId());
            if (result)
                return ResultPojo.success(idsStr);
        }
        return ResultPojo.error(ResponseUtil.ERROR_MSG);
    }

    /**
     * 获取学校信息select框
     * @return
     */
    @RequestMapping("getSchoolInfoSelect")
    @ResponseBody
    public List<AntSchoolInfoSelectPojo> getSchoolInfoSelect(){
        return iAntSchoolService.getSchoolInfoSelectList();
    }
}
