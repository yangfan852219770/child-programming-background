package com.child.programming.base.service.impl;

import com.child.programming.base.mapper.TbTeacherCourseScheduleDoMapper;
import com.child.programming.base.mapper.TeacherCourseScheduleCustomMapper;
import com.child.programming.base.model.TbStudentSignUpDo;
import com.child.programming.base.model.TbTeacherCourseScheduleDo;
import com.child.programming.base.model.TbTeacherCourseScheduleDoExample;
import com.child.programming.base.service.ISignUpFormalCourseService;
import com.child.programming.base.service.ITeacherCourseScheduleService;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.base.util.ListUtil;
import com.child.programming.education.manage.dto.CourseScheduleDto;
import com.child.programming.education.manage.dto.TeacherCourseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description：
 * @Author：yangfan
 **/
@Service
public class TeacherCourseScheduleServiceImpl implements ITeacherCourseScheduleService {
    @Autowired
    private TbTeacherCourseScheduleDoMapper teacherCourseScheduleDoMapper;
    @Autowired
    private TeacherCourseScheduleCustomMapper teacherCourseScheduleCustomMapper;

    @Autowired
    private ISignUpFormalCourseService iSignUpFormalCourseService;

    @Override
    public Boolean generateSchedule(List<CourseScheduleDto> courseScheduleDtoList, Integer userId) {
        if (EmptyUtils.listIsEmpty(courseScheduleDtoList))
            return false;
        List<TbTeacherCourseScheduleDo> teacherCourseScheduleDoList = ListUtil.convertElement(courseScheduleDtoList, TbTeacherCourseScheduleDo.class);
        if (EmptyUtils.listIsEmpty(teacherCourseScheduleDoList))
            return false;
        // 未有人报名的不生成课表
        Integer gradeId = courseScheduleDtoList.get(0).getGradeId();
        List<TbStudentSignUpDo> studentSignUpDoList = iSignUpFormalCourseService.getListPayByGradeId(gradeId);
        if (EmptyUtils.listIsEmpty(studentSignUpDoList))
            return true;
        for (TbTeacherCourseScheduleDo schedule:teacherCourseScheduleDoList
             ) {
            schedule.setCreateId(userId);
            schedule.setCreateTime(new Date());
            schedule.setStatus(Byte.valueOf("0"));
            schedule.setIsSignIn(Byte.valueOf("0"));
            // TODO 签到字段，设置默认值 0
        }
        int count = teacherCourseScheduleCustomMapper.insertBatch(teacherCourseScheduleDoList);
        return count == teacherCourseScheduleDoList.size();
    }

    @Override
    public List<TeacherCourseDto> getTeacherCourseList(Map map) {
        return teacherCourseScheduleCustomMapper.getTeacherCourseList(map);
    }

    @Override
    public List<TbTeacherCourseScheduleDo> getTeacherCourseScheduleList(Integer teacherId, Integer courseId, Integer gradeId) {
        if (EmptyUtils.intIsEmpty(teacherId) || EmptyUtils.intIsEmpty(courseId) || EmptyUtils.intIsEmpty(gradeId))
            return null;
        TbTeacherCourseScheduleDoExample example = new TbTeacherCourseScheduleDoExample();
        example.setOrderByClause("period");
        TbTeacherCourseScheduleDoExample.Criteria criteria = example.createCriteria();
        criteria.andTeacherIdEqualTo(teacherId).andCourseIdEqualTo(courseId).andGradeIdEqualTo(gradeId);
        return teacherCourseScheduleDoMapper.selectByExample(example);
    }
}
