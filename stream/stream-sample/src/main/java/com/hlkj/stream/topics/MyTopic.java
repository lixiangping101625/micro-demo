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
public interface MyTopic {

    String INPUT = "myTopic";
    String OUTPUT = "myTopicOut";

    @Input(INPUT)
    SubscribableChannel input();

    /**
     * 指定生产者
     * @return
     */
//    @Output(INPUT)//暴雷了
    @Output(OUTPUT)
    MessageChannel output();

}
