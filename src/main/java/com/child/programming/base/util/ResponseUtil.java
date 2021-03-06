package com.child.programming.base.util;

/**
 * @Description：返回的状态码
 * @Author：yangfan
 **/
public class ResponseUtil {
    public final static short SUCCESS_200 = 200; //正常返回

    public final static short FAIL_0 = 0; //失败返回

    public final static short INTERNAL_ERROR_500 = 500; //服务器处理有问题

    public final static short NO_LOGIN_401 = 401; //用户未登录

    public final static String SUCCESS_MSG = "操作成功";

    public final static String ERROR_MSG = "内部服务器错误";

    public final static String FAIL_MSG = "操作失败";
}
