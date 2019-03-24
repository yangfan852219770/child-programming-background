package com.child.programming.education.manage.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description：校区信息选择框对象
 * @Author：yangfan
 **/

@ToString
public class AntSchoolInfoSelectPojo {

    @Getter
    @Setter
    private Integer key; // 校区id

    @Getter
    @Setter
    private String value; // 校区名称
}
