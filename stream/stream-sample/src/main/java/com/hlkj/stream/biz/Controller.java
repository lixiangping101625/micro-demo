package com.hlkj.stream.biz;

import com.hlkj.stream.topics.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Lixiangping
 * @createTime 2022年11月05日 15:19
 * @decription:
 */
@RestController
@Slf4j
public class Controller {

    //注入生产者
    @Resource
    private MyTopic producer;
    @Resource
    private GroupTopic groupTopicProducer;
    @Resource
    private DelayTopic delayTopicProducer;
    @Resource
    private ErrorTopic errorTopicProducer;
    @Resource
    private RequeueTopic requeueTopicProducer;
    @Resource
    private DLQTopic dlqTopicProducer;
    @Resource
    private FallbackTopic fallbackTopicProducer;

    @PostMapping("/send")
    public void sendMessage(@RequestParam(name = "body") String body){
        producer.output().send(MessageBuilder.withPayload(body).build());
    }

    @PostMapping("/send2Group")
    public void send2Group(@RequestParam(name = "body") String body){
        groupTopicProducer.output().send(MessageBuilder.withPayload(body).build());
    }

    @PostMapping("/sendDelayedMessage")
    public void sendDelayedMessage(@RequestParam(name = "seconds") Integer seconds,
                                    @RequestParam(name = "body") String body){
        log.info("ready to send delayed message");
        delayTopicProducer.output().send(MessageBuilder.withPayload(body)
                                            .setHeader("x-delay", 1000*seconds)
                                            .build());
    }

    // 异常重试（单机重试——consumer的重试）
    @PostMapping("/sendError")
    public void sendErrorMsg(@RequestParam(name = "body") String body){
        MessageBean messageBean = new MessageBean();
        messageBean.setPayload(body);
        errorTopicProducer.output().send(MessageBuilder.withPayload(messageBean).build());
    }

    // 异常重试（联机版——重新入列）
    @PostMapping("/requeue")
    public void requeue(@RequestParam(name = "body") String body){
        MessageBean messageBean = new MessageBean();
        messageBean.setPayload(body);
        requeueTopicProducer.output().send(MessageBuilder.withPayload(messageBean).build());
    }

    // 死信队列
    @PostMapping("/dlq")
    public void sendMessageToDLQ(@RequestParam(name = "body") String body){
        MessageBean messageBean = new MessageBean();
        messageBean.setPayload(body);
        dlqTopicProducer.output().send(MessageBuilder.withPayload(messageBean).build());
    }

    // fallback + 升版
    @PostMapping("/fallback")
    public void sendMessageToFallback(@RequestParam(name = "body") String body,
                                      @RequestParam(name = "version", defaultValue = "1.0") String version){
        MessageBean messageBean = new MessageBean();
        messageBean.setPayload(body);
        fallbackTopicProducer.output().send(
                MessageBuilder.withPayload(messageBean)
                        .setHeader("version", version)
                        .build());
    }

}
