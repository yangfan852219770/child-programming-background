package com.child.programming.education.manage.dto;

import lombok.Data;

import java.util.List;

/**
 * @Description：初始化显示班级页面信息
 * @Author：yangfan
 **/

@Data
public class InitGradeInfoDto {

    private List<CascadeSelectDto> classroomDetailInfoList;

    private List<SelectDto> teacherInfoList;

}
