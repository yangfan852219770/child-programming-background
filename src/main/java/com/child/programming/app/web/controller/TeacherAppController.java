package com.child.programming.app.web.controller;

import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.service.ITeacherService;
import com.child.programming.base.util.ResponseUtil;
import com.child.programming.education.manage.dto.SelectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * @Description:    教师信息
 * @Author:         赵赞峰
 * @Version:        1.0
 */
@RestController
@RequestMapping("app/web/teacher")
public class TeacherAppController {

    @Autowired
    private ITeacherService iTeacherService;

    /**
     * @Description:    教师列表
     */
    @RequestMapping("getTeacherList")
    public ResultDto getTeacherList(){
        List<SelectDto> selectDtos = iTeacherService.getTeacherSelectList();
        if (selectDtos!=null && selectDtos.size()>0){
            return ResultDto.success(selectDtos);
        }
        return new ResultDto(ResponseUtil.FAIL_0,"获取课程列表失败");
    }



}
