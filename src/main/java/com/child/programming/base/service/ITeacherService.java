package com.child.programming.base.service;

import com.child.programming.base.dto.TeacherInfoDto;
import com.child.programming.base.model.TbTeacherDo;

import java.util.List;

/**
 * @Description：
 * @Author：yangfan
 **/

public interface ITeacherService {

    /**
     * 查询老师
     * @param name 老师姓名
     * @return
     */
    // TODO 目前没有分页
    List<TeacherInfoDto> getList(String name);

    /**
     * 插入、更新
     * @param teacherDo
     * @param userId 操作者id
     * @return
     */
    Boolean save(TbTeacherDo teacherDo, Integer userId);

    /**
     * 单个删除、批量
     * @param idArray id集合
     * @param userId 操作用户id
     * @return
     */
    Boolean delete(String[] idArray, Integer userId);
}
