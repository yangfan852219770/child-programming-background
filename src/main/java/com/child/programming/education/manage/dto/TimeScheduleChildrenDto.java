package com.child.programming.education.manage.dto;

import lombok.Data;

import java.util.List;

/**
 * @Description：
 * @Author：yangfan
 **/
@Data
public class TimeScheduleChildrenDto {

    private List<String> day; // 一周，，比如一、二

    private TimeRangeDto timeRange; // 起止时间，时分秒

}
