package com.hlkj.gateway;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

/**
 * @author Lixiangping
 * @createTime 2022年10月27日 16:56
 * @decription: Redis限流配置
 */
@Configuration
public class RedisLimitConfiguration {
    // 为不同业务指定不同的resolver. eg：用户模块-ip地址限流 商品模块-用户登录名称限流。

    // key：限流分组的标识，每个key相当于一个令牌桶.这里将请求的HostAddress作为key
    @Bean
    @Primary
    public KeyResolver remoteAddrKeyResolver() {
        return exchange -> Mono.just(
                exchange.getRequest()
                        .getRemoteAddress()
                        .getHostName());
    }

    @Bean("redisLimitItem")
    @Primary //作为默认的RedisRateLimiter
    public RedisRateLimiter redisLimitItem() {
        return new RedisRateLimiter(1, 2);
    }

    @Bean("redisLimitOrder")
    public RedisRateLimiter redisLimitOrder() {
        return new RedisRateLimiter(20, 50);
    }

}