package com.hlkj.domain.order.impl;

import com.hlkj.domain.order.mapper.OrderMapper;
import com.hlkj.domain.order.pojo.Order;
import com.hlkj.domain.order.service.OrderService;
import com.hlkj.domain.user.pojo.User;
import com.hlkj.domain.user.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Lixiangping
 * @createTime 2022年10月31日 20:22
 * @decription:
 */
@RestController
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private UserService userService;

    @Override
    public List<Order> listAll() {
        return orderMapper.list();
    }

//    @HystrixCommand
    @Override
    public Order detail(Long id) {
        Order order = orderMapper.getDetail(id);
        Long userId = order.getUserId();
        //feign 调用user服务
        User user = userService.detail(id);
        log.info("user -> {}", user);
        return orderMapper.getDetail(id);
    }
}
