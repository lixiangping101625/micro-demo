package com.hlkj.stream.biz;

import com.hlkj.stream.topics.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Lixiangping
 * @createTime 2022年11月04日 13:12
 * @decription:
 */
@Slf4j
//把信道(和topic类似)加载到上下文
@EnableBinding(value = {
        Sink.class,
        MyTopic.class,
        GroupTopic.class,
        DelayTopic.class,
        ErrorTopic.class,
        RequeueTopic.class,
        DLQTopic.class,
        FallbackTopic.class}
    )
public class StreamConsumer {

    private AtomicInteger count = new AtomicInteger(1);

    @StreamListener(value = Sink.INPUT)//指定监听的信道
    public void consume(String payload){
        log.info("message consumed successfully. payload={}", payload.toString());
    }

    @StreamListener(value = MyTopic.INPUT)//指定监听的信道
    public void consumeMyMessage(String payload){
        log.info("My message consumed successfully. payload={}", payload);
    }

    @StreamListener(value = GroupTopic.INPUT)//指定监听的信道
    public void consumeGroupMessage(String payload){
        log.info("group message consumed successfully. payload={}", payload);
    }

    @StreamListener(value = DelayTopic.INPUT)//指定监听的信道
    public void consumeDelayedMessage(String payload){
        log.info("delayed message consumed successfully. payload={}", payload);
    }

    // 异常重试（单机版）
    @StreamListener(value = ErrorTopic.INPUT)//指定监听的信道
    public void consumeErrorMessage(MessageBean payload){
        log.info("Are you OK? ");
        if(count.incrementAndGet()%3==0){
            log.info("fine,thank you");
            count.set(0);
        }else{
            log.info("what's your problem?");
            throw new RuntimeException("I'm not OK");
        }
        log.info("Error message consumed successfully,payload={}",payload);
    }

    // 异常重试（联机版-重新入列）
    @StreamListener(value = RequeueTopic.INPUT)//指定监听的信道
    public void requeueErrorMessage(MessageBean payload){
        log.info("Are you OK? ");
        try {
            // 3秒后消息会回退到消息队列，有同一个消费组的其他消费者消费
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        throw new RuntimeException("I'm not OK");
    }

    // 死信队列消费
    @StreamListener(value = DLQTopic.INPUT)//指定监听的信道
    public void consumeDLQMessage(MessageBean payload){
        log.info("Dlq Are you OK? ");
        if(count.incrementAndGet()%3==0){
            log.info("Dlq fine,thank you");
        }else{
            log.info("Dlq what's your problem?");
            throw new RuntimeException("Dlq I'm not OK");
        }
    }

    // fallback + 升版
    @StreamListener(value = FallbackTopic.INPUT)//指定监听的信道
    public void goodbyeBadGuy(MessageBean payload, @Header("version") String version){
        log.info("Fallback Are you OK? ");
        if("1.0".equalsIgnoreCase(version)){
            log.info("Fallback fine,thank you");
        }else if("2.0".equalsIgnoreCase(version)){
            log.info("unsupported version");
            throw new RuntimeException("Fallback I'm not OK");
        }else{
            log.info("Fallback - version = {}", version);
        }
    }

    @ServiceActivator(inputChannel = "fallback-topic.fallback-group.errors")
    public void fallback(Message<?> message){
        log.info("fallback entered");
//        Object payload = message.getPayload();
//        MessageHeaders headers = message.getHeaders();
    }

}
