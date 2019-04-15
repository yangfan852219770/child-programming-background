package com.child.programming.education.manage.dto;

import lombok.Data;

/**
 * @Description：选择框对象
 * @Author：yangfan
 **/

@Data
public class SelectDto {

    private Integer value; // 唯一标识

    private String label; // 名称

    private Boolean disabled; // 是否禁用
}
