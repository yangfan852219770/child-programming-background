package com.child.programming.base.mapper;

import com.child.programming.base.model.TbStudentDto;
import com.child.programming.base.model.TbStudentDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TbStudentDtoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbggenerated Wed Mar 13 16:01:54 CST 2019
     */
    int countByExample(TbStudentDtoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbggenerated Wed Mar 13 16:01:54 CST 2019
     */
    int deleteByExample(TbStudentDtoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbggenerated Wed Mar 13 16:01:54 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbggenerated Wed Mar 13 16:01:54 CST 2019
     */
    int insert(TbStudentDto record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbggenerated Wed Mar 13 16:01:54 CST 2019
     */
    int insertSelective(TbStudentDto record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbggenerated Wed Mar 13 16:01:54 CST 2019
     */
    List<TbStudentDto> selectByExample(TbStudentDtoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbggenerated Wed Mar 13 16:01:54 CST 2019
     */
    TbStudentDto selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbggenerated Wed Mar 13 16:01:54 CST 2019
     */
    int updateByExampleSelective(@Param("record") TbStudentDto record, @Param("example") TbStudentDtoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbggenerated Wed Mar 13 16:01:54 CST 2019
     */
    int updateByExample(@Param("record") TbStudentDto record, @Param("example") TbStudentDtoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbggenerated Wed Mar 13 16:01:54 CST 2019
     */
    int updateByPrimaryKeySelective(TbStudentDto record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbggenerated Wed Mar 13 16:01:54 CST 2019
     */
    int updateByPrimaryKey(TbStudentDto record);
}