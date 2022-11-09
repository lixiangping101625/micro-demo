package com.hlkj.domain.order.controller;

import com.hlkj.domain.order.bo.OrderStatusBO;
import com.hlkj.domain.order.mapper.OrderMapper;
import com.hlkj.domain.order.pojo.Order;
import com.hlkj.domain.order.service.OrderService;
import com.hlkj.domain.stream.OrderStatusTopic;
import com.hlkj.sharedpojo.pojo.UnifyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Lixiangping
 * @createTime 2022年10月31日 20:28
 * @decription:
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Resource
    private OrderService orderService;

    @Resource
    private OrderStatusTopic orderStatusProducer;

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

    @Resource
    private OrderMapper orderMapper;

    /**
     * 下单接口
     * @param order
     * @return
     */
    @PostMapping("/place")
    public UnifyResponse placeOrder(@RequestBody Order order){
        order.setId(1010L);//数据库没有
        order.setItemsJson("{\"id\": 1, \"count\": 2, \"price\": 100, \"title\": \"针织衫\"}");
        // 发送延迟消息（延时支付）
        OrderStatusBO message = new OrderStatusBO();
        message.setOrderId(order.getId().toString());
        log.info("Start send order delay message, orderId = {}", order.getId());
        orderStatusProducer.output()
                .send(MessageBuilder.withPayload(message)
                        .setHeader("x-delay", 60000)// 订单延时支付：1分钟（实际建议稍加几分钟）
                        .build());
        //TODO 向支付中心发起请求生成支付信息
        //向数据库插入订单
        int i = orderMapper.insert(order);
        return i>0 ? UnifyResponse.buildSuccess("下单成功"):UnifyResponse.buildFailed("下单失败");
    }

}
