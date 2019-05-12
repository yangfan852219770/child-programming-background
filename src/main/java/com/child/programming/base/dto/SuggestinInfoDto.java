package com.child.programming.base.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Description：
 * @Author：yangfan
 **/
@Data
public class SuggestinInfoDto {
    private Integer id; // 主键

    private String commentText; // 评价文字

    private Date createTime; // 评价时间
}
