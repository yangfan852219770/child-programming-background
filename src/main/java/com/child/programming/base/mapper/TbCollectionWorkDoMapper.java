package com.child.programming.base.mapper;

import com.child.programming.base.model.TbCollectionWorkDo;
import com.child.programming.base.model.TbCollectionWorkDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TbCollectionWorkDoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table collection_work
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    int countByExample(TbCollectionWorkDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table collection_work
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    int deleteByExample(TbCollectionWorkDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table collection_work
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table collection_work
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    int insert(TbCollectionWorkDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table collection_work
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    int insertSelective(TbCollectionWorkDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table collection_work
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    List<TbCollectionWorkDo> selectByExample(TbCollectionWorkDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table collection_work
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    TbCollectionWorkDo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table collection_work
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    int updateByExampleSelective(@Param("record") TbCollectionWorkDo record, @Param("example") TbCollectionWorkDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table collection_work
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    int updateByExample(@Param("record") TbCollectionWorkDo record, @Param("example") TbCollectionWorkDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table collection_work
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    int updateByPrimaryKeySelective(TbCollectionWorkDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table collection_work
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    int updateByPrimaryKey(TbCollectionWorkDo record);
}