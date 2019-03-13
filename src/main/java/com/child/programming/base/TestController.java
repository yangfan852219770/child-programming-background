package com.child.programming.base;

import com.child.programming.base.mapper.TbClassroomDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description：
 * @Author：yangfan
 **/
@Controller
public class TestController {
    @Autowired
    private TbClassroomDtoMapper classroomDtoMapper;

    @RequestMapping("test")
    public String test(){
        System.out.println("test");
        return "test";
    }
}
