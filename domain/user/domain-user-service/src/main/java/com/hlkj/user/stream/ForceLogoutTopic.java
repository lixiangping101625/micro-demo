package com.hlkj.user.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author Lixiangping
 * @createTime 2022年11月09日 10:53
 * @decription: 强制登出Topic
 */
public interface ForceLogoutTopic {

    String INPUT = "force-logout-consumer";
    String OUTPUT = "force-logout-producer";

    @Input(INPUT)
    SubscribableChannel input();

    @Output(OUTPUT)
    MessageChannel output();

}
