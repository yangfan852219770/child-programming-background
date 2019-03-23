package com.child.programming.education.manage.controller;

import com.child.programming.base.model.TbClassroomDto;
import com.child.programming.base.pojo.LoginedUserInfoPojo;
import com.child.programming.base.pojo.ResultPojo;
import com.child.programming.base.service.IClassroomService;
import com.child.programming.base.util.HttpSessionUtil;
import com.child.programming.base.util.ResponseUtil;
import com.child.programming.education.manage.pojo.ClassroomInfoPojo;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ClassroomController {
    @Autowired
    private IClassroomService iClassroomService;

    /**
     * 查询
     * @return
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    @ResponseBody
    public List<ClassroomInfoPojo> getList(@RequestParam(value = "schoolId", required = false) Integer schoolId){
        Map<String, Integer> map = new HashMap<>();
        map.put("schoolId",schoolId);
        return iClassroomService.getList(map);
    }


    /**
     * 新增和编辑保存
     * @param classroomDto
     * @param session
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public ResultPojo save(HttpSession session, @RequestBody TbClassroomDto classroomDto){
        LoginedUserInfoPojo userInfoPojo = HttpSessionUtil.getLoginedUserInfo(session);
        if (null != userInfoPojo && null != classroomDto){
            boolean result = iClassroomService.save(classroomDto,userInfoPojo.getId());
            if (result)
                return ResultPojo.success(classroomDto);
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
            boolean result = iClassroomService.delete(idArray, userInfoPojo.getId());
            if (result)
                return ResultPojo.success(idsStr);
        }
        return ResultPojo.error(ResponseUtil.ERROR_MSG);
    }
}
