package com.child.programming.education.manage.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Description：教室前展示信息
 * @Author：yangfan
 **/

@Data
public class ClassroomInfoDto {

    private Integer id;  //主键id

    private Integer schoolId; // 校区id

    private String schoolName; // 校区名称

    private Integer code; // 教室编号

    private Integer maxCapacity; // 最大容量

    private String comment; // 教室备注

    private Date createTime; // 创建时间

}
