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
        redisTemplate.opsForValue().set(user.getId(), account);

        return AuthResponse.builder()
                .account(account)
                .responseCode(ResponseCode.SUCCESS)
                .build();
    }

    @Override
    public AuthResponse verify(Long userId, String token) {
        // 判断redis中是否存在(强制登出只会删除redis中的，所以需要检查redis中当前token是否生效)
        Object o = redisTemplate.opsForValue().get(userId);
        if (null == o){
            return AuthResponse.builder().responseCode(ResponseCode.INVALID_TOKEN).build();
        }
        boolean success = jwtService.verify(token, userId);
        return AuthResponse.builder()
                .responseCode(success ? ResponseCode.SUCCESS:ResponseCode.INVALID_TOKEN)
                .build();
    }

    public AuthResponse refresh(@RequestParam String refreshToken){
        Account account = (Account) redisTemplate.opsForValue().get(refreshToken);
        if (account == null) {
            return AuthResponse.builder().responseCode(ResponseCode.USER_NOT_FOUND)
                    .build();
        }

        String token = jwtService.token(account);
        account.setToken(token);
        account.setRefreshToken(UUID.randomUUID().toString());
        redisTemplate.delete(account.getRefreshToken());
        redisTemplate.opsForValue().set(account.getRefreshToken(), account);

        return AuthResponse.builder()
                .account(account)
                .responseCode(ResponseCode.SUCCESS)
                .build();
    }

    public AuthResponse delete(@RequestParam(name = "userId") Long userId) {
        //删除redis
        redisTemplate.delete(userId);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setResponseCode(ResponseCode.SUCCESS);
        return authResponse;
    }

}