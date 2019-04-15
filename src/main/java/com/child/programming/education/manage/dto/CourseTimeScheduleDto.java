package com.child.programming.education.manage.dto;

import lombok.Data;

import java.util.List;

/**
 * @Description：保存课程时，时间安排对象
 * @Author：yangfan
 **/
@Data
public class CourseTimeScheduleDto {

    private DateRangeDto dateRange; // 起止日期，年月日

    private Integer gradeId; // 班级id

    private List<TimeScheduleChildrenDto> childrenData; // 星期和时间集合

}
