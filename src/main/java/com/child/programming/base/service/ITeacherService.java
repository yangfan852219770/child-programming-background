package com.child.programming.base.service;

import com.child.programming.base.dto.LoginedUserInfoDto;
import com.child.programming.base.dto.ShiroDto;
import com.child.programming.base.dto.TeacherInfoDto;
import com.child.programming.base.model.TbTeacherDo;
import com.child.programming.education.manage.dto.SelectDto;

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

    /**
     * 重置密码
     * @param teacherId 老师id
     * @param userId 操作者id
     * @return
     */
    Boolean resetPassword(Integer teacherId, Integer userId);

    /**
     * 老师选择框信息
     * @return
     */
    List<SelectDto> getTeacherSelectList();

    /***
     * 教师登录校验
     * @param loginId
     * @param password
     * @return
     */
    LoginedUserInfoDto getTeacherByLoginIdAndPassword(String loginId , String password);

    /**
     * 根据主键查询老师
     * @param teacherId
     * @return
     */
    TbTeacherDo getOneById(Integer teacherId);
    /***
     * 根据登录名称查询教师
     * @param loginId
     * @return
     */
    ShiroDto getTeacherByLoginId(String loginId);

    /**
     * 校验登陆账号是否重复
     * @param id 主键
     * @param loginId
     * @return true 可用
     */
    Boolean validateLoginId(Integer id, String loginId);
}
