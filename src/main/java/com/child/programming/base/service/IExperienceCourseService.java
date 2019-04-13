package com.child.programming.base.service;

import com.child.programming.base.model.TbExperienceCourseDo;

import java.util.List;

public interface IExperienceCourseService {

    /**
     * @Description:    所有准备开课的体验课列表
     */
    List<TbExperienceCourseDo> getAllExperienceCourse();
}
