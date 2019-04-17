package com.child.programming.education.manage.dto;

import com.child.programming.base.dto.GradeWeekendsScheduleDto;
import lombok.Data;
import org.apache.jasper.tagplugins.jstl.core.ForEach;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description：保存课程时，时间安排对象
 * @Author：yangfan
 **/
@Data
public class CourseTimeScheduleDto {

    private DateRangeDto dateRange; // 起止日期，年月日

    private Integer gradeId; // 班级id

    private LabelInValueDto gradeSelect; // 回显选择框信息

    private List<TimeScheduleChildrenDto> childrenData; // 星期和时间集合

    /**
     * 类型转换
     * @return
     */
    public List<GradeWeekendsScheduleDto> convertToGradeWeekendsSchedule(){
        List<GradeWeekendsScheduleDto> gradeWeekendsScheduleDtoList = new ArrayList<>();
        for (TimeScheduleChildrenDto children:this.childrenData
             ) {
            gradeWeekendsScheduleDtoList.add(children.convertToGradeWeekendsSchedule());
        }
        return gradeWeekendsScheduleDtoList;
    }

}
