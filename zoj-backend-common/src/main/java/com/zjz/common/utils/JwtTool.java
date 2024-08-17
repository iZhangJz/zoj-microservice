package com.zjz.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zjz.common.common.ErrorCode;
import com.zjz.common.exception.BusinessException;

import java.time.Duration;
import java.util.Date;
import java.util.Map;

/**
 * Jwt登录认证 工具类
 */
public class JwtTool {
    /**
     * 生成 jwt 令牌
     * @param userId 用户 id
     * @return token
     */
    public static String createToken(Long userId, Duration ttl) {
        return JWT.create()
                .withClaim("user", userId) // 设置负载
                .withExpiresAt(new Date(System.currentTimeMillis() + ttl.toMillis()))    // 设置令牌过期时间
                .sign(Algorithm.HMAC256("zhangjz"));
    }

    /**
     * 解析 jwt 令牌
     * @param token token
     * @return true or false
     */
    public static Long parseToken(String token) {
        // 1. 验证 token 是否为空
        if (token == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        // 2. 验证 token 是否合法
        DecodedJWT decodedJWT;
        try{
            decodedJWT = JWT.require(Algorithm.HMAC256("zhangjz")).build().verify(token);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.TOKEN_INVALID_ERROR);
        }
        // 3. 验证 token 是否过期
        if (decodedJWT.getExpiresAt().before(new Date())) {
            throw new BusinessException(ErrorCode.TOKEN_EXPIRE_ERROR);
        }
        Map<String, Claim> claims = decodedJWT.getClaims();
        Claim user = claims.get("user");
        if (user == null) {
            throw new BusinessException(ErrorCode.TOKEN_INVALID_ERROR);
        }
        return user.asLong();
    }
}
