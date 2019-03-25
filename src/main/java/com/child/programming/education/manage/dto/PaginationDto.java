package com.child.programming.education.manage.dto;

import lombok.Data;

/**
 * @Description：分页信息
 * @Author：yangfan
 **/

@Data
public class PaginationDto {

    private Integer total; //总页数

    private Integer pageSize; //每页数据规模

    private Integer current; //当前页

    public PaginationDto() {
    }

    public PaginationDto(Integer total, Integer pageSize, Integer current) {
        this.total = total;
        this.pageSize = pageSize;
        this.current = current;
    }
}
