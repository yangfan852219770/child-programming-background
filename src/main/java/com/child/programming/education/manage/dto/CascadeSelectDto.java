package com.child.programming.education.manage.dto;

import lombok.Data;

import java.util.List;

/**
 * @Description：级联选择框
 * @Author：yangfan
 **/

@Data
public class CascadeSelectDto {

    private Integer value; // 唯一标识

    private String label; // 名称

    private List<CascadeSelectDto> children;
}
