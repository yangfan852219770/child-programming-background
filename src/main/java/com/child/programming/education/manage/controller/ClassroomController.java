package com.child.programming.education.manage.controller;

import com.child.programming.base.dto.ClassroomDetailInfoDto;
import com.child.programming.base.dto.LoginedUserInfoDto;
import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.model.TbClassroomDo;
import com.child.programming.base.service.IClassroomService;
import com.child.programming.base.util.HttpSessionUtil;
import com.child.programming.base.util.ResponseUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description：教室管理
 * @Author：yangfan
 **/

@RestController
@RequestMapping("/classroom")
@Log4j2
public class ClassroomController {
    @Autowired
    private IClassroomService iClassroomService;

    /**
     * 列表
     * @param schoolId 校区id
     * @return
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    public List<ClassroomDetailInfoDto> getList(@RequestParam(value = "schoolId", required = false) Integer schoolId){
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
    public ResultDto save(HttpSession session, @RequestBody TbClassroomDo classroomDo){
        LoginedUserInfoDto userInfoPojo = HttpSessionUtil.getLoginedUserInfo(session);
        if (null != userInfoPojo && null != classroomDo){
            boolean validateResult = iClassroomService.validateCode(classroomDo.getCode());
            if (validateResult){
                boolean result = iClassroomService.save(classroomDo,userInfoPojo.getId());
                if (result)
                    return ResultDto.success();
            }else
                return ResultDto.fail("该教室编码已被占用!");

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
            boolean result = iClassroomService.delete(idArray, userInfoPojo.getId());
            if (result)
                return ResultDto.success();
        }
        return ResultDto.fail();
    }
}
