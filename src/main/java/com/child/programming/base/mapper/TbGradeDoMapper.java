package com.child.programming.base.mapper;

import com.child.programming.base.model.TbGradeDo;
import com.child.programming.base.model.TbGradeDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TbGradeDoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grade
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    int countByExample(TbGradeDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grade
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    int deleteByExample(TbGradeDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grade
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grade
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    int insert(TbGradeDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grade
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    int insertSelective(TbGradeDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grade
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    List<TbGradeDo> selectByExample(TbGradeDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grade
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    TbGradeDo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grade
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    int updateByExampleSelective(@Param("record") TbGradeDo record, @Param("example") TbGradeDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grade
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    int updateByExample(@Param("record") TbGradeDo record, @Param("example") TbGradeDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grade
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    int updateByPrimaryKeySelective(TbGradeDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grade
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    int updateByPrimaryKey(TbGradeDo record);
}