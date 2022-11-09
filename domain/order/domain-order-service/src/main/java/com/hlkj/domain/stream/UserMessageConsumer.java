package com.hlkj.domain.stream;

import com.hlkj.domain.order.bo.OrderStatusBO;
import com.hlkj.domain.order.mapper.OrderMapper;
import com.hlkj.domain.order.pojo.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Lixiangping
 * @createTime 2022年11月09日 10:57
 * @decription:
 */
@EnableBinding(value = {
        OrderStatusTopic.class
})
@Slf4j
public class UserMessageConsumer {

    @Resource
    private OrderMapper orderMapper;

    @StreamListener(value = OrderStatusTopic.INPUT)
    public void consumeOrderStatusMessage(OrderStatusBO bean){
        log.info("start consume order delay message. orderId={}", bean.getOrderId());
        // 根据订单id和订单状态“等待支付”从数据库查询您订单
        Order queryDomain = new Order();
        queryDomain.setId(Long.parseLong(bean.getOrderId()));
        //queryDomain.setStatus(OrderStatus.WAITING_PAT);
        List<Order> list = orderMapper.select(queryDomain);
        if (list.size() == 0) {//如果查询结果集为空，说明当前订单已付款或者已关闭
            log.info("order paid or closed, orderId={}", bean.getOrderId());

        }
        log.info("Begin consume order-status-topic user, orderId = {}", bean.getOrderId());
        // Order order = list.get(0);
        // TODO 再次判断订单是否过期
        //if (DateUtils.daysBetween(order.getExpiredTime, new Date())){
            // TODO 更新数据库订单状态为“关闭”
            // orderMapper.updateByPrimaryKey(order);
            log.info("Closed order, orderId = {}", bean.getOrderId());
        //}
    }

}
