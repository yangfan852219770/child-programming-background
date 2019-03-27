package com.child.programming.base.service;

import com.child.programming.base.dto.StudentInfoDto;
import com.child.programming.base.model.TbStudentDo;

import java.util.List;

/**
 * @author zdp
 * @description: 学生业务逻辑处理
 */
public interface IStudentService {

    TbStudentDo getStudentByOpenId(String openid);

    int updateStudent(TbStudentDo studentDto);

    int addStudent(TbStudentDo studentDto);

    /**
     * @description 查询学生信息
     * @param name 学生姓名
     * @return StudentInfoDto
     */
    List<StudentInfoDto> getList(String name);

    /**
     *  @description 新增、更新学生信息
     * @param tbStudentDo 学生信息
     * @param currentUserId 当前登录者的Id
     * @return boolean
     */
    Boolean save(TbStudentDo tbStudentDo,Integer currentUserId);

    /**
     * @description 删除、批量删除学生信息
     * @param idArray 删除学生id数组
     * @param currentUserId 当前登录者id
     * @return boolean
     */
    Boolean delete(String[] idArray, Integer currentUserId);
}
