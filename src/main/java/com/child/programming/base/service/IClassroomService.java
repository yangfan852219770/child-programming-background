package com.child.programming.base.service;

import com.child.programming.base.model.TbClassroomDo;
import com.child.programming.education.manage.dto.ClassroomInfoDto;

import java.util.List;
import java.util.Map;

/**
 * @Description：
 * @Author：yangfan
 **/
public interface IClassroomService {

    /**
     * 查询教室
     * @param map 校区id map
     * @return
     */
    // TODO 无分页
    List<ClassroomInfoDto> getList(Map<String, Integer> map);

    /**
     * 新增、编辑
     * @param classroomDo
     * @param userId
     * @return
     */
    Boolean save(TbClassroomDo classroomDo, Integer userId);

    /**
     * 单个、批量删除
     * @param idArray
     * @param userId
     * @return
     */
    Boolean delete(String[] idArray, Integer userId);
}
