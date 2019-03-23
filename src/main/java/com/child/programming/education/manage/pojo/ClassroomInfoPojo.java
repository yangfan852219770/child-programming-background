package com.child.programming.education.manage.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Description：
 * @Author：yangfan
 **/

@ToString
public class ClassroomInfoPojo {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private Integer schoolId; // 校区id

    @Getter
    @Setter
    private String schoolName; // 校区名称

    @Getter
    @Setter
    private Integer code; // 教室编号

    @Getter
    @Setter
    private Integer maxCapacity; // 最大容量

    @Getter
    @Setter
    private String comment; // 教室备注

    @Getter
    @Setter
    private Date createTime; // 创建时间

}
