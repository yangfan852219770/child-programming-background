package com.child.programming.base.mapper;

import com.child.programming.base.model.TbStudentCourseScheduleDo;
import com.child.programming.base.model.TbTeacherCourseScheduleDo;
import com.child.programming.education.manage.dto.TeacherCourseDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description：
 * @Author：yangfan
 **/
@Repository
public interface TeacherCourseScheduleCustomMapper {
    /**
     * 批量插入
     * @param teacherCourseScheduleDoList
     * @return
     */
    int insertBatch(List<TbTeacherCourseScheduleDo> teacherCourseScheduleDoList);

    /**
     * 获取老师课程信息
     * @param map
     * @return
     */
    List<TeacherCourseDto> getTeacherCourseList(Map map);
}
