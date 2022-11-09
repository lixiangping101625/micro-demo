package com.hlkj.domain.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author Lixiangping
 * @createTime 2022年11月09日 10:53
 * @decription: 强制登出Topic
 */
public interface OrderStatusTopic {

    String INPUT = "order-status-consumer";
    String OUTPUT = "order-status-producer";

    @Input(INPUT)
    SubscribableChannel input();

    @Output(OUTPUT)
    MessageChannel output();

}
