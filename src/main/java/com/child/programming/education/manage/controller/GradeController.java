package com.child.programming.education.manage.controller;

import com.child.programming.base.dto.GradeInfoDto;
import com.child.programming.base.dto.LoginedUserInfoDto;
import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.model.TbGradeDo;
import com.child.programming.base.service.IGradeService;
import com.child.programming.base.util.HttpSessionUtil;
import com.child.programming.education.manage.dto.InitGradeInfoDto;
import com.child.programming.education.manage.dto.SelectDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
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
    //TODO 没有对容量进行校验
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ResultDto save(HttpSession session, @RequestBody TbGradeDo gradeDo){
        LoginedUserInfoDto userInfoPojo = HttpSessionUtil.getLoginedUserInfo(session);
        if (null != userInfoPojo && null != gradeDo){
            boolean result = iGradeService.save(gradeDo,userInfoPojo.getId());
            if (result)
                return ResultDto.success();
        }

        return ResultDto.fail();
    }

    /**
     * 获取班级select框信息
     * @return
     */
    @RequestMapping("getGradeInfoSelect")
    public List<SelectDto> getGradeInfoSelect(){
        return iGradeService.getGradeInfoSelectList();
    }
}
