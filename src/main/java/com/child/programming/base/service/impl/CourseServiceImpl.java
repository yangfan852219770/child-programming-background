package com.child.programming.base.service.impl;

import com.child.programming.app.web.dto.CourseArrange;
import com.child.programming.app.web.dto.HomePageHeighSerachParam;
import com.child.programming.base.mapper.CourseCustomMapper;
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
    private CourseCustomMapper courseCustomMapper;

    @Override
    public List<TbCourseDo> getClassNow(HomePageHeighSerachParam homePageHeighSerachParam) {
        Map map = new HashMap();
        int pageSize = Integer.parseInt(homePageHeighSerachParam.getLimit());
        int startRow = (Integer.parseInt(homePageHeighSerachParam.getPage()) - 1 ) * Integer.parseInt(homePageHeighSerachParam.getLimit());
        map.put("pageSize",pageSize);
        map.put("startRow",startRow);
        if (!StringUtils.isEmpty(homePageHeighSerachParam.getCourseName())){
            map.put("courseName","%"+homePageHeighSerachParam.getCourseName()+"%");
        }
        if (!StringUtils.isEmpty(homePageHeighSerachParam.getSelectSchoolId())){
            map.put("selectSchoolId",homePageHeighSerachParam.getSelectSchoolId());
        }
        if (!StringUtils.isEmpty(homePageHeighSerachParam.getTeacherId())){
            map.put("teacherId",homePageHeighSerachParam.getTeacherId());
        }
        if (!StringUtils.isEmpty(homePageHeighSerachParam.getLowPrice())){
            map.put("lowPrice",homePageHeighSerachParam.getLowPrice());
        }
        if (!StringUtils.isEmpty(homePageHeighSerachParam.getHeighPrice())){
            map.put("heighPrice",homePageHeighSerachParam.getHeighPrice());
        }
        if (!StringUtils.isEmpty(homePageHeighSerachParam.getLowDate())){
            map.put("lowDate",homePageHeighSerachParam.getLowDate());
        }
        if (!StringUtils.isEmpty(homePageHeighSerachParam.getHeighDate())){
            map.put("heighDate",homePageHeighSerachParam.getHeighDate());
        }
        List<TbCourseDo> tbCourseDos = courseCustomMapper.getClassNowByExample(map);
        return tbCourseDos;
    }

    @Override
    public List<CourseArrange> getCourseDetailByCourseId(int courseId) {
        return courseCustomMapper.getCourseDetailByCourseId(courseId);
    }


}
