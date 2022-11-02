package com.hlkj.domain.order.controller;

import com.hlkj.domain.order.pojo.Order;
import com.hlkj.domain.order.service.OrderService;
import com.hlkj.sharedpojo.pojo.UnifyResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Lixiangping
 * @createTime 2022年10月31日 20:28
 * @decription:
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/listAll")
    public UnifyResponse listAll(){
        List<Order> orders = orderService.listAll();
        return UnifyResponse.buildSuccess(orders);
    }

    @GetMapping("/detail/{id}")
    public UnifyResponse detail(@PathVariable("id") Long id){
        Order order = orderService.detail(id);
        return UnifyResponse.buildSuccess(order);
    }
}
