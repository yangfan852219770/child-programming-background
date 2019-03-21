package com.child.programming.base.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Description：
 * @Author：yangfan
 **/

@ToString
public class AntdPaginationResultPojo {

    @Getter
    @Setter
    private List list;

    @Getter
    @Setter
    private AntdPaginationPojo pagination;

    public AntdPaginationResultPojo() {
    }

    public AntdPaginationResultPojo(List list, AntdPaginationPojo pagination) {
        this.list = list;
        this.pagination = pagination;
    }
}
