package com.child.programming.education.manage.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description：选择框对象
 * @Author：yangfan
 **/

@ToString
public class SelectDto {

    @Getter
    @Setter
    private Integer value; // 唯一标识

    @Getter
    @Setter
    private String label; // 名称
}
