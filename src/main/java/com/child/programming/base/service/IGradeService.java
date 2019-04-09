package com.child.programming.base.service;

import com.child.programming.base.dto.GradeInfoDto;
import com.child.programming.base.model.TbGradeDo;
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
}
