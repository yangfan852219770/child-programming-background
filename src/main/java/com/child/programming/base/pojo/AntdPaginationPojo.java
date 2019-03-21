package com.child.programming.base.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description：分页信息
 * @Author：yangfan
 **/

@ToString
public class AntdPaginationPojo {

    @Getter
    @Setter
    private Integer total; //总页数

    @Getter
    @Setter
    private Integer pageSize; //每页数据规模

    @Getter
    @Setter
    private Integer current; //当前页

    public AntdPaginationPojo() {
    }

    public AntdPaginationPojo(Integer total, Integer pageSize, Integer current) {
        this.total = total;
        this.pageSize = pageSize;
        this.current = current;
    }
}
