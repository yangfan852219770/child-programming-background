package com.child.programming.base.mapper;

import com.child.programming.base.model.TbClassroomDo;
import com.child.programming.base.model.TbClassroomDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TbClassroomDoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classroom
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    int countByExample(TbClassroomDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classroom
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    int deleteByExample(TbClassroomDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classroom
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classroom
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    int insert(TbClassroomDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classroom
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    int insertSelective(TbClassroomDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classroom
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    List<TbClassroomDo> selectByExample(TbClassroomDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classroom
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    TbClassroomDo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classroom
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    int updateByExampleSelective(@Param("record") TbClassroomDo record, @Param("example") TbClassroomDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classroom
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    int updateByExample(@Param("record") TbClassroomDo record, @Param("example") TbClassroomDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classroom
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    int updateByPrimaryKeySelective(TbClassroomDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classroom
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    int updateByPrimaryKey(TbClassroomDo record);
}