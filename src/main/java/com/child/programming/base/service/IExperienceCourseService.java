package com.child.programming.base.service;

import com.child.programming.base.model.TbExperienceCourseDo;
import com.child.programming.base.model.TbShareCircleDo;

import java.util.List;

public interface IExperienceCourseService {

    /**
     * @Description:    所有准备开课的体验课列表
     */
    List<TbExperienceCourseDo> getAllExperienceCourse();

    List<TbShareCircleDo> getShareCircleByStudentIdAndCourseId(int studentId, int experienceCourseId);

    int insertTbShareCircle(TbShareCircleDo shareCircleDo);

    List<TbShareCircleDo> getShareCircleByCourseIdAndShareCode(int experienceCourseId, String shareCodeText);

    int updateShareCodeCount(TbShareCircleDo tbShareCircleDo);
}
