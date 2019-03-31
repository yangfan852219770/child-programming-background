package com.child.programming.base.service.impl;

import com.child.programming.base.mapper.TbCourseDoMapper;
import com.child.programming.base.model.TbCourseDo;
import com.child.programming.base.model.TbCourseDoExample;
import com.child.programming.base.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private TbCourseDoMapper tbCourseDoMapper;

    @Override
    public List<TbCourseDo> getClassNow(int limit, int page) {
        TbCourseDoExample example = new TbCourseDoExample();
        example.setPageSize(limit);
        example.setStartRow((page - 1 ) * limit );
        List<TbCourseDo> tbCourseDos = tbCourseDoMapper.getClassNowByExample(example);
        return tbCourseDos;
    }



}
