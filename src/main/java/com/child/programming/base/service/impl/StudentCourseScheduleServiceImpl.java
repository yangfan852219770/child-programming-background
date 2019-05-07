package com.child.programming.base.service.impl;

import com.child.programming.base.mapper.StudentCourseScheduleCustomMapper;
import com.child.programming.base.mapper.TbStudentCourseScheduleDoMapper;
import com.child.programming.base.model.TbStudentCourseScheduleDo;
import com.child.programming.base.model.TbStudentSignUpDo;
import com.child.programming.base.service.ISignUpFormalCourseService;
import com.child.programming.base.service.IStudentCourseScheduleService;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.base.util.ListUtil;
import com.child.programming.education.manage.dto.CourseScheduleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
    private StudentCourseScheduleCustomMapper studentCourseScheduleCustomMapper;

    @Autowired
    private ISignUpFormalCourseService iSignUpFormalCourseService;

    // TODO 批量操作，事务处理
    @Override
    public Boolean generateSchedule(List<CourseScheduleDto> courseScheduleDtoList, Integer gradeId, Integer userId) {
        if (EmptyUtils.listIsEmpty(courseScheduleDtoList) || EmptyUtils.intIsEmpty(gradeId))
            return false;
        // 缴费、不是中途报名的学生
        // 无人报名，则不必生成课表
        List<TbStudentSignUpDo> studentSignUpDoList = iSignUpFormalCourseService.getListByGradeId(gradeId);
        if (EmptyUtils.listIsEmpty(studentSignUpDoList))
            return true;
        List<TbStudentCourseScheduleDo> studentCourseScheduleDoList = new ArrayList<>();
        // TODO 之后分步插入，打印日志
        for (TbStudentSignUpDo studentSignUp:studentSignUpDoList
             ) {
            List<TbStudentCourseScheduleDo> studentCourseScheduleDoList1 = ListUtil.convertElement(courseScheduleDtoList, TbStudentCourseScheduleDo.class);
            if (EmptyUtils.listIsEmpty(studentCourseScheduleDoList1))
                return false;
            for (TbStudentCourseScheduleDo schedule:studentCourseScheduleDoList1
                 ) {
                schedule.setCreateId(userId);
                schedule.setCreateTime(new Date());
                schedule.setStatus(Byte.valueOf("1"));
                // TODO 给其余字段，设置默认值
                schedule.setStudentId(studentSignUp.getStudentId());
            }
            studentCourseScheduleDoList.addAll(studentCourseScheduleDoList1);
        }
        int count = studentCourseScheduleCustomMapper.insertBatch(studentCourseScheduleDoList);

        return count == studentCourseScheduleDoList.size();
    }
}
