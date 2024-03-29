package com.child.programming.base.mapper;

import com.child.programming.base.model.TbTeacherCourseScheduleDo;
import com.child.programming.base.model.TbTeacherCourseScheduleDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TbTeacherCourseScheduleDoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher_course_schedule
     *
     * @mbggenerated Tue May 07 14:37:18 CST 2019
     */
    int countByExample(TbTeacherCourseScheduleDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher_course_schedule
     *
     * @mbggenerated Tue May 07 14:37:18 CST 2019
     */
    int deleteByExample(TbTeacherCourseScheduleDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher_course_schedule
     *
     * @mbggenerated Tue May 07 14:37:18 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher_course_schedule
     *
     * @mbggenerated Tue May 07 14:37:18 CST 2019
     */
    int insert(TbTeacherCourseScheduleDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher_course_schedule
     *
     * @mbggenerated Tue May 07 14:37:18 CST 2019
     */
    int insertSelective(TbTeacherCourseScheduleDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher_course_schedule
     *
     * @mbggenerated Tue May 07 14:37:18 CST 2019
     */
    List<TbTeacherCourseScheduleDo> selectByExample(TbTeacherCourseScheduleDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher_course_schedule
     *
     * @mbggenerated Tue May 07 14:37:18 CST 2019
     */
    TbTeacherCourseScheduleDo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher_course_schedule
     *
     * @mbggenerated Tue May 07 14:37:18 CST 2019
     */
    int updateByExampleSelective(@Param("record") TbTeacherCourseScheduleDo record, @Param("example") TbTeacherCourseScheduleDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher_course_schedule
     *
     * @mbggenerated Tue May 07 14:37:18 CST 2019
     */
    int updateByExample(@Param("record") TbTeacherCourseScheduleDo record, @Param("example") TbTeacherCourseScheduleDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher_course_schedule
     *
     * @mbggenerated Tue May 07 14:37:18 CST 2019
     */
    int updateByPrimaryKeySelective(TbTeacherCourseScheduleDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher_course_schedule
     *
     * @mbggenerated Tue May 07 14:37:18 CST 2019
     */
    int updateByPrimaryKey(TbTeacherCourseScheduleDo record);
}