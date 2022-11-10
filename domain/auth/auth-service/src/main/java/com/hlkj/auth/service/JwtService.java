package com.hlkj.auth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.hlkj.auth.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Lixiangping
 * @createTime 2022年10月26日 11:46
 * @decription:
 */
@Slf4j
@Service
public class JwtService {

    private static final String ISSUE = "@Hlkj_163666";
    private static final long TOKEN_EXP_TIME = 30 * 60 * 1000; //30分钟
    private static final String USER_ID= "userid";

    /**
     * 生成token
     * @param account
     * @return
     */
    public String token(Account account){
        Date now = new Date();

        Algorithm algorithm = Algorithm.HMAC256("changeIt");

        String token = JWT.create()
                .withIssuer(ISSUE)
                .withIssuedAt(now)
                .withExpiresAt(new Date(now.getTime() + TOKEN_EXP_TIME))
                .withClaim(USER_ID, account.getUserId())
                //.withClaim("role", "")
                .sign(algorithm);
        log.info("jwt generated user={}", account.getUserId());
        return token;
    }

    /**
     * 校验token
     * @param token
     * @param username
     * @return
     */
    public boolean verify(String token, Long userId){
        log.info("verify jwt. userId={}", userId);
        try {
            Algorithm algorithm = Algorithm.HMAC256("changeIt");
            JWTVerifier jwtVerifier = JWT.require(algorithm)
                    .withIssuer(ISSUE)
                    .withClaim(USER_ID, userId)
                    .build();
            jwtVerifier.verify(token);
            return true;
        }catch (Exception e){
            log.info("verify failed");
            return false;
        }
    }
}