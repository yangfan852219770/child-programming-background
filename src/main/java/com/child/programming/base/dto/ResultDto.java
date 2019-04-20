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
     * @return
     */
    public static ResultDto success(String msg){
        return  new ResultDto(ResponseUtil.SUCCESS_200,
                msg);
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
     * 内部服务器错误
     * @param msg
     * @return
     */
    public static ResultDto error(String msg){
        return new ResultDto(ResponseUtil.INTERNAL_ERROR_500, msg);
    }

    /**
     * 未登录
     * @param msg
     * @return
     */
    public static ResultDto noLogin(String msg){
        return new ResultDto(ResponseUtil.NO_LOGIN_401, msg);
    }


    /**
     * 操作失败
     * @return
     */
    public static ResultDto fail(){
        return new ResultDto(ResponseUtil.FAIL_0, ResponseUtil.FAIL_MSG);
    }

    /**
     * 操作失败
     * @param msg
     * @return
     */
    public static ResultDto fail(String msg){
        return new ResultDto(ResponseUtil.FAIL_0, msg);
    }

    /**
     * 操作失败
     * @param msg
     * @param o
     * @return
     */
    public static ResultDto fail(String msg, Object o) {
        return new ResultDto(ResponseUtil.FAIL_0, msg, o);
    }

}
