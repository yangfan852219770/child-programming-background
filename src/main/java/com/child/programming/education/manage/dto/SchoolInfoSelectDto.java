package com.child.programming.education.manage.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description：校区信息选择框对象
 * @Author：yangfan
 **/

@ToString
public class SchoolInfoSelectDto {

    @Getter
    @Setter
    private Integer key; // 校区id

    @Getter
    @Setter
    private String value; // 校区名称
}
