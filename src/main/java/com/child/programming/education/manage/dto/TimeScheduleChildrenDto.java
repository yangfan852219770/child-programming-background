package com.child.programming.education.manage.dto;

import com.child.programming.base.dto.GradeWeekendsScheduleDto;
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

    /**
     * 类型转换
     * @return
     */
    public GradeWeekendsScheduleDto convertToGradeWeekendsSchedule(){
        GradeWeekendsScheduleDto gradeWeekendsScheduleDto = new GradeWeekendsScheduleDto();
        gradeWeekendsScheduleDto.setDay(this.day);
        gradeWeekendsScheduleDto.setStartHour(this.timeRange.getStartHour());
        gradeWeekendsScheduleDto.setEndHour(this.timeRange.getEndHour());
        return gradeWeekendsScheduleDto;
    }
}
