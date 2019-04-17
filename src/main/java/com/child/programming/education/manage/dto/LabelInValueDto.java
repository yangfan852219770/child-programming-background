package com.child.programming.education.manage.dto;

import lombok.Data;

/**
 * @Description：课程管理 班级回显信息
 * @Author：yangfan
 **/
@Data
public class LabelInValueDto {
    private Integer key; // 唯一标识

    private String label; // 内容
}
