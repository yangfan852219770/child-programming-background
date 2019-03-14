package com.child.programming.base;

import com.child.programming.base.mapper.TbClassroomDtoMapper;
import com.child.programming.base.model.TbClassroomDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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

    /**
     * 从request的body中读取payload数据，
     * 可直接用注解@RequestBody，但是需要有相应的对象或者用map
     * @param classroomDto
     * @return
     */
    @RequestMapping(value = "testBody", method = RequestMethod.POST)
    @ResponseBody
    public TbClassroomDto testBody(@RequestBody TbClassroomDto classroomDto){
        return classroomDto;
    }

}
