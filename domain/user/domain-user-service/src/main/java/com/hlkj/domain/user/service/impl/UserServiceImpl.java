package com.hlkj.domain.user.service.impl;

import com.hlkj.domain.user.mapper.UserMapper;
import com.hlkj.domain.user.pojo.User;
import com.hlkj.domain.user.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Lixiangping
 * @createTime 2022年10月17日 17:23
 * @decription:
 */
@RestController //eureka时基于http的服务治理框架，所以service提供的服务需要声明成controller
//@Service //注意不需要
@Slf4j
public class UserServiceImpl implements UserService {

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;

    public List<User> listAll() {
        logger.info("查询用户列表");
        return userMapper.list();
    }

    @HystrixCommand(fallbackMethod = "detailSelfHandle")
    public User detail(@RequestParam(name = "id") Long id) {
//        int i = 10/0;
        return userMapper.getDetail(id);
    }

    @Override
    public User login(@RequestParam(name = "username") String username,
                      @RequestParam(name = "password") String password) {
        return userMapper.login(username, password);
    }

    private User detailSelfHandle(@RequestParam(name = "id") Long id){
        log.error("jump in detailSelfHandle function");
        User user = new User();
        user.setUsername("内部降级username");
        return user;
    }


}