package com.child.programming.base.mapper;

import com.child.programming.base.model.TbRoleDo;
import com.child.programming.base.model.TbRoleDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TbRoleDoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbggenerated Thu Apr 18 20:51:56 CST 2019
     */
    int countByExample(TbRoleDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbggenerated Thu Apr 18 20:51:56 CST 2019
     */
    int deleteByExample(TbRoleDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbggenerated Thu Apr 18 20:51:56 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbggenerated Thu Apr 18 20:51:56 CST 2019
     */
    int insert(TbRoleDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbggenerated Thu Apr 18 20:51:56 CST 2019
     */
    int insertSelective(TbRoleDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbggenerated Thu Apr 18 20:51:56 CST 2019
     */
    List<TbRoleDo> selectByExample(TbRoleDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbggenerated Thu Apr 18 20:51:56 CST 2019
     */
    TbRoleDo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbggenerated Thu Apr 18 20:51:56 CST 2019
     */
    int updateByExampleSelective(@Param("record") TbRoleDo record, @Param("example") TbRoleDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbggenerated Thu Apr 18 20:51:56 CST 2019
     */
    int updateByExample(@Param("record") TbRoleDo record, @Param("example") TbRoleDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbggenerated Thu Apr 18 20:51:56 CST 2019
     */
    int updateByPrimaryKeySelective(TbRoleDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbggenerated Thu Apr 18 20:51:56 CST 2019
     */
    int updateByPrimaryKey(TbRoleDo record);
}