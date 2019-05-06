package com.child.programming.education.manage.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description：
 * @Author：yangfan
 **/
@Data
public class WeekendsScheduleDto {
    private String day; // 周几有课，如一

    private String startHour; // 开始上课时间

    private String endHour; // 结束上课时间

    /**
     * 比较day、startHour的大小
     * 比目标小于返回-1，大或等于返回1；
     * @param target
     * @return
     */
    public int compareTo(WeekendsScheduleDto target){
        if (this.day.compareTo(target.getDay()) < 0 )
            return -1;
        if (this.startHour.compareTo(target.getStartHour()) < 0)
            return -1;
        return 1;
    }
}
