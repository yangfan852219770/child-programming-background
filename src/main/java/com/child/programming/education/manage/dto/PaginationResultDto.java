package com.child.programming.education.manage.dto;

import lombok.Data;

import java.util.List;

/**
 * @Description：
 * @Author：yangfan
 **/

@Data
public class PaginationResultDto {

    private List list;

    private PaginationDto pagination;

    public PaginationResultDto() {
    }

    public PaginationResultDto(List list, PaginationDto pagination) {
        this.list = list;
        this.pagination = pagination;
    }
}
