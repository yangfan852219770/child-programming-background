package com.child.programming.base.service.impl;

import com.child.programming.app.web.dto.CourseArrange;
import com.child.programming.app.web.dto.HomePageHeighSerachParam;
import com.child.programming.app.web.dto.SignUpCourseDto;
import com.child.programming.base.dto.CourseInfoDto;
import com.child.programming.base.dto.GradeWeekendsScheduleDto;
import com.child.programming.base.mapper.CourseCustomMapper;
import com.child.programming.base.mapper.TbCourseDoMapper;
import com.child.programming.base.model.TbCourseDo;
import com.child.programming.base.model.TbCourseDoExample;
import com.child.programming.base.model.TbGradeDo;
import com.child.programming.base.service.ICourseService;
import com.child.programming.base.service.IGradeService;
import com.child.programming.base.util.*;
import com.child.programming.education.manage.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Time;
import java.util.*;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseCustomMapper courseCustomMapper;
    @Autowired
    private TbCourseDoMapper tbCourseDoMapper;
    @Autowired
    private IGradeService iGradeService;

    /**
     * @Description:    小程序首页课程列表展示，包括搜索，高级搜索
     * @param homePageHeighSerachParam 查询的参数
     */
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

    /**
     * @Description:    根据课程id查询小程序课程安排详细信息
     */
    @Override
    public List<CourseArrange> getCourseDetailByCourseId(int courseId) {
        List<CourseArrange> courseArrangesNew = new ArrayList<>();
        List<CourseArrange> courseArranges = courseCustomMapper.getCourseDetailByCourseId(courseId);
        for (CourseArrange courseArrange:courseArranges) {
            //解析json
            if (!StringUtils.isEmpty(courseArrange.getWeekendsSchedule())){
                String weekendsSchedule = courseArrange.getWeekendsSchedule();
                List<GradeWeekendsScheduleDto> weekendsSchedules = JSONUtil.parseArray(weekendsSchedule,GradeWeekendsScheduleDto.class);
                String weekendsScheduleString="";
                for (GradeWeekendsScheduleDto weekends:weekendsSchedules) {
                    String weekwnd = "周"+weekends.getDay()+" "+weekends.getStartHour()+"-"+weekends.getEndHour();
                    weekendsScheduleString = weekendsScheduleString + weekwnd +",";
                }
                weekendsScheduleString = weekendsScheduleString.substring(0,weekendsScheduleString.length() - 1);
                courseArrange.setWeekendsSchedule(weekendsScheduleString);
                courseArrangesNew.add(courseArrange);
            }
        }
        return courseArrangesNew;
    }

    /**
     * @Description:    根据学生ID查询报名的课程
     */
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

    /**
     * @Description:    根据课程ID查询课程信息
     */
    @Override
    public TbCourseDo getCourseById(int courseId) {
        return tbCourseDoMapper.selectByPrimaryKey(courseId);
    }

    /**
     * @Description:    查询学生的购课历史
     */
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

    /**
     * @Description:    查询学生的正在进行的课程信息
     */
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

    /**
     * @Description:    根据日期查询学生的课程信息列表
     */
    @Override
    public List<SignUpCourseDto> getStudentCourseListByDate(String selectDate, String week, String studentId) {
        Map map = new HashMap();
        map.put("selectDate",selectDate);
        map.put("week","%"+week+"%");
        map.put("studentId",studentId);
        List<SignUpCourseDto> studentCourseListByDate = courseCustomMapper.getStudentCourseListByDate(map);
        return studentCourseListByDate;
    }

    /**
     *  @Description:    查询学生收藏课程
     */
    @Override
    public List<TbCourseDo> getStudentCollectCourseList(int page, int limit, int studentId) {
        Map map = new HashMap();
        map.put("pageSize",limit);
        map.put("startRow",(page-1) * limit);
        map.put("studentId",studentId);
        List<TbCourseDo> courseDos = courseCustomMapper.getStudentCollectCourseList(map);
        return courseDos;
    }

    /********************************************************************/

    @Override
    public List<CourseSaveDto> getList(String name) {
        TbCourseDoExample example = new TbCourseDoExample();
        example.setOrderByClause("create_time desc");
        TbCourseDoExample.Criteria criteria = example.createCriteria();
        //TODO status值查询匹配
        if (EmptyUtils.stringIsNotEmpty(name))
            criteria.andNameLike("%" + name + "%");
        List<TbCourseDo> courseDoList = tbCourseDoMapper.selectByExample(example);
        if (EmptyUtils.listIsNotEmpty(courseDoList)) {
            List<CourseSaveDto> courseSaveDtoList = ListUtil.convertElement(courseDoList, CourseSaveDto.class);
            // 时间安排
            initTimeSchedule(courseSaveDtoList);

            return courseSaveDtoList;
        }
        return null;
    }

    @Override
    public Boolean save(Integer userId, TbCourseDo courseDo, List<CourseTimeScheduleDto> courseTimeScheduleDtoList) {
        if (!EmptyUtils.objectIsEmpty(courseDo) && EmptyUtils.listIsNotEmpty(courseTimeScheduleDtoList)){
            Integer courseSaveResult = -1;
            // 容量
            Integer capacity = 0;
            // TODO 之后转为sql语句统计容量
            for (CourseTimeScheduleDto timeSchedule:courseTimeScheduleDtoList
            ) {
                TbGradeDo gradeDo = iGradeService.getOneById(timeSchedule.getGradeId());
                if(!EmptyUtils.objectIsEmpty(gradeDo)){
                    capacity +=gradeDo.getMaxCapacity();
                }

            }
            courseDo.setMaxCapacity(capacity);

            // 新增
            if (EmptyUtils.objectIsEmpty(courseDo.getId())){
                // 课程编码六位随机数，无实际含义
                courseDo.setCode(UuidUtils.getStochastic(6));
                courseDo.setStatus(1); // 报名
                courseDo.setCreateTime(new Date());
                courseDo.setCreateId(userId);
                // 插入返回主键
                courseSaveResult = tbCourseDoMapper.insert(courseDo);
                if (courseSaveResult != 1)
                    return false;

            }else{
                // 更新
                courseDo.setLastUpdateTime(new Date());
                courseDo.setLastUpdateId(userId);
                courseSaveResult = tbCourseDoMapper.updateByPrimaryKeySelective(courseDo);
                if (courseSaveResult != 1)
                    return false;
            }
            if (courseSaveResult == 1){
                // 班级时间安排保存
                Boolean updateResult = iGradeService.updateTimeSchedule(courseDo.getId(), userId, courseTimeScheduleDtoList);
                return updateResult;
            }
        }
        return false;
    }

    @Override
    public Boolean updateCourse(Integer userId, TbCourseDo courseDo) {
        if (!EmptyUtils.objectIsEmpty(courseDo) && EmptyUtils.intIsNotEmpty(courseDo.getId())){
            courseDo.setLastUpdateId(userId);
            courseDo.setLastUpdateTime(new Date());
            return tbCourseDoMapper.updateByPrimaryKeySelective(courseDo) == 1;
        }
        return false;
    }

    /**
     * 查询列表时，对时间安排赋值
     * @param courseSaveDtoList
     */
    private void initTimeSchedule(List<CourseSaveDto> courseSaveDtoList){
        if (EmptyUtils.listIsEmpty(courseSaveDtoList))
            return;
        for (CourseSaveDto course:courseSaveDtoList
        ) {
            List<TbGradeDo> gradeDoList = iGradeService.getListByCourseId(course.getId());
            if (EmptyUtils.listIsEmpty(gradeDoList))
                return;
            List<CourseTimeScheduleDto> courseTimeScheduleDtoList = new ArrayList<>();
            for (TbGradeDo grade:gradeDoList
                 ) {
                courseTimeScheduleDtoList.add(iGradeService.convertToCourseTimeSchedule(grade));
            }
            course.setTimeSchedule(courseTimeScheduleDtoList);
        }
    }

    /******************************************************************/
}
