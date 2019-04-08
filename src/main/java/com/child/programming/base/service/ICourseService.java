package com.child.programming.base.service;

import com.child.programming.app.web.dto.CourseArrange;
import com.child.programming.app.web.dto.HomePageHeighSerachParam;
import com.child.programming.base.dto.CourseInfoDto;
import com.child.programming.base.model.TbCourseDo;

import java.util.List;

public interface ICourseService {
    List<TbCourseDo> getClassNow(HomePageHeighSerachParam homePageHeighSerachParam);

    List<CourseArrange> getCourseDetailByCourseId(int courseId);

    /**
     * antd 列表
     * @param name 课程名称
     * @return
     */
    List<CourseInfoDto> getList(String name);
}
