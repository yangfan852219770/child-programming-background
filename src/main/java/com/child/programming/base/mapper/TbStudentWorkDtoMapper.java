package com.child.programming.base.mapper;

import com.child.programming.base.model.TbStudentWorkDto;
import com.child.programming.base.model.TbStudentWorkDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TbStudentWorkDtoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_work
     *
     * @mbggenerated Wed Mar 13 16:01:54 CST 2019
     */
    int countByExample(TbStudentWorkDtoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_work
     *
     * @mbggenerated Wed Mar 13 16:01:54 CST 2019
     */
    int deleteByExample(TbStudentWorkDtoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_work
     *
     * @mbggenerated Wed Mar 13 16:01:54 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_work
     *
     * @mbggenerated Wed Mar 13 16:01:54 CST 2019
     */
    int insert(TbStudentWorkDto record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_work
     *
     * @mbggenerated Wed Mar 13 16:01:54 CST 2019
     */
    int insertSelective(TbStudentWorkDto record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_work
     *
     * @mbggenerated Wed Mar 13 16:01:54 CST 2019
     */
    List<TbStudentWorkDto> selectByExample(TbStudentWorkDtoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_work
     *
     * @mbggenerated Wed Mar 13 16:01:54 CST 2019
     */
    TbStudentWorkDto selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_work
     *
     * @mbggenerated Wed Mar 13 16:01:54 CST 2019
     */
    int updateByExampleSelective(@Param("record") TbStudentWorkDto record, @Param("example") TbStudentWorkDtoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_work
     *
     * @mbggenerated Wed Mar 13 16:01:54 CST 2019
     */
    int updateByExample(@Param("record") TbStudentWorkDto record, @Param("example") TbStudentWorkDtoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_work
     *
     * @mbggenerated Wed Mar 13 16:01:54 CST 2019
     */
    int updateByPrimaryKeySelective(TbStudentWorkDto record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_work
     *
     * @mbggenerated Wed Mar 13 16:01:54 CST 2019
     */
    int updateByPrimaryKey(TbStudentWorkDto record);
}