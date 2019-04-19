package com.child.programming.education.manage.dto;

import com.child.programming.base.dto.GradeWeekendsScheduleDto;
import com.child.programming.base.util.ConstDataUtil;
import com.child.programming.base.util.DateUtil;
import com.child.programming.base.util.EmptyUtils;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.Date;
import java.util.List;

/**
 * @Description：
 * @Author：yangfan
 **/
@Data
@Log4j2
public class ValidateTimeScheduleDto {

    private Integer teacherId; // 老师id

    private Integer classroomId; // 教室id

    private Date startDate; // 开始日期

    private Date endDate; // 结束日期

    private List<GradeWeekendsScheduleDto> weekendsScheduleDtoList; // 一周时间安排

    /**
     * 冲突检测
     * @param targetValidateTime
     * @return
     */
    public String detectConflict(ValidateTimeScheduleDto targetValidateTime){
        if (!EmptyUtils.objectIsEmpty(targetValidateTime)){
            if(DateUtil.compareDate(targetValidateTime.getEndDate(), this.startDate) == 1
                    || DateUtil.compareDate(targetValidateTime.getStartDate(), this.endDate) == -1)
                return "0"; // 无时间交叉则不需要检测
            for (GradeWeekendsScheduleDto sourceSchedule:this.weekendsScheduleDtoList
            ) {
                for (GradeWeekendsScheduleDto targeSchedule:targetValidateTime.getWeekendsScheduleDtoList()
                ) {
                    String result = sourceSchedule.detectConflict(targeSchedule);
                    // 有冲突
                    if(!"0".equals(result)){
                        return result;
                    }
                }
            }
            // 执行到此处说明无冲突
            return "0";
        }
        log.warn(ConstDataUtil.VALIDATE_PARAMETER_FALSE);
        return ConstDataUtil.VALIDATE_PARAMETER_FALSE;
    }
}
