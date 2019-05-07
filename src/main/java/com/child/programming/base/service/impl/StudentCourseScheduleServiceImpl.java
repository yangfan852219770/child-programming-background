package com.child.programming.base.service.impl;

import com.child.programming.base.mapper.TbStudentCourseScheduleDoMapper;
import com.child.programming.base.model.TbStudentSignUpDo;
import com.child.programming.base.service.ISignUpFormalCourseService;
import com.child.programming.base.service.IStudentCourseScheduleService;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.education.manage.dto.CourseScheduleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description：
 * @Author：yangfan
 **/
@Service
public class StudentCourseScheduleServiceImpl implements IStudentCourseScheduleService {
    @Autowired
    private TbStudentCourseScheduleDoMapper studentCourseScheduleDoMapper;

    @Autowired
    private ISignUpFormalCourseService iSignUpFormalCourseService;

    @Override
    public Boolean generateSchedule(List<CourseScheduleDto> courseScheduleDtoList, Integer gradeId, Integer userId) {
        if (EmptyUtils.listIsEmpty(courseScheduleDtoList) || EmptyUtils.intIsEmpty(userId))
            return false;
        // 缴费、不是中途报名的学生
        List<TbStudentSignUpDo> studentSignUpDoList = iSignUpFormalCourseService.getListByGradeId(gradeId);
        if (EmptyUtils.listIsEmpty(studentSignUpDoList))
            return false;
        for (TbStudentSignUpDo studentSignUp:studentSignUpDoList
             ) {
            for (CourseScheduleDto courseSchedule:courseScheduleDtoList
                 ) {

            }
        }
        return null;
    }
}
