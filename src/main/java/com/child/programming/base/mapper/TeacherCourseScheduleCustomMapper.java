package com.child.programming.base.mapper;

import com.child.programming.base.model.TbTeacherCourseScheduleDo;
import org.springframework.stereotype.Repository;

import java.util.List;

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

}
