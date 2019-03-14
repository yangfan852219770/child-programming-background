package com.child.programming.education.manage.controller;

import com.child.programming.base.model.TbSchoolDto;
import com.child.programming.base.pojo.ResultPojo;
import com.child.programming.base.service.ISchoolService;
import com.child.programming.base.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description：
 * @Author：yangfan
 **/

@Controller
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    private ISchoolService iSchoolService;

    //TODO 测试
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResultPojo add(@RequestBody TbSchoolDto schoolDto){
        if (null != schoolDto){
            boolean result = iSchoolService.addOne(schoolDto);
            if (result)
              return ResultPojo.success(schoolDto);
        }
        return ResultPojo.error(ResponseUtil.ERROR_MSG);
    }
}
