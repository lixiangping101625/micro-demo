package com.hlkj.user.stream;

import com.hlkj.auth.Account;
import com.hlkj.auth.AuthResponse;
import com.hlkj.auth.AuthService;
import com.hlkj.auth.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author Lixiangping
 * @createTime 2022年11月09日 10:57
 * @decription:
 */
@EnableBinding(value = {
        ForceLogoutTopic.class
})
@Slf4j
public class UserMessageConsumer {
    private static final String USER_TOKEN = "USER_TOKEN-";

    @Resource
    private AuthService authService;

    @StreamListener(value = ForceLogoutTopic.INPUT)
    public void consumeForceLogoutMessage(String userId){
        log.info("Begin consume Force logout user, id = {}", userId);
        Account account = Account.builder()
                .userId(Long.parseLong(userId))
                .skipVerification(true)
                .build();
        AuthResponse response = authService.delete(account);
        if (!response.getResponseCode().equals(ResponseCode.SUCCESS)){
            log.error("Error occurred when deleting user session, userId ={}", userId);
            throw new RuntimeException("cannot delete user session");
        }
    }

    /**
     * 可以进行本机重试、requeue、死信队列、服务降级（本示例采用）
     */
    @ServiceActivator(inputChannel = "force-logout-topic.force-logout-group.errors")
    public void fallback(Message message){
        log.info("Force logout failed");
        // TODO: 2022/11/9 可以进行人工接入，如通知钉钉让运营介入
        // 其实如果强制登出失败，还可以在用户表新增active/inactive标记字段，来做补充措施（用户已经被禁止）
    }

}
