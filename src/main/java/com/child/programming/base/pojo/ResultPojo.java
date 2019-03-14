package com.child.programming.base.pojo;

import com.child.programming.base.util.ResponseUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description：
 * @Author：yangfan
 **/
@ToString
public class ResultPojo {

    @Getter
    @Setter
    private Integer status; //返回状态

    @Getter
    @Setter
    private String msg; //返回信息

    @Getter
    @Setter
    private Object data; //返回对象

    public ResultPojo(){}

    public ResultPojo(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ResultPojo(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功返回对象
     * @param o
     * @return
     */
    public static ResultPojo success(Object o){
        return  new ResultPojo(ResponseUtil.SUCCESS_200,
                ResponseUtil.SUCCESS_MSG, o);
    }

    /**
     * 失败返回对象
     * @param msg
     * @return
     */
    public static ResultPojo error(String msg){
        return new ResultPojo(ResponseUtil.INTERNAL_ERROR_500, msg);
    }
}
