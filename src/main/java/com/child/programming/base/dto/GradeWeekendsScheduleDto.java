package com.child.programming.base.dto;

import lombok.Data;

/**
 * @Description：班级的一周上课安排
 * @Author：yangfan
 **/
@Data
public class GradeWeekendsScheduleDto {

    private String day; // 周几有课，如一、二

    private String startHour; // 开始上课时间

    private String endHour; // 结束上课时间
}
