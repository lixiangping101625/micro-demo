package com.hlkj.auth;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lixiangping
 * @createTime 2022年10月26日 11:36
 * @decription:
 */
@FeignClient("platform-auth-service")
@RequestMapping("auth")
public interface AuthService {

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @PostMapping("login")
    @ResponseBody
    public AuthResponse login(@RequestParam(name = "username") String username,
                              @RequestParam(name = "password") String password);

    /**
     * token校验
     * @param username
     * @param token
     * @return
     */
    @GetMapping("verify")
    @ResponseBody
    public AuthResponse verify(@RequestParam(name = "userId") Long userId,
                               @RequestParam(name = "token") String token);

    /**
     * 刷新token
     * @param refresh
     * @return
     */
    @PostMapping("refresh")
    @ResponseBody
    public AuthResponse refresh(@RequestParam(name = "refresh") String refresh);

    /**
     * 删除token
     */
    @DeleteMapping("delete")
    @ResponseBody
    public AuthResponse delete(@RequestBody Account account);

}
