package com.child.programming.base.dto;

import com.child.programming.base.util.ConstDataUtil;
import com.child.programming.education.manage.dto.TimeRangeDto;
import com.child.programming.education.manage.dto.TimeScheduleChildrenDto;
import com.child.programming.education.manage.dto.WeekendsScheduleDto;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description：班级的一周上课安排
 * @Author：yangfan
 **/
@Data
@Log4j2
public class GradeWeekendsScheduleDto {

    private List<String> day; // 周几有课，如[一、二]

    private String startHour; // 开始上课时间

    private String endHour; // 结束上课时间

    /**
     * 冲突检测
     * @param target
     * @return
     */
    public String detectConflict(GradeWeekendsScheduleDto target){
        if (null != target){
            for (String sourceDay:this.day
            ) {
                for (String targetDay:target.getDay()
                ) {
                    // 星期相同，则要检测时间安排
                    if (targetDay.equals(sourceDay)){
                        if (target.getEndHour().compareTo(this.startHour) < 0
                        || target.getStartHour().compareTo(this.endHour) > 0)
                            continue;
                        // 执行此处，说明不满足上述条件，有冲突
                        return "星期" + day + "时间安排有冲突!";
                    }
                }
            }
            // 执行此处return，说明没有检测到冲突
            return "0";
        }
        log.warn(ConstDataUtil.VALIDATE_PARAMETER_FALSE);
        return ConstDataUtil.VALIDATE_PARAMETER_FALSE;
    }

    /**
     * 类型转换
     * @return
     */
    public TimeScheduleChildrenDto convertToTimeScheduleChildren(){
        TimeScheduleChildrenDto timeScheduleChildrenDto = new TimeScheduleChildrenDto();

        TimeRangeDto timeRangeDto =  new TimeRangeDto();
        timeRangeDto.setStartHour(this.startHour);
        timeRangeDto.setEndHour(this.endHour);

        timeScheduleChildrenDto.setDay(this.day);
        timeScheduleChildrenDto.setTimeRange(timeRangeDto);

        return timeScheduleChildrenDto;
    }

    /**
     * 类型转换，主要是将星期拆分开
     * @return
     */
    public List<WeekendsScheduleDto> convertToWeekendsSchedule(){
        List<WeekendsScheduleDto> weekendsScheduleDtoList = new ArrayList<>();
        for (String day:this.day
             ) {
            WeekendsScheduleDto weekendsScheduleDto = new WeekendsScheduleDto();

            weekendsScheduleDto.setDay(day);
            weekendsScheduleDto.setStartHour(this.startHour);
            weekendsScheduleDto.setEndHour(this.endHour);

            weekendsScheduleDtoList.add(weekendsScheduleDto);
        }
        return weekendsScheduleDtoList;
    }
}
