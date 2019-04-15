package com.child.programming.base.service;

import com.child.programming.base.dto.GradeInfoDto;
import com.child.programming.base.model.TbGradeDo;
import com.child.programming.education.manage.dto.CourseTimeScheduleDto;
import com.child.programming.education.manage.dto.InitGradeInfoDto;
import com.child.programming.education.manage.dto.SelectDto;

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
     * @return
     */
    List<SelectDto> getGradeInfoSelectList();

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
     * @return
     */
    String validateTimeScheduleConflict(List<CourseTimeScheduleDto> timeSchedule);
}
