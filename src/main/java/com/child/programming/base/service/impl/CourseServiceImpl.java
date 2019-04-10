package com.child.programming.base.service.impl;

import com.child.programming.app.web.dto.CourseArrange;
import com.child.programming.app.web.dto.HomePageHeighSerachParam;
import com.child.programming.app.web.dto.SignUpCourseDto;
import com.child.programming.app.web.dto.WeekendsSchedule;
import com.child.programming.base.dto.CourseInfoDto;
import com.child.programming.base.mapper.CourseCustomMapper;
import com.child.programming.base.mapper.TbCourseDoMapper;
import com.child.programming.base.model.TbCourseDo;
import com.child.programming.base.model.TbCourseDoExample;
import com.child.programming.base.service.ICourseService;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.base.util.JSONUtil;
import com.child.programming.base.util.ListUtil;
import org.apache.logging.log4j.core.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseCustomMapper courseCustomMapper;
    @Autowired
    private TbCourseDoMapper tbCourseDoMapper;

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
        List<CourseArrange> courseArrangesNew = new ArrayList<>();
        List<CourseArrange> courseArranges = courseCustomMapper.getCourseDetailByCourseId(courseId);
        for (CourseArrange courseArrange:courseArranges) {
            //解析json
            if (!StringUtils.isEmpty(courseArrange.getWeekendsSchedule())){
                String weekendsSchedule = courseArrange.getWeekendsSchedule();
                List<WeekendsSchedule> weekendsSchedules = JSONUtil.parseArray(weekendsSchedule,WeekendsSchedule.class);
                String weekendsScheduleString="";
                for (WeekendsSchedule weekends:weekendsSchedules) {
                    String weekwnd = "星期"+weekends.getData()+" "+weekends.getStart_hour()+"-"+weekends.getEnd_hour();
                    weekendsScheduleString=weekendsScheduleString+weekwnd+",";
                }
                weekendsScheduleString = weekendsScheduleString.substring(0,weekendsScheduleString.length() - 1);
                courseArrange.setWeekendsSchedule(weekendsScheduleString);
                courseArrangesNew.add(courseArrange);
            }
        }
        return courseArrangesNew;
    }

    @Override
    public List<CourseInfoDto> getList(String name) {
        TbCourseDoExample example = new TbCourseDoExample();
        example.setOrderByClause("create_time desc");
        TbCourseDoExample.Criteria criteria = example.createCriteria();
        //TODO status值查询匹配
        if (EmptyUtils.stringIsNotEmpty(name))
            criteria.andNameLike("%" + name + "%");
        List<TbCourseDo> courseDoList = tbCourseDoMapper.selectByExample(example);
        if (EmptyUtils.listIsNotEmpty(courseDoList))
            return ListUtil.convertElement(courseDoList, CourseInfoDto.class);

        return null;
    }

    @Override
    public List<SignUpCourseDto> getStudentSignUpCourseList(int page, int limit, String studentId) {
        Map map = new HashMap();
        int pageSize = limit;
        int startRow = (page-1) * limit;
        map.put("pageSize",pageSize);
        map.put("startRow",startRow);
        map.put("studentId",studentId);
        List<SignUpCourseDto> signUpCourseDtos = courseCustomMapper.getStudentSignUpCourseList(map);
        return signUpCourseDtos;
    }

    @Override
    public TbCourseDo getCourseById(int courseId) {
        return tbCourseDoMapper.selectByPrimaryKey(courseId);
    }

    @Override
    public List<SignUpCourseDto> getStudentSignUpCourseHistoryList(int page, int limit, String studentId) {
        Map map = new HashMap();
        int pageSize = limit;
        int startRow = (page-1) * limit;
        map.put("pageSize",pageSize);
        map.put("startRow",startRow);
        map.put("studentId",studentId);
        List<SignUpCourseDto> signUpCourseHistoryDtos = courseCustomMapper.getStudentSignUpCourseHistoryList(map);
        return signUpCourseHistoryDtos;
    }

    @Override
    public List<SignUpCourseDto> getStudentCourseClassList(int page, int limit, String studentId) {
        Map map = new HashMap();
        int pageSize = limit;
        int startRow = (page-1) * limit;
        map.put("pageSize",pageSize);
        map.put("startRow",startRow);
        map.put("studentId",studentId);
        List<SignUpCourseDto> signUpCourseHistoryDtos = courseCustomMapper.getStudentCourseClassList(map);
        return signUpCourseHistoryDtos;
    }
}
