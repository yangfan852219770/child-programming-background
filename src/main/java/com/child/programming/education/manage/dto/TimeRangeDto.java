package com.child.programming.education.manage.dto;

import lombok.Data;

/**
 * @Description：
 * @Author：yangfan
 **/
@Data
public class TimeRangeDto {
    private String startHour; // 开时间，时分秒

    private String endHour; // 结束时间，时分秒
}
