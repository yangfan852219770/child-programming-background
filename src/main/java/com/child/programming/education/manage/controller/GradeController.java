package com.child.programming.education.manage.controller;

import com.child.programming.base.dto.GradeInfoDto;
import com.child.programming.base.dto.LoginedUserInfoDto;
import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.model.TbCourseDo;
import com.child.programming.base.model.TbGradeDo;
import com.child.programming.base.service.IGradeService;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.base.util.HttpSessionUtil;
import com.child.programming.education.manage.dto.InitGradeInfoDto;
import com.child.programming.education.manage.dto.SelectDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description：班级管理
 * @Author：yangfan
 **/

@RestController
@RequestMapping("/grade")
@Log4j2
public class GradeController {
    @Autowired
    private IGradeService iGradeService;

    /**
     * 列表
     * @param name 班级名称
     * @return
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    public List<GradeInfoDto> getList(@RequestParam(value = "name", required = false) String name){
        Map map = new HashMap<>();
        map.put("name",name);
        return iGradeService.getList(map);
    }

    /**
     * 添加、编辑时，初始化校区、教室、老师select框
     * @return
     */
    @RequestMapping(value = "initGrade", method = RequestMethod.GET)
    public InitGradeInfoDto initGradeInfo(){
        return iGradeService.initGradeInfo();
    }

    /**
     * 新增、编辑
     * @param session
     * @param gradeDo
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ResultDto save(HttpSession session, @RequestBody TbGradeDo gradeDo){
        LoginedUserInfoDto userInfoPojo = HttpSessionUtil.getLoginedUserInfo(session);
        if (null != userInfoPojo && null != gradeDo){
            Integer value = iGradeService.validateCapacity(gradeDo.getClassroomId(), gradeDo.getMaxCapacity());
            // 可以添加
            if (value == -1) {
                boolean result = iGradeService.save(gradeDo,userInfoPojo.getId());
                if (result)
                    return ResultDto.success();
            }
            // 无法添加
            if (value > 0) {
                return ResultDto.fail("教室最大容量为:"+value);
            }

        }

        return ResultDto.fail();
    }

    /**
     * 获取班级select框信息
     * @return
     */
    @RequestMapping(value = "getGradeInfoSelect", method = RequestMethod.GET)
    public List<SelectDto> getGradeInfoSelect(@RequestParam(value = "gradeIds", required = false)String gradeIds){
        return iGradeService.getGradeInfoSelectList(gradeIds);
    }

    /**
     * 逻辑删除
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
            Boolean validateResult = iGradeService.validateCourseId(idArray);
            if (!validateResult)
                return ResultDto.fail("该班有课程占用，无法删除!");
            boolean result = iGradeService.delete(idArray, userInfoPojo.getId());
            if (result)
                return ResultDto.success();
        }
        return ResultDto.fail();
    }
}
