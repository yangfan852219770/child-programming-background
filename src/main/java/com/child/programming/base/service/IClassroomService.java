package com.child.programming.base.service;

import com.child.programming.base.model.TbClassroomDto;

import java.util.List;

/**
 * @Description：
 * @Author：yangfan
 **/
public interface IClassroomService {

    /**
     * 查询教室
     * @param schoolId 校区id
     * @return
     */
    // TODO 无分页
    List<TbClassroomDto> getList(Integer schoolId);

    /**
     * 新增、编辑
     * @param classroomDto
     * @param userId
     * @return
     */
    Boolean save(TbClassroomDto classroomDto, Integer userId);

    /**
     * 单个、批量删除
     * @param idArray
     * @param userId
     * @return
     */
    Boolean delete(String[] idArray, Integer userId);
}
