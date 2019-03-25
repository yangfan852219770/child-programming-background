package com.child.programming.education.manage.controller;

import com.child.programming.base.dto.LoginedUserInfoDto;
import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.model.TbSchoolDo;
import com.child.programming.base.service.ISchoolService;
import com.child.programming.base.util.HttpSessionUtil;
import com.child.programming.base.util.ResponseUtil;
import com.child.programming.education.manage.dto.SchoolInfoDto;
import com.child.programming.education.manage.dto.SchoolInfoSelectDto;
import com.child.programming.education.manage.service.IAntSchoolService;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
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
    @ResponseBody
    public ResultDto save(HttpSession session, @RequestBody TbSchoolDo schoolDo){
        LoginedUserInfoDto userInfoPojo = HttpSessionUtil.getLoginedUserInfo(session);
        if (null != userInfoPojo && null != schoolDo){
            boolean result = iSchoolService.save(schoolDo,userInfoPojo.getId());
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
            boolean result = iSchoolService.delete(idArray, userInfoPojo.getId());
            if (result)
                return ResultDto.success();
        }
        return ResultDto.error(ResponseUtil.ERROR_MSG);
    }

    /**
     * 获取学校信息select框
     * @return
     */
    @RequestMapping("getSchoolInfoSelect")
    @ResponseBody
    public List<SchoolInfoSelectDto> getSchoolInfoSelect(){
        return iAntSchoolService.getSchoolInfoSelectList();
    }
}
