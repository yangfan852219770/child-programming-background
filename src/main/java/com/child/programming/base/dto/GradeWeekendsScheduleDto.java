package com.child.programming.base.dto;

import lombok.Data;

import java.util.List;

/**
 * @Description：班级的一周上课安排
 * @Author：yangfan
 **/
@Data
public class GradeWeekendsScheduleDto {

    private List<String> day; // 周几有课，如一、二

    private String startHour; // 开始上课时间

    private String endHour; // 结束上课时间

    /**
     * 冲突检测
     * @param target
     * @return
     */
    public String detectConflict(GradeWeekendsScheduleDto target){
        if (null == target){
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
        // 不会执行此处
        return "-1";
    }
}
