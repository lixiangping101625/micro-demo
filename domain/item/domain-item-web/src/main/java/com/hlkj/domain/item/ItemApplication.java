package com.hlkj.domain.item;

//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Lixiangping
 * @createTime 2022年10月31日 11:14
 * @decription:
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.hlkj.domain.item.mapper")
@EnableCircuitBreaker
public class ItemApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ItemApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }

}
