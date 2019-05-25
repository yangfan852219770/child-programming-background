package com.child.programming.base.service.impl;

import com.child.programming.base.mapper.TbTeacherCourseScheduleDoMapper;
import com.child.programming.base.mapper.TeacherCourseScheduleCustomMapper;
import com.child.programming.base.model.TbStudentSignUpDo;
import com.child.programming.base.model.TbTeacherCourseScheduleDo;
import com.child.programming.base.model.TbTeacherCourseScheduleDoExample;
import com.child.programming.base.service.ICourseService;
import com.child.programming.base.service.ISignUpFormalCourseService;
import com.child.programming.base.service.ITeacherCourseScheduleService;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.base.util.ListUtil;
import com.child.programming.education.manage.dto.CourseDetailDto;
import com.child.programming.education.manage.dto.CourseScheduleDto;
import com.child.programming.education.manage.dto.TeacherScheduleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    @Autowired
    private ICourseService iCourseService;

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
    public List<TeacherScheduleDto> getTeacherCourseScheduleList(Integer teacherId) {
        if (EmptyUtils.intIsEmpty(teacherId))
            return null;
        TbTeacherCourseScheduleDoExample example = new TbTeacherCourseScheduleDoExample();
        example.setOrderByClause("period");
        TbTeacherCourseScheduleDoExample.Criteria criteria = example.createCriteria();
        criteria.andTeacherIdEqualTo(teacherId);
        List<TbTeacherCourseScheduleDo> teacherCourseScheduleDoList = teacherCourseScheduleDoMapper.selectByExample(example);
        if (EmptyUtils.listIsEmpty(teacherCourseScheduleDoList))
            return null;

        // 将gradeId 放入set中
        Set<Integer> gradeIdSet = new LinkedHashSet<>();
        for (TbTeacherCourseScheduleDo t:teacherCourseScheduleDoList
             ) {
            if (t.getGradeId() != null)
                gradeIdSet.add(t.getGradeId());
        }
        if (gradeIdSet.isEmpty())
            return null;
        // 根据gradeId 查询相关校区、教室、课程信息
        List<CourseDetailDto> courseDetailDtoList = iCourseService.getCourseDetaiListByGradeIdSet(gradeIdSet);
        if (EmptyUtils.listIsEmpty(courseDetailDtoList))
            return null;

        List<TeacherScheduleDto> teacherScheduleDtoList = new ArrayList<>();
        for (TbTeacherCourseScheduleDo t:teacherCourseScheduleDoList
             ) {
            for (CourseDetailDto c:courseDetailDtoList
                 ) {
                if (t.getGradeId().equals(c.getGradeId())){
                    TeacherScheduleDto teacherScheduleDto = new TeacherScheduleDto();

                    teacherScheduleDto.setTeacherCourseSchedule(t);
                    teacherScheduleDto.setCourseDetail(c);

                    teacherScheduleDtoList.add(teacherScheduleDto);
                    break;
                }
            }
        }
        return teacherScheduleDtoList;
    }
}
