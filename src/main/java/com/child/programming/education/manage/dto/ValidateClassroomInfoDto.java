package com.child.programming.education.manage.dto;

import lombok.Data;

/**
 * @Description：删除学校时，校验教室返回信息
 * @Author：yangfan
 **/

@Data
public class ValidateClassroomInfoDto {

    private String classroomCode; // 教室编码

    private Integer schoolId; // 学校id

    private String schoolName; // 学校名称
}
