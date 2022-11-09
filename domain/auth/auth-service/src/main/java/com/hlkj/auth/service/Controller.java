package com.hlkj.auth.service;

import com.hlkj.auth.Account;
import com.hlkj.auth.AuthResponse;
import com.hlkj.auth.AuthService;
import com.hlkj.auth.ResponseCode;
import com.hlkj.domain.user.pojo.User;
import com.hlkj.domain.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author Lixiangping
 * @createTime 2022年10月26日 11:57
 * @decription:
 */
@RestController
@Slf4j
public class Controller implements AuthService {

    @Resource
    private JwtService jwtService;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private UserService userService;

    private static final String USER_TOKEN = "USER_TOKEN-";

    public AuthResponse login(@RequestParam String username,
                              @RequestParam String password){
        Account account = Account.builder()
                .userId(1L)
                .username(username)
                .build();
        // 用户名密码数据库查询
        User user = userService.login(username, password);
        if (user == null) {
            return AuthResponse.builder()
                    .responseCode(ResponseCode.INCORRECT_PWD)
                    .build();
        }
        String token = jwtService.token(account);
        account.setToken(token);
        account.setRefreshToken(UUID.randomUUID().toString());

        //存入redis
        redisTemplate.opsForValue().set(USER_TOKEN + user.getId(), account);
        redisTemplate.opsForValue().set(account.getRefreshToken(), user.getId());

        return AuthResponse.builder()
                .account(account)
                .responseCode(ResponseCode.SUCCESS)
                .build();
    }

    @Override
    public AuthResponse verify(@RequestBody Account account) {
        // 判断redis中是否存在(强制登出只会删除redis中的，所以需要检查redis中当前token是否生效)
        Object o = redisTemplate.opsForValue().get(USER_TOKEN + account.getUserId());
        if (null == o){
            return AuthResponse.builder().responseCode(ResponseCode.INVALID_TOKEN).build();
        }
        boolean success = jwtService.verify(account.getToken(), account.getUserId());
        return AuthResponse.builder()
                .responseCode(success ? ResponseCode.SUCCESS:ResponseCode.INVALID_TOKEN)
                .build();
    }

    public AuthResponse refresh(@RequestParam String refreshToken){
        Long userId= (Long) redisTemplate.opsForValue().get(refreshToken);
        if (userId == null) {// refreshToken已过期
            return AuthResponse.builder().responseCode(ResponseCode.USER_NOT_FOUND)
                    .build();
        }
        Account account = Account.builder().userId(userId).build();
        String token = jwtService.token(account);
        account.setToken(token);
        account.setRefreshToken(UUID.randomUUID().toString());
        redisTemplate.delete(account.getRefreshToken());

        redisTemplate.opsForValue().set(USER_TOKEN + account.getUserId(), token);
        redisTemplate.opsForValue().set(account.getRefreshToken(), account);

        return AuthResponse.builder()
                .account(account)
                .responseCode(ResponseCode.SUCCESS)
                .build();
    }

    public AuthResponse delete(@RequestBody Account account) {
        AuthResponse resp = new AuthResponse();
        resp.setResponseCode(ResponseCode.SUCCESS);
        if (account.isSkipVerification()) {//强制登出只提供了userId
            redisTemplate.delete(USER_TOKEN + account.getUserId());
        } else {
            AuthResponse response = verify(account);
            if (ResponseCode.SUCCESS.equals(response)) {
                redisTemplate.delete(USER_TOKEN + account.getUserId());
                redisTemplate.delete(account.getRefreshToken());
            }else{
                resp.setResponseCode(ResponseCode.INVALID_TOKEN);
            }
        }
        return resp;
    }

}