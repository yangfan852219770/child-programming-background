package com.child.programming.base.service;

import com.child.programming.base.dto.StudentInfoDto;
import com.child.programming.base.model.TbSignUpExperienceCourseDo;
import com.child.programming.base.model.TbStudentDo;
import com.child.programming.base.model.TbStudentSignUpDo;

import java.util.List;

/**
 * @author zdp
 * @description: 学生业务逻辑处理
 */
public interface IStudentService {

    /**
     * @Description:    据openId查询学生信息
     */
    TbStudentDo getStudentByOpenId(String openid);

    /**
     * @Description:    根据openId修改学生信息
     */
    int updateStudentByOpenId(TbStudentDo studentDto);

    /**
     * @Description:    根据openId添加学生信息
     */
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

    /**
     * @Description:    报名课程
     */
    int signUpCourse(TbStudentSignUpDo studentSignUpDo);

    /**
     * @Description:    报名体验课
     */
    int signUpExperienceCourse(TbSignUpExperienceCourseDo signUpExperienceCourseDo);

    /**
     * @Description:    是否收藏此课程
     */
    Boolean isCollectCourse(int courseId, int studentId);

    /**
     * @Description:    收藏此课程
     */
    int saveCollectCourse(int courseId, int studentId);

    /**
     * @Description:    取消收藏此课程
     */
    int deleteCollectCourse(int courseId, int studentId);

    /**
     * @Description:    查询此课程是否已经报名
     */
    List<TbStudentSignUpDo> getStudentSignUpByClassIdAndStudentId(int gradeId, int studentId);

    /**
     * @Description:    查询此体验课是否已经报名
     */
    List<TbSignUpExperienceCourseDo> getsignUpExperienceCourseByExperienceCourseIdAndStudentId(int experienceCourseId, int studentId);
}
