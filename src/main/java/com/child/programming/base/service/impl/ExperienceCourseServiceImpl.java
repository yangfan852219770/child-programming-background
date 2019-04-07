package com.child.programming.base.service.impl;

import com.child.programming.base.mapper.TbExperienceCourseDoMapper;
import com.child.programming.base.model.TbExperienceCourseDo;
import com.child.programming.base.model.TbExperienceCourseDoExample;
import com.child.programming.base.service.IExperienceCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceCourseServiceImpl implements IExperienceCourseService {

    @Autowired
    private TbExperienceCourseDoMapper experienceCourseDoMapper;

    @Override
    public List<TbExperienceCourseDo> getAllExperienceCourse() {
        TbExperienceCourseDoExample example = new TbExperienceCourseDoExample();
        example.setOrderByClause("sign_up_end_date asc");
        TbExperienceCourseDoExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo("1");
        return experienceCourseDoMapper.selectByExample(example);
    }
}
