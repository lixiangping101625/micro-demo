package com.hlkj.domain.user;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Lixiangping
 * @createTime 2022年10月31日 23:08
 * @decription:
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.hlkj.domain.user.mapper")
@EnableCircuitBreaker
@EnableFeignClients(basePackages = "com.hlkj.auth")
@ComponentScan(basePackages = "com.hlkj")//需要扫描包含com.hlkj.user.stream
public class UserApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(UserApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
