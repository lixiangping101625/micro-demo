package com.hlkj.domain.user.controller;

import com.hlkj.domain.user.UserRegisterSwitch;
import com.hlkj.domain.user.pojo.User;
import com.hlkj.domain.user.service.UserService;
import com.hlkj.sharedpojo.pojo.UnifyResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Lixiangping
 * @createTime 2022年10月17日 17:40
 * @decription:
 */
@RestController
@RequestMapping("/user")
@Api(value = "商品接口", tags = "用户信息展示相关接口")
@Slf4j
@RefreshScope
public class UserController {
    @Resource
    private UserService userService;

    @ApiOperation(value = "查询所有", tags = "查询所有用户信息")
    @GetMapping("/listAll")
    public List<User> list(){
        return userService.listAll();
    }

    @ApiOperation(value = "查询用户详情", tags = "查询用户详情")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "用户id", required = true)
    )
    @GetMapping("/info")
    public User detail(@RequestParam(name = "id") Long id){
        return userService.detail(id);
    }

    /**
     * 测试config控制用户注册开关
     */
    @Resource
    private UserRegisterSwitch registerSwitch;
    @GetMapping("/registry")
    public UnifyResponse registry(){
        if (registerSwitch.isDisableRegistration()) {
            return UnifyResponse.buildFailed("当前注册用户过多，请稍后再试");
        }
        return UnifyResponse.buildSuccess("注册成功");
    }

}
