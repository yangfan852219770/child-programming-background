package com.child.programming.base.mapper;

import com.child.programming.base.model.TbStudentCourseScheduleDo;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
