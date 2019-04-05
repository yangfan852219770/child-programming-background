package com.child.programming.base.util;

import com.child.programming.app.web.dto.TokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class WXTokenUtil {

    /**
     * 根据 tokenPojo 生成 Token
     * @param tokenPojo
     * @return
     */
    public String generateToken(TokenDto tokenPojo) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("openId", tokenPojo.getOpenId());
        claims.put("accessToken", tokenPojo.getAccessToken());
        claims.put("created", this.generateCurrentDate());
        return this.generateToken(claims);
    }

    /**
     * 根据 claims 生成 Token
     *
     * @param claims
     * @return
     */
    private String generateToken(Map<String, Object> claims) {
        try {
            return Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(this.generateExpirationDate())
                    .signWith(SignatureAlgorithm.HS512, TokenDto.TOKEN_SECRET.getBytes("UTF-8"))
                    .compact();
        } catch (UnsupportedEncodingException ex) {
            return Jwts.builder()
                    .setClaims(claims)                              //token 开始时间+用户名+设备id
                    .setExpiration(this.generateExpirationDate())   //token 过期时间
                    .signWith(SignatureAlgorithm.HS512, TokenDto.TOKEN_SECRET)
                    .compact();
        }
    }

    /**
     * 从 token 中拿到 password
     *
     * @param token
     * @return
     */
    public String getOpenId(String token) {
        String openId;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            openId = (String) claims.get("openId");
        } catch (Exception e) {
            openId = null;
        }
        return openId;
    }

    /**
     * 从 token 中拿到 password
     *
     * @param token
     * @return
     */
    public String AetaccessToken(String token) {
        String accessToken;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            accessToken = (String) claims.get("accessToken");
        } catch (Exception e) {
            accessToken = null;
        }
        return accessToken;
    }

    /**
     * token 过期时间
     *
     * @return
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + TokenDto.TOKEN_EXPIRATION);
    }

    /**
     * 检查 token 是否处于有效期内
     * @param token
     * @return
     */
    public Boolean validateToken(String token) {
        return !(this.isTokenExpired(token));
    }

    /**
     * 检查当前时间是否在封装在 token 中的过期时间之后，若是，则判定为 token 过期
     * @param token
     * @return
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = this.getExpirationDateFromToken(token);
        return expiration.before(this.generateCurrentDate());
    }

    /**
     * 获得我们封装在 token 中的 token 过期时间
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    /**
     * 解析 token 的主体 Claims
     *
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(TokenDto.TOKEN_SECRET.getBytes("UTF-8"))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 获得当前时间
     *
     * @return
     */
    private Date generateCurrentDate() {
        return new Date(System.currentTimeMillis());
    }


}
