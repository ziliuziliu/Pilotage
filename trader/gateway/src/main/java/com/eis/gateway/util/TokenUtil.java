package com.eis.gateway.util;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.eis.gateway.GatewayApplication;
import com.eis.gateway.dto.UserInfo;

import java.util.Date;

public class TokenUtil {
    private static final String TAG="TokenUtil ";
    private static final long EXPIRE_TIME = 15 * 60 * 1000;
    private static final String TOKEN_SECRET = "pilotage";  //密钥盐

    public static String sign(UserInfo userInfo) {
        String token = null;
        try {
            Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("userId", userInfo.getUserId())
                    .withExpiresAt(expiresAt)
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    public static boolean authenticationVerify(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT jwt;
            try {
                jwt = verifier.verify(token);
            } catch (com.auth0.jwt.exceptions.TokenExpiredException e) {
                GatewayApplication.logger.info(TAG+"token expired");
                return false;
            }
            System.out.println("认证通过：");
            System.out.println("issuer: " + jwt.getIssuer());
            System.out.println("userId: " + jwt.getClaim("userId").asString());
            System.out.println("过期时间：      " + jwt.getExpiresAt());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
