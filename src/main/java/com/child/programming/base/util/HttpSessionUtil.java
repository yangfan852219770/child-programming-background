package com.child.programming.base.util;

import com.child.programming.base.dto.LoginedUserInfoDto;

import javax.servlet.http.HttpSession;

/**
 * @Description：
 * @Author：yangfan
 **/
public class HttpSessionUtil {

    /**
     * 获取session中登陆用户信息
     * @param session
     * @return
     */
    public static LoginedUserInfoDto getLoginedUserInfo(HttpSession session){
        return (LoginedUserInfoDto)session.getAttribute(ConstDataUtil.CURRENT_USER);
    }
}
