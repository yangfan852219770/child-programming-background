package com.child.programming.app.web.dto;

import com.alibaba.fastjson.JSONObject;

public class TokenDto {

    //token 请求头的名字
    // public static final String TOKEN_HEADER = "X_Auth_Token";
    //token 加密密钥
    public static final String TOKEN_SECRET = "sbZhTPKQfif!5kP5LyLnLhWO&ABLSQ%6";
    //token 过期时间，以秒为单位，7200 是 2 小时
    public static final long TOKEN_EXPIRATION = 7200l;
    private String openId;
//    private String sessionKey;
    private String accessToken;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

//    public String getSessionKey() {
//        return sessionKey;
//    }
//
//    public void setSessionKey(String sessionKey) {
//        this.sessionKey = sessionKey;
//    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
