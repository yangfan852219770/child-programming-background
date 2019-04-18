package com.child.programming.base.service;

import com.child.programming.base.dto.ClassroomDetailInfoDto;
import com.child.programming.base.model.TbClassroomDo;
import com.child.programming.education.manage.dto.CascadeSelectDto;
import com.child.programming.education.manage.dto.SelectDto;
import com.child.programming.education.manage.dto.ValidateClassroomInfoDto;

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
    List<ClassroomDetailInfoDto> getList(Map<String, Integer> map);

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

    /**
     * 删除学校时，校验是否还有教室归属删除学校
     * @return
     */
    List<ValidateClassroomInfoDto> validateSchoolId(String[] schoolIdArray);

    /**
     * 根据校区id获取教室
     * @param schoolId
     * @return
     */
    List<SelectDto> getClassroomSelectBySchoolId(Integer schoolId);

    /**
     * 级联下拉框，包括校区、教室
     * @return
     */
    List<CascadeSelectDto> getClassroomCascadeSelect();

    /**
     * 根据主键查询
     * @param classroomId
     * @return
     */
    TbClassroomDo getOneById(Integer classroomId);

    /**
     * 验证教室编码
     * @param classroomId
     * @param code
     * @param schoolId
     * @return
     */
    Boolean validateCode(Integer classroomId, Integer code, Integer schoolId);
}
