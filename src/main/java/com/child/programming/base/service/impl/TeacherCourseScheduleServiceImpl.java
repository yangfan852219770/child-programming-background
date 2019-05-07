package com.child.programming.base.service.impl;

import com.child.programming.base.mapper.TbTeacherCourseScheduleDoMapper;
import com.child.programming.base.mapper.TeacherCourseScheduleCustomMapper;
import com.child.programming.base.model.TbTeacherCourseScheduleDo;
import com.child.programming.base.service.ITeacherCourseScheduleService;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.base.util.ListUtil;
import com.child.programming.education.manage.dto.CourseScheduleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    @Override
    public Boolean generateSchedule(List<CourseScheduleDto> courseScheduleDtoList, Integer userId) {
        if (EmptyUtils.listIsEmpty(courseScheduleDtoList))
            return false;
        List<TbTeacherCourseScheduleDo> teacherCourseScheduleDoList = ListUtil.convertElement(courseScheduleDtoList, TbTeacherCourseScheduleDo.class);
        if (EmptyUtils.listIsEmpty(teacherCourseScheduleDoList))
            return false;
        for (TbTeacherCourseScheduleDo schedule:teacherCourseScheduleDoList
             ) {
            schedule.setCourseId(userId);
            schedule.setCreateTime(new Date());
            schedule.setStatus(Byte.valueOf("0"));
            // TODO 属性设置默认
        }
        int count = teacherCourseScheduleCustomMapper.insertBatch(teacherCourseScheduleDoList);
        return count == teacherCourseScheduleDoList.size();
    }
}
