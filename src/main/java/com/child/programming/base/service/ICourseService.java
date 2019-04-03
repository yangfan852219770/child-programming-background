package com.child.programming.base.service;

import com.child.programming.app.web.dto.HomePageHeighSerachParam;
import com.child.programming.base.model.TbCourseDo;

import java.util.List;

public interface ICourseService {
    List<TbCourseDo> getClassNow(HomePageHeighSerachParam homePageHeighSerachParam);
}
