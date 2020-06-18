package com.child.programming.base.mapper;

import com.child.programming.base.model.TbReportDo;
import com.child.programming.base.model.TbReportDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TbReportDoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table report
     *
     * @mbggenerated Tue May 14 14:04:29 CST 2019
     */
    int countByExample(TbReportDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table report
     *
     * @mbggenerated Tue May 14 14:04:29 CST 2019
     */
    int deleteByExample(TbReportDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table report
     *
     * @mbggenerated Tue May 14 14:04:29 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table report
     *
     * @mbggenerated Tue May 14 14:04:29 CST 2019
     */
    int insert(TbReportDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table report
     *
     * @mbggenerated Tue May 14 14:04:29 CST 2019
     */
    int insertSelective(TbReportDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table report
     *
     * @mbggenerated Tue May 14 14:04:29 CST 2019
     */
    List<TbReportDo> selectByExample(TbReportDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table report
     *
     * @mbggenerated Tue May 14 14:04:29 CST 2019
     */
    TbReportDo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table report
     *
     * @mbggenerated Tue May 14 14:04:29 CST 2019
     */
    int updateByExampleSelective(@Param("record") TbReportDo record, @Param("example") TbReportDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table report
     *
     * @mbggenerated Tue May 14 14:04:29 CST 2019
     */
    int updateByExample(@Param("record") TbReportDo record, @Param("example") TbReportDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table report
     *
     * @mbggenerated Tue May 14 14:04:29 CST 2019
     */
    int updateByPrimaryKeySelective(TbReportDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table report
     *
     * @mbggenerated Tue May 14 14:04:29 CST 2019
     */
    int updateByPrimaryKey(TbReportDo record);
}