package com.hlkj.stream.topics;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author Lixiangping
 * @createTime 2022年11月05日 15:14
 * @decription:
 */
public interface DelayTopic {

    String INPUT = "delayed-consumer";
    String OUTPUT = "delayed-producer";

    @Input(INPUT)
    SubscribableChannel input();

    @Output(OUTPUT)
    MessageChannel output();

}
