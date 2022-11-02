package com.hlkj.config;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author Lixiangping
 * @createTime 2022年11月01日 11:29
 * @decription:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
public class ConfigServerApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigServerApp.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }

}
