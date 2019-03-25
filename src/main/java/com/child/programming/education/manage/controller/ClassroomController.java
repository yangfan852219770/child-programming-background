package com.child.programming.education.manage.controller;

import com.child.programming.base.dto.LoginedUserInfoDto;
import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.model.TbClassroomDo;
import com.child.programming.base.service.IClassroomService;
import com.child.programming.base.util.HttpSessionUtil;
import com.child.programming.base.util.ResponseUtil;
import com.child.programming.education.manage.dto.ClassroomInfoDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description：
 * @Author：yangfan
 **/

@Controller
@RequestMapping("/classroom")
@Log4j2
public class ClassroomController {
    @Autowired
    private IClassroomService iClassroomService;

    /**
     * 查询
     * @return
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    @ResponseBody
    public List<ClassroomInfoDto> getList(@RequestParam(value = "schoolId", required = false) Integer schoolId){
        Map<String, Integer> map = new HashMap<>();
        map.put("schoolId",schoolId);
        return iClassroomService.getList(map);
    }


    /**
     * 新增和编辑保存
     * @param classroomDo
     * @param session
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public ResultDto save(HttpSession session, @RequestBody TbClassroomDo classroomDo){
        LoginedUserInfoDto userInfoPojo = HttpSessionUtil.getLoginedUserInfo(session);
        if (null != userInfoPojo && null != classroomDo){
            boolean result = iClassroomService.save(classroomDo,userInfoPojo.getId());
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
            boolean result = iClassroomService.delete(idArray, userInfoPojo.getId());
            if (result)
                return ResultDto.success();
        }
        return ResultDto.error(ResponseUtil.ERROR_MSG);
    }
}
