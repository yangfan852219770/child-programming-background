package com.child.programming.portal.web.controller;

import com.child.programming.base.dto.TeacherInfoDto;
import com.child.programming.base.service.ITeacherService;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.base.util.ListUtil;
import com.child.programming.portal.web.dto.TeacherInfoPortalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zdp
 * @description: TODO
 */
@RestController
@RequestMapping("teacherPortal")
public class TeacherPortalController {

    @Autowired
    private ITeacherService teacherService;
    @Value("${IMAGE.BASE.MANAGE.URL}")
    private String baseUrl;

    /**
     * 教师信息展示
     * @return
     */
    @RequestMapping("getList")
    public List<TeacherInfoPortalDto> getList(){

        List<TeacherInfoDto> tbTeacherDos=teacherService.getList("");
       if(!EmptyUtils.listIsEmpty(tbTeacherDos)){

           for (TeacherInfoDto teachDto:tbTeacherDos
                   ) {
               teachDto.setPhotoUrl(baseUrl+teachDto.getPhotoUrl());
           }

           return ListUtil.convertElement(tbTeacherDos,TeacherInfoPortalDto.class);
       }
        return null;
    }
}
