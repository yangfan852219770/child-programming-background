package com.child.programming.base.service;

import com.child.programming.base.model.TbSchoolDto;

import java.util.List;

/**
 * @Description：
 * @Author：yangfan
 **/
public interface ISchoolService {

    /**
     * 查询校区
     * @param name 校区名称
     * @return
     */
    //TODO 目前没有分页
    List<TbSchoolDto> getList(String name);

    /**
     * 插入、更新
     * @param schoolDto
     * @param userId
     * @return
     */
    Boolean save(TbSchoolDto schoolDto, Integer userId);

    /**
     * 单个、批量删除
     * @param idArray 删除id集合
     * @param userId
     * @return
     */
    Boolean delete(String[] idArray, Integer userId);
}
