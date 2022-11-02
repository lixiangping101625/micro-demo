package com.hlkj.domain.user;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lixiangping
 * @createTime 2022年11月01日 12:57
 * @decription: 用户注册开关（通过config从GitHub拉取）
 */
@RefreshScope
@Configuration
@Data
public class UserRegisterSwitch {

    @Value("${userservice.registration.disabled}")
    private boolean disableRegistration;

}
