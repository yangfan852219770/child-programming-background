package com.child.programming.base.service;

import com.child.programming.base.dto.SchoolInfoDto;
import com.child.programming.base.model.TbSchoolDo;
import com.child.programming.education.manage.dto.SchoolInfoSelectDto;

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
    List<SchoolInfoDto> getList(String name);

    /**
     * 插入、更新
     * @param schoolDo
     * @param userId
     * @return
     */
    Boolean save(TbSchoolDo schoolDo, Integer userId);

    /**
     * 单个、批量删除
     * @param idArray 删除id集合
     * @param userId
     * @return
     */
    Boolean delete(String[] idArray, Integer userId);

    /**
     * ant 校区下拉框
     * @return
     */
    List<SchoolInfoSelectDto> getSchoolInfoSelectList();
}
