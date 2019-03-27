package com.child.programming.base.mapper;

import com.child.programming.base.model.TbTeacherDo;
import com.child.programming.base.model.TbTeacherDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TbTeacherDoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    int countByExample(TbTeacherDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    int deleteByExample(TbTeacherDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    int insert(TbTeacherDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    int insertSelective(TbTeacherDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    List<TbTeacherDo> selectByExample(TbTeacherDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    TbTeacherDo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    int updateByExampleSelective(@Param("record") TbTeacherDo record, @Param("example") TbTeacherDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    int updateByExample(@Param("record") TbTeacherDo record, @Param("example") TbTeacherDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    int updateByPrimaryKeySelective(TbTeacherDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    int updateByPrimaryKey(TbTeacherDo record);
}