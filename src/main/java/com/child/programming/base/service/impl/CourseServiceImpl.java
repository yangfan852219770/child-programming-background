package com.child.programming.base.service.impl;

import com.child.programming.base.mapper.TbCourseDoMapper;
import com.child.programming.base.model.TbCourseDo;
import com.child.programming.base.model.TbCourseDoExample;
import com.child.programming.base.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private TbCourseDoMapper tbCourseDoMapper;

    @Override
    public List<TbCourseDo> getClassNow(int limit, int page,String courseName) {
        Map map = new HashMap();
        int pageSize = limit;
        int startRow = (page - 1 ) * limit;
        map.put("pageSize",pageSize);
        map.put("startRow",startRow);
        if (!StringUtils.isEmpty(courseName)){
            map.put("courseName","%"+courseName+"%");
        }
        List<TbCourseDo> tbCourseDos = tbCourseDoMapper.getClassNowByExample(map);
        return tbCourseDos;
    }



}
