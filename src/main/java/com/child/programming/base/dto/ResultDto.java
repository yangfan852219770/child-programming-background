package com.child.programming.base.dto;

import com.child.programming.base.util.ResponseUtil;
import lombok.Data;

/**
 * @Description：
 * @Author：yangfan
 **/
@Data
public class ResultDto {

    private short status; //返回状态

    private String msg; //返回信息

    private Object data; //返回对象

    public ResultDto(){}

    public ResultDto(short status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ResultDto(short status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功返回对象
     * @return
     */
    public static ResultDto success(){
        return  new ResultDto(ResponseUtil.SUCCESS_200,
                ResponseUtil.SUCCESS_MSG);
    }

    /**
     * 成功返回对象
     * @param o
     * @return
     */
    public static ResultDto success(Object o) {
        return  new ResultDto(ResponseUtil.SUCCESS_200,
                ResponseUtil.SUCCESS_MSG, o);
    }

    /**
     *
     * @param msg
     * @return
     */
    public static ResultDto success(Object o, String msg) {
        return  new ResultDto(ResponseUtil.SUCCESS_200, msg, o);
    }


    /**
     * 失败返回对象
     * @param msg
     * @return
     */
    public static ResultDto error(String msg){
        return new ResultDto(ResponseUtil.INTERNAL_ERROR_500, msg);
    }
}
