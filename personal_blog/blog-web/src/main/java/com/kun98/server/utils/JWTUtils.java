package com.kun98.server.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LIU JIANKUN
 * @create 2022-03-14 15:29
 */
public class JWTUtils {
    private static final String SIGN = "!@*(^*#sfdf&*$asdh$F&^";

    /**
     * 获取token
     * @param map
     * @return 根据payload获取到的token
     */
    public static String getToken(HashMap<String, String> map){

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,7);

        JWTCreator.Builder builder = JWT.create();

        for(Map.Entry<String,String> entrySet: map.entrySet()) {
            builder.withClaim(entrySet.getKey(), entrySet.getValue());
        }

        String token = builder.withExpiresAt(calendar.getTime()).sign(Algorithm.HMAC256(SIGN));

        return token;
    }

    /**
     * 验证token，如果没通过会报错
     * @param token
     */
    public static void verifyToken(String token){
        try{
            JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);//没抛出异常就通过了
        }catch (Exception e){
            throw new RuntimeException("token生成失败，您的账号密码输入错误");
        }
    }

    /**
     * 获取token中的信息
     * @param token
     * @return 解密的JWT
     */
    public static DecodedJWT getTokenInfo(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        return verify;
    }
}
