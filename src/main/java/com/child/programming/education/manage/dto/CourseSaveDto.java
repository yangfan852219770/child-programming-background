package com.child.programming.education.manage.dto;

import lombok.Data;

import java.util.List;


/**
 * @Description：新增、编辑课程信息
 * @Author：yangfan
 **/
@Data
public class CourseSaveDto {
    private Integer id; // 课程主键

    private String name; // 课程名称

    private Integer periodCount; // 课时数量

    private String code; // 课程编码

    private Integer maxCapacity; // 最大容量

    private Double money; // 价格

    private String telephone; // 联系电话

    private String introduction; // 简介

    private String photoUrl; // 图片描述地址

    private List<CourseTimeScheduleDto> timeSchedule; // 时间安排
}
