package com.hlkj.domain.user.fallback;

import com.hlkj.domain.user.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lixiangping
 * @createTime 2022年10月31日 23:26
 * @decription:
 */
@Component
@Slf4j
@RequestMapping("/jokejoke")
public class UserFallback implements UserFeignClient {

    @Override
    public List<User> listAll() {
        log.error("jump in order-service fallback, special for user-service");
        ArrayList<User> list = new ArrayList<>();
        return list;
    }

    @Override
    public User detail(Long id) {
        log.error("jump in order-service fallback, special for user-service");
        return null;
    }

    @Override
    public User login(String username, String password) {
        return null;
    }
}
