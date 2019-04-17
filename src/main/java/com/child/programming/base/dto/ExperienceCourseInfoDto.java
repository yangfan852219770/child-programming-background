package com.child.programming.base.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Description：
 * @Author：yangfan
 **/

@Data
public class ExperienceCourseInfoDto {

    private Integer id;

    private String title;

    private Double money;

    private String introduction;

    private String photoUrl;

    private String address;

    private Date signUpEndDate;

    private String telephone;

    private Integer status;

    private Date createTime;
}
