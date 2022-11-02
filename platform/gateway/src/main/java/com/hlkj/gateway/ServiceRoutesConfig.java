package com.hlkj.gateway;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;

import javax.annotation.Resource;

/**
 * @author Lixiangping
 * @createTime 2022年11月01日 00:21
 * @decription:
 */
@Configuration
public class ServiceRoutesConfig {

    @Resource
    private KeyResolver hostNameResolver;
    @Resource
    @Qualifier("redisLimitItem")
    private RedisRateLimiter redisRateLimiter;

    @Resource
    private AuthFilter authFilter;

    @Bean
    @Order
    public RouteLocator routes(RouteLocatorBuilder builder){
        return builder.routes()
                /** 测试用户认证过滤 */
                .route(r -> r.path("/order/**")
                .filters(f -> f.filter(authFilter))
                .uri("lb://DOMAIN-ORDER-SERVICE"))
                .route(r -> r.path("/item/**")
                        .filters(f -> f.requestRateLimiter(
                                c -> {
                                    c.setKeyResolver(hostNameResolver);
                                    c.setRateLimiter(redisRateLimiter);
                                    c.setStatusCode(HttpStatus.BAD_GATEWAY);
                                }
                        ))
                .uri("lb://DOMAIN-ITEM-SERVICE"))
                .route(r -> r.path("/user/**")
                .uri("lb://DOMAIN-USER-SERVICE"))
                .route(r -> r.path("/order/**")
                .uri("lb://DOMAIN-ORDER-SERVICE"))
                .route(r -> r.path("/auth/**")
                .uri("lb://PLATFORM-AUTH-SERVICE"))
                .build();
    }

}
