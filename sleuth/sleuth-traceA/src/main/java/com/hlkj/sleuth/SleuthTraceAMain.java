package com.hlkj.sleuth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author Lixiangping
 * @createTime 2022年11月03日 15:44
 * @decription:
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController//启动类也可以做controller
@Slf4j
public class SleuthTraceAMain {

    @LoadBalanced
    @Bean
    public RestTemplate lb(){
        return new RestTemplate();
    }

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/traceA")
    public String traceA(){
        log.info("-----------------TraceA");
        // 调用traceB服务
        return restTemplate.getForEntity("http://sleuth-traceB/traceB", String.class).getBody();
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(SleuthTraceAMain.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }

}
