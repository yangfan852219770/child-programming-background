package com.child.programming.base.mapper;

import com.child.programming.base.model.TbStudentCourseScheduleDo;
import com.child.programming.education.manage.dto.StudentCourseDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description：
 * @Author：yangfan
 **/
@Repository
public interface StudentCourseScheduleCustomMapper {
    /**
     * 批量插入
     * @param studentCourseScheduleDoList
     * @return
     */
    int insertBatch(List<TbStudentCourseScheduleDo> studentCourseScheduleDoList);

    /**
     * 获取学生上课信息
     * @param map
     * @return
     */
    List<StudentCourseDto> getStudentCourseList(Map map);
}
