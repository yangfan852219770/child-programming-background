package com.child.programming.base.service;

import com.child.programming.base.dto.GradeInfoDto;
import com.child.programming.base.model.TbGradeDo;
import com.child.programming.education.manage.dto.*;

import java.util.List;
import java.util.Map;

/**
 * @Description：
 * @Author：yangfan
 **/
public interface IGradeService {

    /**
     * 班级列表
     * @param map 查询条件
     * @return
     */
    List<GradeInfoDto> getList(Map map);

    /**
     * 初始化班级页面信息
     * @return
     */
    InitGradeInfoDto initGradeInfo();

    /**
     * 新增、编辑
     * @param gradeDo
     * @param userId
     * @return
     */
    Boolean save(TbGradeDo gradeDo, Integer userId);

    /**
     * 获取班级select信息
     * @param gradeIds 此id集合的select禁用
     * @return
     */
    List<SelectDto> getGradeInfoSelectList(String gradeIds);

    /**
     * 验证班级容量
     * @param classroomId 教室id
     * @param maxCapacity 班级最大容量
     * @return
     */
    Integer validateCapacity(Integer classroomId, Integer maxCapacity);

    /**
     * 根据主键获取对象
     * @param gradeId
     * @return
     */
    TbGradeDo getOneById(Integer gradeId);

    /**
     * 根据classroomId，获取集合
     * @param classroomId 教室id
     * @return
     */
    List<TbGradeDo> getListByClassroomId(Integer classroomId);

    /**
     * 根据teacherId，获取集合
     * @param teacherId 老师id
     * @return
     */
    List<TbGradeDo> getListByTeacherId(Integer teacherId);

    /**
     * 班级的课程安排校验
     * @param timeSchedule
     * @param courseId
     * @return
     */
    String validateTimeScheduleConflict(List<CourseTimeScheduleDto> timeSchedule, Integer courseId);

    /**
     * 更新时间安排 、课程id
     * @param courseId
     * @param userId
     * @param courseTimeScheduleDtoList
     * @return
     */
    Boolean updateTimeSchedule(Integer courseId, Integer userId, List<CourseTimeScheduleDto> courseTimeScheduleDtoList);

    /**
     * 根据课程id查询
     * @param courseId
     * @return
     */
    List<TbGradeDo> getListByCourseId(Integer courseId);

    /**
     * 将班级的时间安排转换
     * @param gradeDo
     * @return
     */
    CourseTimeScheduleDto convertToCourseTimeSchedule(TbGradeDo gradeDo);

    /**
     * 删除
     * @param idArray
     * @param userId
     * @return
     */
    Boolean delete(String[] idArray, Integer userId);

    /**
     * 删除时，校验是否有课程占用
     * @param idArray
     * @return
     */
    Boolean validateCourseId(String[] idArray);

    /**
     * 将班级的课程信息，转化为课程表
     * @param gradeDo
     * @return
     */
    List<CourseScheduleDto> convertToCourseSchedule(TbGradeDo gradeDo);

    /**
     * 删除教室时，是否有班级占用校验
     * @param idArray
     * @return
     */
    List<ValidateDeleteDto> validateByClassroomIds(String[] idArray);

    /**
     * 删除老师时，是否有班级占用
     * @param idArray
     * @return
     */
    List<ValidateDeleteDto> validateByTeacherIds(String[] idArray);
}
