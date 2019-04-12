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

    private List<String> day; // 一周，，比如一、二

    private TimeRangeDto timeRange; // 起止时间，时分秒

    private Integer gradeId; // 班级id

}
