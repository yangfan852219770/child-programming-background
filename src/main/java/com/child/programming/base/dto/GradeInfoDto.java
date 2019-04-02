package com.child.programming.base.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description：
 * @Author：yangfan
 **/

@Data
public class GradeInfoDto {

    private Integer id; // 主键

    private Integer schoolId; // 校区id

    private String schoolName; // 校区名称

    private Integer classroomId; // 教室id

    private String classroomCode; // 教室编码

    private Integer courseId; // 课程id

    private String courseName; // 课程名称

    private Integer teacherId; // 老师id

    private String teacherName; // 老师名称

    private String name; // 班级名称

    private Integer maxCapacity; // 最大容量

    private String description; // 班级描述

    private Integer currentPeriod; // 当前所上课时

    private String periodHistory; // 所上课时历史

    private Date startDate; // 上课开始时间，年月日

    private Date endDate; // 上课结束时间，年月日

    private String weekendsSchedule; // 上课安排字符串

    private Date createTime; // 创建时间

    private List<GradeWeekendsScheduleDto> weekendsScheduleList; //一周上课安排

    private List schoolAndClassroomId; // 校区和教室id集合

}
